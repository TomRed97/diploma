package com.diploma.easyscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class EasyScraperController {

    @GetMapping("/home")
    public String getHomePage(Model model) throws IOException {
        Document doc = Jsoup.connect("http://en.wikipedia.org/").get();

        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {

                    headline.attr("title");
                    headline.absUrl("href");
        }
        model.addAttribute("name", doc.title());

        return "home";
    }
}
