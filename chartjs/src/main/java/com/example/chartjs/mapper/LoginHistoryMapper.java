package com.example.chartjs.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginHistoryMapper {
	int deleteLoginHistoryByOneMonth(@Param("cutoffDate") LocalDateTime cutoffDate);
	
	int deleteLoginHistoryBefore(LocalDateTime fiveMinutesAgo);
}
