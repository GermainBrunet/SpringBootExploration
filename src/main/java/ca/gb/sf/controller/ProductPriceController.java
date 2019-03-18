package ca.gb.sf.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import ca.gb.sf.models.Product;
import ca.gb.sf.models.ProductPriceForm;
import ca.gb.sf.models.ProductView;
import ca.gb.sf.repositories.ProductRepository;
import ca.gb.sf.repositories.ProductViewRepository;
import ca.gb.sf.service.PrincipalService;

@Controller
public class ProductPriceController {

    private final ProductRepository productRepository;

    private final ProductViewRepository productViewRepository;
    
    private final ProductController productController;
    
    private final PrincipalController principalController;
    
    private final PrincipalService principalService;

    @Autowired
    public ProductPriceController(ProductRepository productRepository, ProductViewRepository productViewRepository, ProductController productController, PrincipalController principalController, PrincipalService principalService) {
        this.productRepository = productRepository;
        this.productViewRepository = productViewRepository;
        this.productController = productController;
        this.principalController = principalController;
        this.principalService = principalService;
    }
	
    @PostMapping("/addproductprice")
    public RedirectView saveProductPrice(@Valid ProductPriceForm productPriceForm, BindingResult result, Model model) {
    	
    	System.out.println(productPriceForm);
    	
    	Long id = Long.valueOf(productPriceForm.getProductId());
    	
    	principalService.saveWebSiteReference(productPriceForm.getProductId(), productPriceForm.getUrl(), productPriceForm.getPrice());
    	
    	return new RedirectView("productView/" + id);
    	
    }
    
    
}
