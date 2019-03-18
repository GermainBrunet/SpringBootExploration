package ca.gb.sf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ca.gb.sf.models.Manufacturer;
import ca.gb.sf.repositories.ManufacturerRepository;

@Service
public class ManufacturerService {

	@Autowired
	ManufacturerRepository manufacturerRepo;
	
	/**
	 * Function that will return a manufacture or create one if one does not exist.  Will return anull if the manufacturer name is empty.
	 * 
	 * @param manufacturerName
	 * 
	 * @return
	 */
	public Manufacturer getManufacturer(String manufacturerName) {
		
		if (StringUtils.isEmpty(manufacturerName)) {
		
			return null;
			
		}
		
		Manufacturer manufacturer = manufacturerRepo.findByName(manufacturerName);
		
		if (manufacturer != null) {
			
			return manufacturer;
			
		}
		
		manufacturer = new Manufacturer();
		
		manufacturer.setName(manufacturerName);
		
		manufacturer = manufacturerRepo.save(manufacturer);
		
		return manufacturer;
		
	}
	
	
}
