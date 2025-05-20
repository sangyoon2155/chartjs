package com.example.chartjs.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LoginHistory {
	private Integer no;
	private String id;
	private LocalDateTime logindate;
}
