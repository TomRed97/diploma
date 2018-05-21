package com.diploma.easyscraper.scheduling;

import com.diploma.easyscraper.interfaces.WebScrapingService;
import com.diploma.easyscraper.model.ScrapeJob;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;

public class RunnableScrapeJob implements Runnable {

    private WebScrapingService webScrapingService;

    private ScrapeJob scrapeJob;

    public RunnableScrapeJob(ScrapeJob scrapeJob, WebScrapingService webScrapingService) {
        this.scrapeJob = scrapeJob;
        this.webScrapingService = webScrapingService;
    }

    @Override
    public void run() {

        try {
            webScrapingService.scrape(scrapeJob);
            System.out.println(LocalDateTime.now() + "----- Scraping Job Scheduler ----- Job: " + scrapeJob.getId());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
