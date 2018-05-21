package com.diploma.easyscraper.service;

import com.diploma.easyscraper.interfaces.SchedulerService;
import com.diploma.easyscraper.interfaces.WebScrapingService;
import com.diploma.easyscraper.model.ScrapeJob;
import com.diploma.easyscraper.scheduling.RunnableScrapeJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private Map<Integer, ScheduledFuture> tasks = new HashMap<>();

    @Autowired
    private WebScrapingService webScrapingService;

    @Autowired
    public ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Async
    @Override
    public void schedule(ScrapeJob scrapeJob) {
        CronTrigger cronTrigger = new CronTrigger("0 0/5 * * * *");
        RunnableScrapeJob runnableScrapeJob = new RunnableScrapeJob(scrapeJob, webScrapingService);
        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(runnableScrapeJob, cronTrigger);

        tasks.put(scrapeJob.getId(), scheduledFuture);

    }

    @Async
    @Override
    public void deleteTask(Integer id) {
        tasks.get(id).cancel(true);
    }
}
