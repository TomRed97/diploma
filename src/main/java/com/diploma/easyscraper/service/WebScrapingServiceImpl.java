package com.diploma.easyscraper.service;

import com.diploma.easyscraper.interfaces.MailService;
import com.diploma.easyscraper.interfaces.SpreadsheetService;
import com.diploma.easyscraper.interfaces.WebScrapingService;
import com.diploma.easyscraper.model.ScrapeJob;
import com.diploma.easyscraper.model.User;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WebScrapingServiceImpl implements WebScrapingService {

    @Autowired
    private MailService mailService;

    @Autowired
    private SpreadsheetService spreadsheetService;

    @Override
    @Async
    public void scrape(ScrapeJob scrapeJob) throws IOException, MessagingException {
        String[] keywords = scrapeJob.getKeywords().split(";");
        List<List<String>> allData = new ArrayList<>();
        allData.add(Arrays.asList("Name", "URL", "Price"));

        for (int i = 0; i < keywords.length; i++) {
            allData.addAll(doScraping(keywords[i]));
        }

        String fileName = spreadsheetService.createSpreadsheet(allData);
        notifyUser(scrapeJob, fileName);
    }

    @Override
    public void notifyUser(ScrapeJob scrapeJob, String fileName) throws MessagingException {
        User user = scrapeJob.getUser();
        String content = "Dear " + user.getLogin() + ", \n";
        content += "You can see results from " + scrapeJob.getName() + " job.";

        mailService.send(user.getEmail(), content, fileName);
    }

    public List<List<String>> doScraping(String keyword) throws IOException {

        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        HtmlPage htmlPage = webClient.getPage("https://www.amazon.com/");
        HtmlForm htmlForm = htmlPage.getFormByName("site-search");
        HtmlTextInput htmlTextInput = htmlForm.getInputByName("field-keywords");
        HtmlSubmitInput htmlSubmitInput = htmlForm.getInputByValue("Go");
        htmlTextInput.setValueAttribute(keyword);
        HtmlPage htmlPage1 = htmlSubmitInput.click();

        Document doc = Jsoup.parse(htmlPage1.asXml());
        Elements elements = doc.getElementsByAttributeValue("id", "s-results-list-atf");

        webClient.close();

        Elements orderedList = elements.get(0).getElementsByTag("li");

        List<List<String>> data = new ArrayList<>();


        orderedList.forEach((element -> {
            List<String> rowData = new ArrayList<>();
            if (element.getElementsByAttribute("data-attribute").size() != 0) {
                rowData.add(element.getElementsByAttribute("data-attribute").get(0).attr("data-attribute"));

                rowData.add("https://www.amazon.com/");

                if (element.getElementsByClass("sx-price").text().trim().isEmpty()) {
                    rowData.add(element.getElementsByClass("a-size-base").text());
                } else {
                    String priceLabel = element.getElementsByClass("sx-price").text().replace(" ", ".");
                    priceLabel = priceLabel.replaceFirst("\\.", "");
                    rowData.add(priceLabel);

                }
            }

            data.add(rowData);

        }));

        return data;
    }
}
