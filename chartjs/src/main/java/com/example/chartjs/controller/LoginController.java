package com.example.chartjs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.chartjs.dto.Member;
import com.example.chartjs.service.LoginService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired LoginService loginService;
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, Member parammember, HttpServletResponse response) {
		System.out.println("로그인 시도: " + parammember.getId() + " / " + parammember.getPassword());
		Member loginMember = loginService.login(parammember);

	    if (loginMember != null) {
	    	System.out.println("로그인 성공: " + loginMember.getId());
	    	session.setAttribute("loginMember", loginMember);

	        return "redirect:/Main"; // 메인 페이지나 대시보드로 리디렉트
	    } else {
	        // 로그인 실패 시 다시 로그인 폼으로
	    	System.out.println("로그인 실패!");
	        return "redirect:/login?error=true";
	    }
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/Main")
	public String Main() {
		return "Main";
	}

}
