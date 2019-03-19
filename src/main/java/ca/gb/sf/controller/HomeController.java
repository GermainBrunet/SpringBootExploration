package ca.gb.sf.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.gb.sf.models.ProductLowestPriceView;
import ca.gb.sf.util.PageWrapper;
import ca.gb.sf.web.form.SearchForm;

@Controller
public class HomeController {

    @GetMapping("/")
    public String root() {
        // return "index";
        return "productListNew";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
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
