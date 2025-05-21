package com.example.chartjs.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.chartjs.dto.LoginHistory;
import com.example.chartjs.dto.Member;
import com.example.chartjs.mapper.LoginMapper;
import com.example.chartjs.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberMapper memberMapper;
    private final LoginMapper loginMapper;

    public Member login(Member param) {
        // ID와 비밀번호로 회원 조회
        Member member = memberMapper.selectByIdAndPassword(param.getId(), param.getPassword());

        if (member != null) {
            // 로그인 이력 저장
            LoginHistory history = new LoginHistory();
            history.setId(member.getId());
            history.setLogindate(LocalDateTime.now()); // 현재 로그인 시간
            loginMapper.insertLoginHistory(history); // login_history 테이블에 삽입
        }

        return member; // 로그인 성공한 회원 반환
    }
    
    private void insertLoginHistory(String id) {
        LocalDateTime now = LocalDateTime.now();
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setId(id);
        loginHistory.setLogindate(now); // 현재 로그인 시간

        // login_history 테이블에 삽입
        loginMapper.insertLoginHistory(loginHistory);
    }
}
