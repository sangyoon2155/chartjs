package com.example.chartjs.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.chartjs.mapper.LoginMapper;
import com.example.chartjs.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberMapper memberMapper;
	
	public int deactivateDormantMembers() {
	    LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);
	    return memberMapper.deactivateDormantMembers(tenMinutesAgo);
	}
	
	public void updateActiveStatus(String id, String active) {
	    memberMapper.updateActiveStatus(id, active);
	}
}
