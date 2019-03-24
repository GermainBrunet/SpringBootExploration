package ca.gb.sf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.gb.sf.web.form.SearchForm;

@Controller
public class HomeController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    
    @GetMapping("/legal")
    public String legal() {
        return "legal";
    }

    @GetMapping("/login")
    public String login(SearchForm searchForm, Model model) {

    	model.addAttribute("searchForm", new SearchForm());        
    	
        return "login";
    }
    
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

}
