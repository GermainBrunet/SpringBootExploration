package ca.gb.sf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ca.gb.sf.models.Manufacturer;
import ca.gb.sf.models.WebSite;
import ca.gb.sf.repositories.ManufacturerRepository;
import ca.gb.sf.repositories.WebSiteRepository;

@Service
public class WebSiteService {

	@Autowired
	WebSiteRepository webSiteRepo;
	
	/**
	 * Function that will return a manufacture or create one if one does not exist.  Will return anull if the manufacturer name is empty.
	 * 
	 * @param manufacturerName
	 * 
	 * @return
	 */
	public WebSite getWebSite(String url) {
		
		if (StringUtils.isEmpty(url)) {
		
			return null;
			
		}
		
		WebSite webSite = webSiteRepo.findByUrl(url);
		
		if (webSite != null) {
			
			return webSite;
			
		}
		
		webSite = new WebSite();
		
		webSite.setUrl(url);
		
		webSite = webSiteRepo.save(webSite);
		
		return webSite;
		
	}
	
	
}
