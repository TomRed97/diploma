package com.diploma.easyscraper.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component()
public class ScrapeJob {

    private int id;

    private String name;

    private LocalDate scheduleTime;

    public ScrapeJob() {
    }

    public ScrapeJob(int id, String name) {
        this.id = id;
        this.name = name;
//        this.scheduleTime = scheduleTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalDate scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}
