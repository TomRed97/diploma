package com.diploma.easyscraper;

import com.diploma.easyscraper.model.ScrapeJob;
import com.diploma.easyscraper.service.WebScraperService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EasyScraperController {

    @GetMapping("/home")
    public String getHomePage(Model model) {

        return "home";
    }

    @GetMapping("/newScraper")
    public String createNewScraper(Model model) {


        return "newScraperForm";
    }

    @GetMapping("/view")
    public String viewScraper(Model model) {
        ScrapeJob scrapeJob = new ScrapeJob(1, "My Scraper");
        ScrapeJob scrapeJob1 = new ScrapeJob(2, "My Scraper 2");
        List<ScrapeJob> list = new ArrayList<>();

        list.add(scrapeJob);
        list.add(scrapeJob1);

        model.addAttribute("scrapers", list);

        return "viewScrapers";
    }

    @GetMapping("scraper/{id}")
    public String getScraper(@PathVariable int id, Model model) throws IOException {
        model.addAttribute("body", WebScraperService.doScraping());
        return "scraper";
    }
}
