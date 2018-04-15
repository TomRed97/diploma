package com.diploma.easyscraper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EasyScraperController {

    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("name", "Veronika");

        return "home";
    }
}
