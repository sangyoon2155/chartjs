package com.example.chartjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.chartjs.dto.Member;
import com.example.chartjs.service.PwHistoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PwHistoryController {

    @Autowired
    private PwHistoryService service;

    @GetMapping("/changePw")
    public String changePwPage() {
        return "changePw";
    }

    @PostMapping("/changePw")
    public String changePw(@RequestParam("password") String pw, HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        String id = loginMember.getId();

        try {
            service.changePassword(id, pw);
            model.addAttribute("msg", "비밀번호가 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "Main";
    }


    @PostMapping("/checkDuplicatePw")
    @ResponseBody
    public boolean checkDuplicatePw(@RequestParam("password") String pw, HttpSession session) {
    	Member loginMember = (Member) session.getAttribute("loginMember");
    	String id = loginMember.getId();
        return service.isDuplicatePassword(id, pw);
    }
}
