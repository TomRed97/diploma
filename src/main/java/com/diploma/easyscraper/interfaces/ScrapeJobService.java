package com.diploma.easyscraper.interfaces;

import com.diploma.easyscraper.model.ScrapeJob;

import java.util.List;

public interface ScrapeJobService {
    void save(ScrapeJob scrapeJob);

    List<ScrapeJob> findScrapeJobByUserId(Integer userId);

    void deleteScraperById(Integer jobId);
}
