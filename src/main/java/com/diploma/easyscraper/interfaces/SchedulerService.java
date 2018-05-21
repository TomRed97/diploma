package com.diploma.easyscraper.interfaces;

import com.diploma.easyscraper.model.ScrapeJob;

public interface SchedulerService {
    void schedule(ScrapeJob scrapeJob);

    void deleteTask(Integer id);
}
