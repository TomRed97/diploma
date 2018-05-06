package com.diploma.easyscraper.service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class WebScraperService {

    public static String doScraping() throws IOException {
//        Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        HtmlPage htmlPage = webClient.getPage("https://www.amazon.com/");
        HtmlForm htmlForm = htmlPage.getFormByName("site-search");
        HtmlTextInput htmlTextInput = htmlForm.getInputByName("field-keywords");
        HtmlSubmitInput htmlSubmitInput = htmlForm.getInputByValue("Go");
        htmlTextInput.setValueAttribute("samsung galaxy tab s3");
        HtmlPage htmlPage1 = htmlSubmitInput.click();

        Document doc = Jsoup.parse(htmlPage1.asXml());
        Elements elements = doc.getElementsByAttributeValue("id", "s-results-list-atf");

        Element element =  elements.get(0);

        webClient.close();

        return element.html();

//        Elements newsHeadlines = doc.select("#mp-itn b a");
//        for (Element headline : newsHeadlines) {
//
//            System.out.println(headline.attr("title"));
//            System.out.println(headline.absUrl("href"));
//        }
//        System.out.println(doc.wholeText());
    }
}
