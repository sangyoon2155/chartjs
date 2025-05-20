package com.example.chartjs.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.chartjs.mapper.LoginMapper;
import com.example.chartjs.mapper.MemberMapper;
import com.example.chartjs.service.LoginHistoryService;
import com.example.chartjs.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MySchedule {
	@Autowired
	private MemberService memberService;
	
	private final LoginHistoryService loginHistoryService;
	
	
	@Scheduled(cron = "59 59 23 25 * *")
	public void mySchedule() {
		log.info("매월 25일 23시 59분 59초");
	}
	
	@Scheduled(cron = "0 0 0 1 * *")
	public void cleanOldDate() {
		int deleted = loginHistoryService.deleteLoginHistoryByOneMonth();
		System.out.println("삭제된 로그인 이력 수: " + deleted);
	}
	
	/*
	@Scheduled(cron = "0 0/5 * * * *")  // 5분마다 실행
    public void deleteOldLoginHistory() {
        int deleted = loginHistoryService.deleteLoginHistoryOlderThanFiveMinutes();
        System.out.println("삭제된 로그인 이력 수 (5분 이상): " + deleted);
    }
    */
	
	@Scheduled(fixedRate = 60000) // 1분마다 실행
	public void deactivateDormantAccounts() {
	    int count = memberService.deactivateDormantMembers();
	    System.out.println("휴면 처리된 계정 수: " + count);
	}
	
	/*
	  매월 25일 23시 59분 59초 스케줄러 호출
	  
	  매월 1일 0시0분0초 스케줄러 호출 
	   1개월 지난 로그인 이력을 삭제하는 서비스 메서드
	  	  
	  로그인 컨트롤러
	   로그인시 이력을 입력하는 메서드
	*/
}
