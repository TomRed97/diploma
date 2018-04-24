package com.diploma.easyscraper.model;

import org.springframework.stereotype.Component;

@Component()
public class ScrapeUrl {
     private int id;

     private String url;

     private int scrapeJobId;

     private String result;

    public ScrapeUrl() {
    }

    public ScrapeUrl(int id, String url, int scrapeJobId, String result) {
        this.id = id;
        this.url = url;
        this.scrapeJobId = scrapeJobId;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getScrapeJobId() {
        return scrapeJobId;
    }

    public void setScrapeJobId(int scrapeJobId) {
        this.scrapeJobId = scrapeJobId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
