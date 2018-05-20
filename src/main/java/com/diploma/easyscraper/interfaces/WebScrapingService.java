package com.diploma.easyscraper.interfaces;

import com.diploma.easyscraper.model.ScrapeJob;

import javax.mail.MessagingException;
import java.io.IOException;

public interface WebScrapingService {

    void scrape(ScrapeJob scrapeJob) throws IOException, MessagingException;

    void notifyUser(ScrapeJob scrapeJob, String fileName) throws MessagingException;
}
