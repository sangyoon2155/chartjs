package com.example.chartjs.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.chartjs.dto.LoginHistory;

@Mapper
public interface LoginMapper {
	void insertLoginHistory(LoginHistory history);
}
