package com.example.chartjs.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.chartjs.dto.Member;

@Mapper
public interface MemberMapper {
	Member selectByIdAndPassword(@Param("id") String id,
            @Param("password") String password);

	void updateLoginDate(@Param("id") String id,
	    @Param("loginDate") LocalDateTime loginDate);
	
	int deactivateDormantMembers(@Param("tenMinutesAgo") LocalDateTime tenMinutesAgo);
	
	void updateActiveStatus(@Param("id") String id, @Param("active") String active);
}
