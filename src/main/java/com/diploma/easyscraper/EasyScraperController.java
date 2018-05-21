package com.diploma.easyscraper;

import com.diploma.easyscraper.interfaces.SchedulerService;
import com.diploma.easyscraper.interfaces.ScrapeJobService;
import com.diploma.easyscraper.interfaces.UserService;
import com.diploma.easyscraper.model.ScrapeJob;
import com.diploma.easyscraper.model.User;
import com.diploma.easyscraper.service.SchedulerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class EasyScraperController {

    @Autowired
    private UserService userService;

    @Autowired
    private ScrapeJobService scrapeJobService;

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping("/home")
    public String getHomePage(Model model) {

        Boolean isLoggedIn = !(SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"));
        model.addAttribute("isLoggedIn", isLoggedIn);

        return "home";
    }

    @GetMapping("/newScraper")
    public String getNewScraperForm(Model model) {
        model.addAttribute("scrapeJob", new ScrapeJob());

        return "newScraperForm";
    }

    @PostMapping("/newScraper")
    public String createNewScraper(@ModelAttribute("scrapeJob") ScrapeJob scrapeJob, Principal principal) throws IOException, MessagingException {

        scrapeJob.setUser(userService.findUserByLogin(principal.getName()).get());
        scrapeJobService.save(scrapeJob);

        schedulerService.schedule(scrapeJob);

        return "redirect:view";
    }

    @GetMapping("/view")
    public String viewScraper(Model model, Principal principal) {

        List<ScrapeJob> list = scrapeJobService.findScrapeJobByUserId(userService.findUserByLogin(principal.getName()).get().getId());

        model.addAttribute("scrapers", list);

        return "viewScrapers";
    }

    @GetMapping("/delete/{id}")
    public String deleteScraper(@PathVariable int id) {

        scrapeJobService.deleteScraperById(id);
        schedulerService.deleteTask(id);

        return "deletedScraper";
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

        return "redirect:login";
    }

}
