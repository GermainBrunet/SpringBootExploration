package ca.gb.sf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.gb.sf.repositories.ManufacturerRepository;
import ca.gb.sf.repositories.ProductPriceRepository;
import ca.gb.sf.repositories.ProductPriceSourceRepository;
import ca.gb.sf.repositories.ProductRepository;
import ca.gb.sf.repositories.WebSiteRepository;
import ca.gb.sf.util.UnitOfWork;

@RestController
public class PrincipalController {

	@Autowired
	ManufacturerRepository manufacturerRepo;

	@Autowired
	ProductRepository productRepo;

	@Autowired
	WebSiteRepository webSiteRepo;

	@Autowired
	ProductPriceSourceRepository productPriceSourceRepo;

	@Autowired
	ProductPriceRepository productPriceRepo;
	
	@RequestMapping(value="/create", 
			method={ RequestMethod.POST },
			consumes={ "application/json" },
			produces={ "application/xml" })
	@ResponseBody
    public void create(@RequestBody UnitOfWork unitOfWork) {
		
		System.out.println(unitOfWork);
		
		
		
	}

	
}
