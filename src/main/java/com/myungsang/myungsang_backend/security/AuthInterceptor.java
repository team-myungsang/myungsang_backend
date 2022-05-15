package com.myungsang.myungsang_backend.security;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.myungsang.myungsang_backend.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Value("${security.secretkey}")
    private String secretKey;

    @Value("${security.accesstoken}")
    private String accessToken;

    @Value("${security.refreshtoken}")
    private String refreshToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception {
        Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
        boolean firstHeader = true;
        for (String header : headers) { // there can be multiple Set-Cookie attributes
            if (firstHeader) {
                response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=" + "None"));
                firstHeader = false;
                continue;
            }
            response.addHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite=" + "None"));
        }

        System.out.println("Interceptor Pre / Request Url : " + request.getRequestURI());
        String requestUri = request.getRequestURI();

        if(requestUri.equals("/login")
                || requestUri.equals("/logout")
                || requestUri.equals("/register")
                || requestUri.equals("/main/videos")
                || requestUri.equals("/categories")
                || requestUri.equals("/validRefreshToken")
            ) {
            return true;
        }

        if(Objects.equals(requestUri.split("/")[1], "videos")) {
            return true;
        }

        if(Objects.equals(requestUri.split("/")[1], "main")) {
            return true;
        }

        if(Objects.equals(requestUri.split("/")[1], "users")) {
            return true;
        }

        Map<String, Object> map = new HashMap<>();
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();

        String acToken = request.getHeader(accessToken);                            // Header를 통해 Token 받기
        String rfToken;
        if(request.getHeader(refreshToken) == null) {
            rfToken = null;
        } else {
            rfToken = (String) request.getHeader(refreshToken);
        }

        UserVO userVO = new UserVO();
        userVO.setAccessToken(acToken);
        userVO.setRefreshToken(rfToken);

        if(acToken == null || acToken.length() < 24) {                               // Access 토큰이 없을 때
            map.put("status", 401);
            map.put("msg", "There is no Token");
        }
        else if(rfToken != null){                                           // Access 토큰과 Refresh 토큰이 둘 다 있을 때
            map = jwtService.validRefreshToken(userVO);
            if((int) map.get("status") == 200) {
                response.setHeader(refreshToken, rfToken);
                response.setHeader(accessToken, (String) map.get("token"));  // Access 토큰이 성공적으로 재 발행 되었을 때
                request.setAttribute("userId", map.get("userId"));
            }
        }
        else{                                                                         // Access 토큰만 있을 때
            String ret = jwtService.decodeToken(userVO);
            if(ret.equals("expire")) {
                map.put("msg", "AccessToken has been expired");
                map.put("status", 401);
            }
            else if(ret.equals("invalid")) {
                map.put("msg", "AccessToken is invalid");
                map.put("status", 403);
            }
            else {
                request.setAttribute("userId", Integer.parseInt(ret));
                map.put("status", 200);                                                // AccessToken이 유효할 때
            }
        }
        jsonObject.addProperty("token", (String) map.get("token"));
        jsonObject.addProperty("msg", (String) map.get("msg"));

        response.setStatus((int) map.get("status"));
        if((int) map.get("status") == 200) {
            return true;
        }

        response.getWriter().write(gson.toJson(jsonObject));
        return false;
    }

}
