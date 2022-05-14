package com.myungsang.myungsang_backend.admin.service;

import com.myungsang.myungsang_backend.admin.iservice.AdminIService;
import com.myungsang.myungsang_backend.admin.repository.AdminIMapper;
import com.myungsang.myungsang_backend.admin.vo.AdminVO;
import com.myungsang.myungsang_backend.main_feed.repository.MainFeedIMapper;
import com.myungsang.myungsang_backend.main_feed.vo.MainFeedVO;
import com.myungsang.myungsang_backend.test.repository.TestIMapper;
import com.myungsang.myungsang_backend.test.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService implements AdminIService {

    @Autowired
    private AdminIMapper adminIMapper;

//    @Autowired
//    private TestIMapper testIMapper;

    @Autowired
    private MainFeedIMapper mainFeedIMapper;

//    @Override
//    public String adminTest(AdminVO adminVO) {
//        List<TestVO> testDataList = testIMapper.getTestData();
//        int beforeShowId = (int) adminVO.getBeforeShowId();
//        int afterShowId = (int) adminVO.getAfterShowId();
//        List<Integer> beforeShowIdList = new ArrayList<Integer>();
//
//        for(TestVO data: testDataList) {
//            beforeShowIdList.add(data.getId());
//        }
//
//        if(!beforeShowIdList.contains(beforeShowId)) {
//            return "ShowID does not exist";
//        }
//
//        beforeShowIdList.remove((Object) beforeShowId);
//        List<Integer> afterShowIdList = new ArrayList<Integer>();
//        afterShowIdList.add(afterShowId);
//        for(Integer id: beforeShowIdList) {
//            if(afterShowIdList.contains(id)) {
//                afterShowIdList.add(id + 1);
//            } else {
//                afterShowIdList.add(id);
//            }
//        }
//        afterShowIdList.remove((Object) afterShowId);
//
//        AdminVO changeAdmin = new AdminVO();
//        changeAdmin.setBeforeShowId(beforeShowId);
//        changeAdmin.setAfterShowId(0);
//        adminIMapper.AdminUpdateShowId(changeAdmin);
//
//        for(int i = beforeShowIdList.size() - 1; i >= 0; i--) {
//            if(beforeShowIdList.get(i) != afterShowIdList.get(i)) {
//                changeAdmin.setBeforeShowId(beforeShowIdList.get(i));
//                changeAdmin.setAfterShowId(afterShowIdList.get(i));
//                adminIMapper.AdminUpdateShowId(changeAdmin);
//            }
//        }
//
//        changeAdmin.setBeforeShowId(0);
//        changeAdmin.setAfterShowId(afterShowId);
//        adminIMapper.AdminUpdateShowId(changeAdmin);
//
//        return "ShowID is updated";
//    }

    @Override
    public String adminUpdateShowId(AdminVO adminVO) {
        List<MainFeedVO> mainFeedDataList = mainFeedIMapper.mainVideos();
        long beforeShowId = adminVO.getBeforeShowId();
        long afterShowId = adminVO.getAfterShowId();
        List<Long> beforeShowIdList = new ArrayList<Long>();

        for(MainFeedVO data: mainFeedDataList) {
            beforeShowIdList.add(data.getShowId());
        }

        if(!beforeShowIdList.contains(beforeShowId)) {
            return "ShowID does not exist";
        }

        beforeShowIdList.remove((Object) beforeShowId);
        List<Long> afterShowIdList = new ArrayList<Long>();
        afterShowIdList.add(afterShowId);
        for(long id: beforeShowIdList) {
            if(afterShowIdList.contains(id)) {
                afterShowIdList.add(id + 1);
            } else {
                afterShowIdList.add(id);
            }
        }
        afterShowIdList.remove((Object) afterShowId);

        AdminVO changeAdmin = new AdminVO();
        changeAdmin.setBeforeShowId(beforeShowId);
        changeAdmin.setAfterShowId(0);
        adminIMapper.AdminUpdateShowId(changeAdmin);

        for(int i = beforeShowIdList.size() - 1; i >= 0; i--) {
            if(beforeShowIdList.get(i) != afterShowIdList.get(i)) {
                changeAdmin.setBeforeShowId(beforeShowIdList.get(i));
                changeAdmin.setAfterShowId(afterShowIdList.get(i));
                adminIMapper.AdminUpdateShowId(changeAdmin);
            }
        }

        changeAdmin.setBeforeShowId(0);
        changeAdmin.setAfterShowId(afterShowId);
        adminIMapper.AdminUpdateShowId(changeAdmin);

        return "ShowID is updated";
    }
}
