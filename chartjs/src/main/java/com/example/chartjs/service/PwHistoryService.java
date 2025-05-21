package com.example.chartjs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chartjs.dto.PwHistory;
import com.example.chartjs.mapper.PwHistoryMapper;

@Service
public class PwHistoryService {

    @Autowired
    private PwHistoryMapper mapper;

    public boolean isDuplicatePassword(String id, String pw) {
        PwHistory history = new PwHistory();
        history.setId(id);
        history.setPw(pw);
        return mapper.isDuplicatePassword(history) > 0;
    }

    public void insertPwHistory(String id, String pw) {
        PwHistory history = new PwHistory();
        history.setId(id);
        history.setPw(pw);
        mapper.insertPwHistory(history);
    }

    public void deleteOldHistoriesForAllUsers() {
        List<String> userIds = mapper.getAllUserIds();
        for (String id : userIds) {
            mapper.deleteOldHistories(id);
        }
    }
    
    public void changePassword(String id, String pw) {
        PwHistory history = new PwHistory();
        history.setId(id);
        history.setPw(pw);

        // 1. 비밀번호 중복 여부 확인
        if (mapper.isDuplicatePassword(history) > 0) {
            throw new IllegalArgumentException("이전에 사용한 비밀번호입니다.");
        }

        // 2. member 테이블 비밀번호 변경
        mapper.updateMemberPassword(history);

        // 3. pw_history 이력 추가
        mapper.insertPwHistory(history);
    }
}
