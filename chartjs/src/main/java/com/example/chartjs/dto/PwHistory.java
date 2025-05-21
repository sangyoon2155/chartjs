package com.example.chartjs.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PwHistory {
	private Integer no;
	private String id;
	private String pw;
	private LocalDateTime changeDate;
}
