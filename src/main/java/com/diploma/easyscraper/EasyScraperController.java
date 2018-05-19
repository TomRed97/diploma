package com.diploma.easyscraper;

import com.diploma.easyscraper.interfaces.MailService;
import com.diploma.easyscraper.interfaces.UserService;
import com.diploma.easyscraper.model.ScrapeJob;
import com.diploma.easyscraper.interfaces.UserRepository;
import com.diploma.easyscraper.model.User;
import com.diploma.easyscraper.service.UserServiceImpl;
import com.diploma.easyscraper.service.WebScraperService;
import org.apache.xpath.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EasyScraperController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping("/home")
    public String getHomePage(Model model) {

        Boolean isLoggedIn = !(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"));
        model.addAttribute("isLoggedIn", isLoggedIn);

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


//        mailService.send("taprikyan@gmail.com", "Barev");

        list.add(scrapeJob);
        list.add(scrapeJob1);

        model.addAttribute("scrapers", list);

        return "viewScrapers";
    }

    @GetMapping("/scraper/{id}")
    public String getScraper(@PathVariable int id, Model model) throws IOException {
        model.addAttribute("body", WebScraperService.doScraping());
        return "scraper";
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {

        model.addAttribute("user", new User());

        return "loginForm";
    }

    @PostMapping("/welcome")
    public String welcome() {

        return "welcome";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {

        model.addAttribute("user", new User());

        return "registerForm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user) {

        userService.save(user);

        return "redirect: login";
    }

}
