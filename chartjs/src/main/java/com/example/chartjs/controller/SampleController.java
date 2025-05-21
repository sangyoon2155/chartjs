package com.example.chartjs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.chartjs.dto.SampleForm;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SampleController {
	@GetMapping("/addSample")
	public String addSample() {
		return "addSample";
	}
	
	@PostMapping("/addSample")
	public String addSample(@Valid SampleForm sampleForm,Errors err, Model model) {
		log.info(sampleForm.toString());
		if(err.hasErrors()) {
			for(FieldError fe : err.getFieldErrors()) {
				model.addAttribute(fe.getField()+"ErrMsg",fe.getDefaultMessage());
				
			}
			return "addSample";
		}
		
		return "redirect:/";
	}
}
