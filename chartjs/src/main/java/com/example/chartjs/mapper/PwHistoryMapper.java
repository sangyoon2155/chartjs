package com.example.chartjs.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.chartjs.dto.PwHistory;

@Mapper
public interface PwHistoryMapper {

    int isDuplicatePassword(PwHistory history);

    void insertPwHistory(PwHistory history);

    void deleteOldHistories(String id);

    List<String> getAllUserIds(); // for scheduler
    
    void updateMemberPassword(PwHistory history);
}
