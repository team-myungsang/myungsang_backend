package com.myungsang.myungsang_backend.security;

import com.myungsang.myungsang_backend.user.iservice.UserIService;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

@Service
public class JwtService {

    @Autowired
    private UserIService userIService;

    @Value("${security.expire.accesstoken}")
    private Long ACCESS_TOKEN_EXP_TIME;     //            // 30분 기준

    @Value("${security.expire.refreshtoken}")
    private Long REFRESH_TOKEN_EXP_TIME;     //           // 7일 기준

    @Value("${security.secretkey}")
    private String SECRET_KEY;   // 암/복호 키

    public Map<String, Object> createToken(UserVO userVO) {
        String accessToken = createAccessToken(String.valueOf(userVO.getId()), ACCESS_TOKEN_EXP_TIME, SECRET_KEY);  // 클라이언트에 제공
        String refreshToken = createRefreshToken(REFRESH_TOKEN_EXP_TIME, SECRET_KEY);                      // Redis에 저장 & 클라이언트에 제공

        List<String> userIdAccToken = new ArrayList<>();
        userIdAccToken.add(String.valueOf(userVO.getId()));
        userIdAccToken.add(accessToken);
//        save(refreshToken, userIdAccToken, REFRESH_TOKEN_EXP_TIME);    // RefreshToken DB에 저장
        userVO.setRefreshToken(refreshToken);
        userIService.saveRefreshToken(userVO);

        Map<String, Object> map = new HashMap<>();
        map.put("accessToken", accessToken);
        map.put("refreshToken", refreshToken);

        return map;
    }

    // AccessToken 발행
    public String createAccessToken(String userId, Long expTime, String secretKey) {
        if (expTime < 0L) throw new RuntimeException("만료시간이 0보다 커야합니다.");

        Key signingKey = makeKey(secretKey);

        Date expireTime = new Date(System.currentTimeMillis() + expTime);
        // 추가로 클레임, 헤더 등 다양한 정보를 더 넣을 수 있음
        return Jwts.builder()
                .setSubject(userId)                          // userId & 발행일자를 담아서 암호화
                .setIssuedAt(new Date())
                .signWith(signingKey, SignatureAlgorithm.HS256)   // key, key 암호화 알고리즘 사용
                .setExpiration(expireTime).compact();       // 만료 시점 지정 & compact to String
    }

    // RefreshToken 발행
    public String createRefreshToken(Long expTime, String secretKey) {
        if (expTime < 0L) throw new RuntimeException("만료시간이 0보다 커야합니다.");

        Key signingKey = makeKey(secretKey);

        Date expireTime = new Date(System.currentTimeMillis() + expTime);

        // 추가로 클레임, 헤더 등 다양한 정보를 더 넣을 수 있음
        return Jwts.builder()
                .setSubject("valid")
                .setIssuedAt(new Date())                     // 발행일자를 담아서 암호화
                .signWith(signingKey, SignatureAlgorithm.HS256)   // key, key 암호화 알고리즘 사용
                .setExpiration(expireTime).compact();       // 만료 시점 지정 & compact to String
    }

    // SignKey 생성
    public Key makeKey(String secretKey) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;       // HS256 암호화 알고리즘 사용
        byte[] securityByte = DatatypeConverter.parseBase64Binary(secretKey);   // String 형태의 키를 Byte 형태로 인코딩

        return new SecretKeySpec(securityByte, signatureAlgorithm.getJcaName());   // 암호화된 Key 생성
    }

    // 토큰 Validation & Get Subject
    public String decodeToken(UserVO userVO) {
        // 클레임 : Payload 에 들어있는 값
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .build()
                    .parseClaimsJws(userVO.getAccessToken())
                    .getBody();

            return claims.getSubject();
        }catch(ExpiredJwtException e1){
            e1.printStackTrace();
            return "expire";
        }catch(JwtException e2){
            e2.printStackTrace();
            return "invalid";
        }
    }

    // AccessToken 의 무제한 발행을 막아야한다!
    // RefreshToken 받아서 AccessToken 재발행
    public Map<String, Object> validRefreshToken(UserVO userVO) {
        Map<String, Object> map = new HashMap<>();

        String acUserId = decodeToken(userVO);    // AccessToken을 통해 userId를 추출한다. -> 만료라면 "expire" 반환
        UserVO resultUser = userIService.getUserByRefreshToken(userVO);
        List<Object> userIdAccToken = new ArrayList<Object>();         // redis로 부터 key(refreshToken)를 통해 userId & First AccessToken(rfToken 복호화에 사용)을 가져온다.

        if(resultUser == null) {
            map.put("msg", "RefreshToken has been expired");
            map.put("status", 401);
        }
        // RefreshToken 인증은 성공했지만 AccessToken이 만료되지 않은 경우 = AccessToken이 살아있는데 재발급 받으려는 경우 : 발급 불가 반환
        else if(acUserId.equals(Long.toString(resultUser.getId()))) {
            map.put("msg", "AccessToken Already Valid.");
            map.put("status", 403);    // 발급 불가
        }
        // RefreshToken 유효 & AccessToken 유효한 값이지만 만료 => 재발급
        else {
            map.put("token", createAccessToken(String.valueOf(Long.toString(resultUser.getId())), ACCESS_TOKEN_EXP_TIME, SECRET_KEY));
            map.put("status", 200);
            map.put("msg", "Access Token Updated Complete");
            map.put("userId", userIdAccToken.get(0));
        }

        return map;
    }



}
