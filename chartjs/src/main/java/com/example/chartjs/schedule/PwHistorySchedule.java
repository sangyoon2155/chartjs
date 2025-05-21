package com.example.chartjs.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.chartjs.service.PwHistoryService;

@Component
public class PwHistorySchedule {

    @Autowired
    private PwHistoryService service;

    // 매월 1일 0시 0분 0초
    @Scheduled(cron = "0 0 0 1 * *")
    public void deleteTrace() {
        service.deleteOldHistoriesForAllUsers();
    }
}
