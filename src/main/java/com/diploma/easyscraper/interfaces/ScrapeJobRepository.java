package com.diploma.easyscraper.interfaces;

import com.diploma.easyscraper.model.ScrapeJob;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScrapeJobRepository extends CrudRepository<ScrapeJob, Integer> {
    List<ScrapeJob> findAllByUserId(Integer userId);
}
