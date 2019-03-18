package ca.gb.sf.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ca.gb.sf.models.Manufacturer;
import ca.gb.sf.models.ProductLowestPriceView;
import ca.gb.sf.models.ProductPrice;
import ca.gb.sf.models.ProductPriceSource;
import ca.gb.sf.models.WebSite;
import ca.gb.sf.repositories.ManufacturerRepository;
import ca.gb.sf.repositories.ProductLowestPriceViewRepository;
import ca.gb.sf.repositories.ProductPriceRepository;
import ca.gb.sf.repositories.WebSiteRepository;

@Service
public class ProductLowestPriceService {

	@Autowired
	ProductLowestPriceViewRepository productLowestPriceViewRepository;
	
	private static final String PERCENTAGE = "%";
	
	public Page<ProductLowestPriceView> findPaginated(Pageable pageable, String searchString) {

		Page<ProductLowestPriceView> products = null;
		
		if (StringUtils.isEmpty(searchString)) {
			
			products = productLowestPriceViewRepository.findAll(pageable);
			
		} else {
			
			StringBuilder sb = new StringBuilder();
			sb.append(PERCENTAGE).append(searchString.toLowerCase()).append(PERCENTAGE);
			
			products = productLowestPriceViewRepository.searchByName(pageable, sb.toString());
			
		}
		
		return products;
		
	}	
		
}
