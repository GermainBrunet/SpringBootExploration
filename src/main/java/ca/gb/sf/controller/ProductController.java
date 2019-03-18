package ca.gb.sf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import ca.gb.sf.models.Product;
import ca.gb.sf.models.ProductLowestPriceView;
import ca.gb.sf.models.ProductPriceForm;
import ca.gb.sf.models.ProductView;
import ca.gb.sf.repositories.ProductRepository;
import ca.gb.sf.repositories.ProductViewRepository;
import ca.gb.sf.service.ProductLowestPriceService;
import ca.gb.sf.util.PageWrapper;
import ca.gb.sf.web.form.SearchForm;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    private final ProductViewRepository productViewRepository;
    
    private final ProductLowestPriceService productLowestPriceService;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductViewRepository productViewRepository, ProductLowestPriceService productLowestPriceService) {
        this.productRepository = productRepository;
        this.productViewRepository = productViewRepository;
        this.productLowestPriceService = productLowestPriceService;
    }
	
    @GetMapping("/productList")
    public String productList(Model model) {
    	Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        // return "index";
        return "productList";
    }

    @GetMapping("/workPage")
    public String workPage(SearchForm searchForm, Model model) {

    	model.addAttribute("searchForm", searchForm);        
        
        return "workPage";
    	
    }
    
    @GetMapping("/productListNew")
    public String productListNew(@PageableDefault(size = 3) Pageable pageable, SearchForm searchForm, Model model) {
    	
    	System.out.println("Page Number: " + pageable.getPageNumber());
    	System.out.println("Page Size: " + pageable.getPageSize());
    	
    	String searchString = searchForm.getSearch();
    	
    	Page<ProductLowestPriceView> productPage = productLowestPriceService.findPaginated(pageable, searchString);
    	
    	PageWrapper<ProductLowestPriceView> products = new PageWrapper<ProductLowestPriceView> (productPage, "/productListNew");
    	
        model.addAttribute("page", products);

    	model.addAttribute("searchForm", searchForm);        
        
        return "productListNew";
    }
    
    @PostMapping("/searchNew")
    public RedirectView productSearch(@PageableDefault(size = 3) Pageable pageable, @ModelAttribute SearchForm searchForm) {
    	
    	System.out.println("Search = " + searchForm.getSearch());
    	
    	//pageable.
    	
    	// model.addAttribute("searchForm", new SearchForm());
    	
    	return new RedirectView("productListNew?search=" + searchForm.getSearch());
    	    	
    }
    
    @GetMapping("/productView/{id}")
    public String product(@PathVariable("id") long id, Model model) {
    	
    	Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
    	model.addAttribute("product", product);
    	
    	List<ProductView> productPrices = productViewRepository.findByProductId(id);
    	
    	model.addAttribute("prices", productPrices);

    	ProductPriceForm productPriceForm = new ProductPriceForm();
    	productPriceForm.setProductId(String.valueOf(id));
    	
    	model.addAttribute("productPriceForm", productPriceForm);
    	
        return "product";
    }
    

    
}
