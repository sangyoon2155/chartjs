package com.example.chartjs.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.chartjs.mapper.LoginHistoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginHistoryService {
	private final LoginHistoryMapper loginHistoryMapper;
	
	public int deleteLoginHistoryByOneMonth() {
		LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
		return loginHistoryMapper.deleteLoginHistoryByOneMonth(oneMonthAgo);
	}
	
	public int deleteLoginHistoryOlderThanFiveMinutes() {
        // 현재 시간을 기준으로 5분 전 시간 구하기
        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);

        // DB에서 5분 이상 지난 로그인 이력 삭제
        return loginHistoryMapper.deleteLoginHistoryBefore(fiveMinutesAgo);
    }
}
