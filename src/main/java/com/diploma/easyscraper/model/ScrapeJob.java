package com.diploma.easyscraper.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name = "scrape_job")
public class ScrapeJob {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String urls;

    private String keywords;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ScrapeJob() {
    }

    public ScrapeJob(String name, User user, String urls, String keywords) {
        this.name = name;
        this.user = user;
        this.urls = urls;
        this.keywords = keywords;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "ScrapeJob{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", urls='" + urls + '\'' +
                ", keywords='" + keywords + '\'' +
                ", user=" + user +
                '}';
    }
}
