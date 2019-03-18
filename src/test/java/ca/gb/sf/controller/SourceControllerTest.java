package ca.gb.sf.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.util.UnitOfWork;

public class SourceControllerTest extends SpringContextIntegrationTest {

	@Autowired
	PrincipalController sourceController;
	
	@Test
	public void uploadSource1Test() {
		
		UnitOfWork unitOfWork = new UnitOfWork();
		unitOfWork.setManufacturerName("DELL");
		unitOfWork.setProductName("Precision Laptop");
		unitOfWork.setProductNumber("4800");
		unitOfWork.setSourceURL("https://www.staples.ca/en/dell-refurbished-e6520-latitude-15-6-inch-notebook-2-3-ghz-intel-core-i5-2410m-128-gb-ssd-8-gb-ddr3-windows-10-professional/product_2929496_1-CA_1_20001");
		unitOfWork.setPrice(new Double(349.99));
	
		sourceController.create(unitOfWork);
		
		
	}
	
	
	
}
