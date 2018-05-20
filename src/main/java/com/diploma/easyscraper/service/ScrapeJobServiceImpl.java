package com.diploma.easyscraper.service;

import com.diploma.easyscraper.interfaces.ScrapeJobRepository;
import com.diploma.easyscraper.interfaces.ScrapeJobService;
import com.diploma.easyscraper.model.ScrapeJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapeJobServiceImpl implements ScrapeJobService {

    @Autowired
    private ScrapeJobRepository scrapeJobRepository;

    @Override
    public void save(ScrapeJob scrapeJob) {
        scrapeJobRepository.save(scrapeJob);
    }

    @Override
    public List<ScrapeJob> findScrapeJobByUserId(Integer userId) {
        return scrapeJobRepository.findAllByUserId(userId);
    }

    @Override
    public void deleteScraperById(Integer jobId) {
        scrapeJobRepository.deleteById(jobId);
    }
}
