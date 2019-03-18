package ca.gb.sf.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.gb.sf.SpringContextIntegrationTest;
import ca.gb.sf.repositories.ManufacturerRepository;
import ca.gb.sf.repositories.ProductPriceRepository;
import ca.gb.sf.repositories.ProductPriceSourceRepository;
import ca.gb.sf.repositories.ProductRepository;
import ca.gb.sf.repositories.WebSiteRepository;
import ca.gb.sf.util.UnitOfWork;

public class PrincipalServiceTest extends SpringContextIntegrationTest {

	@Autowired
	PrincipalService principalService;
	
	@Autowired
	ProductPriceRepository productPriceRepository;

	@Autowired
	ProductPriceSourceRepository productPriceSourceRepository;

	@Autowired
	WebSiteRepository webSiteRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	ManufacturerRepository manufacturerRepository;

	@Test
	public void principalServiceTest() {
		
		UnitOfWork unitOfWork = new UnitOfWork();
		
		unitOfWork.setManufacturerName("Dell");
		unitOfWork.setProductName("LATITUDE");
		unitOfWork.setProductNumber("E6520");
		unitOfWork.setSourceURL("https://www.staples.ca/en/dell-refurbished-e6520-latitude-15-6-inch-notebook-2-3-ghz-intel-core-i5-2410m-128-gb-ssd-8-gb-ddr3-windows-10-professional/product_2929496_1-CA_1_20001?akamai-feo=off");
		unitOfWork.setPrice(new Double("349.99"));		
		
		principalService.persist(unitOfWork);
		
		System.out.println(productPriceRepository.count());
		
	}
	
	@Test
	public void clearTables() {
		
		productPriceRepository.deleteAll();
		productPriceSourceRepository.deleteAll();
		webSiteRepository.deleteAll();
		productRepository.deleteAll();
		manufacturerRepository.deleteAll();
		
	}
	
	@Test
	public void displayTableCounts() {
		
		System.out.println(productPriceRepository.count());
		System.out.println(productPriceSourceRepository.count());
		System.out.println(webSiteRepository.count());
		System.out.println(productRepository.count());
		System.out.println(manufacturerRepository.count());
		
		
	}
	
	@Test
	public void addData() {

		List<UnitOfWork> unitsOfWork = new ArrayList<UnitOfWork>();
		
		long oneDay = 1000 * 60 * 60 * 24;
		
		Timestamp timestampOlder = new Timestamp(System.currentTimeMillis() - oneDay);
		Double priceIncreaseOlder = 1.1;
		
		Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
		Double priceIncreaseNow = 1.0;
		
		Timestamp timestampNewer = new Timestamp(System.currentTimeMillis() + oneDay);
		Double priceIncreaseNewer = 1.2;
		
		unitsOfWork.add(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console witch with Gray Joy-Con", "https://www.bestbuy.ca/en-ca/product/nintendo-switch-nintendo-switch-with-gray-joy-con-misc/10381161.aspx?", new Double("380.79")));
		unitsOfWork.add(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console witch with Gray Joy-Con", "https://www.walmart.ca/en/ip/nintendo-switch-console-with-grey-joy-con/6000196784701", new Double("380.21")));
		unitsOfWork.add(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console witch with Gray Joy-Con", "https://www.staples.ca/en/Nintendo-Switch-Console-with-Grey-Joy-Con-Controllers/product_2661398_1-CA_1_20001?CID=PS:GS:CA:b:aud-516347803146:dsa-378859729191::54049798957::1352059385&CampaignID=1352059385&gclid=CjwKCAiAnsnjBRB6EiwATkM1XpPK9OsjjWNNKclej1SIW87EY_0fIcwl9YKTPo5qJru32QxPBm_5DRoCGP8QAvD_BwE", new Double("379.99")));
		unitsOfWork.add(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console witch with Gray Joy-Con", "https://www.amazon.ca/Nintendo-Switch-Console-Grey-Edition/dp/B01LTHP2ZK/ref=pd_lpo_sbs_63_t_1?_encoding=UTF8&psc=1&refRID=X0XP999AY0BASVWCRX51", new Double("379.95")));
		unitsOfWork.add(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console witch with Gray Joy-Con", "https://www.ebgames.ca/Switch/Games/730033/nintendo-switch-with-gray-joy-con", new Double("379.99")));

		unitsOfWork.add(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console with Neon Red/Blue Joy-Con", "https://www.bestbuy.ca/en-ca/product/nintendo-switch-nintendo-switch-console-with-neon-red-blue-joy-con-misc/10381162.aspx?", new Double("380.79")));
		unitsOfWork.add(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console with Neon Red/Blue Joy-Con", "https://www.walmart.ca/en/ip/nintendo-switch-console-with-neon-blue-and-neon-red-joy-con/6000196784704", new Double("380.21")));
		unitsOfWork.add(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console with Neon Red/Blue Joy-Con", "https://www.staples.ca/en/Nintendo-Switch-Console-with-Neon-Red-Neon-Blue-Joy-Con-Controllers/product_2661399_1-CA_1_20001", new Double("379.99")));
		unitsOfWork.add(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console with Neon Red/Blue Joy-Con", "https://www.amazon.ca/Nintendo-Switch-Console-Neon-Edition/dp/B01MUAGZ49/ref=pd_lpo_sbs_63_t_0?_encoding=UTF8&psc=1&refRID=X0XP999AY0BASVWCRX51", new Double("379.96")));
		unitsOfWork.add(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console with Neon Red/Blue Joy-Con", "https://www.ebgames.ca/Switch/Games/730034/nintendo-switch-with-neon-blue-and-neon-red-joy-con", new Double("379.99")));

		unitsOfWork.add(new UnitOfWork("Apple", "MacBook Air 13\" MRE82LL/A", "Apple MRE82LL/A MacBook Air 13\" with Retina Display 1.6 GHz Intel Core i5, 128 GB, 8GB LPDDR3, macOS Mojave, Space Grey", "https://www.staples.ca/en/apple-mre82ll-a-macbook-air-13-with-retina-display-1-6-ghz-intel-core-i5-128-gb-8gb-lpddr3-macos-mojave-space-grey/product_2928463_1-CA_1_20001", new Double("1500.89")));
		unitsOfWork.add(new UnitOfWork("Apple", "MacBook Air 13\" MQD32C/A", "Apple MacBook Air MQD32C/A 13\", 1.8 GHz Intel Core i5, 128 GB SSD, 8 GB LPDDR3, MacOS Sierra, French", "https://www.staples.ca/en/Apple-MacBook-Air-MQD32C-A-13-1-8-GHz-Intel-Core-i5-128-GB-SSD-8-GB-LPDDR3-MacOS-Sierra-French/product_2727455_1-CA_1_20001", new Double("1200.89")));
		unitsOfWork.add(new UnitOfWork("Apple", "Apple MacBook MNYF2LL/A 12\"", "Apple MacBook MNYF2LL/A 12\", 1.2 GHz Intel Core m3, 256 GB SSD, 8 GB LPDDR3, MacOS Sierra, Space Grey", "https://www.staples.ca/en/Apple-MacBook-MNYF2LL-A-12-1-2-GHz-Intel-Core-m3-256-GB-SSD-8-GB-LPDDR3-MacOS-Sierra-Space-Grey/product_2727168_1-CA_1_20001", new Double("1730.89")));

		unitsOfWork.add(new UnitOfWork("Fisher Price", "Fisher Price Jaxon Island Crib in White and Natural", "Fisher Price Jaxon Island Crib in White and Natural", "https://www.costco.ca/Fisher-Price-Jaxon-Island-Crib-in-White-and-Natural.product.100378337.html", new Double("297.99")));
		unitsOfWork.add(new UnitOfWork("Convotherm", "Convotherm C4ED10.10GB LP", "Convotherm C4ED10.10GB Liquid Propane Half Size Combi Oven with easyDial Controls - 129,700 BTU", "https://www.webstaurantstore.com/convotherm-c4ed10-10gb-liquid-propane-half-size-combi-oven-with-easydial-controls-129-700-btu/390C4D101GBL.html", new Double("14133.00")));
		unitsOfWork.add(new UnitOfWork("Avantco", "Avantco SS-2R-G-HC", "Avantco SS-2R-G-HC 54\" Glass Door Reach-In Refrigerator", "https://www.webstaurantstore.com/avantco-ss-2r-g-hc-54-glass-door-reach-in-refrigerator/178SS2RGHC.html", new Double("2479.00")));

		
		for (UnitOfWork unitOfWork : unitsOfWork) {
			
			principalService.persist(unitOfWork);
			
		}
		
		principalService.persist(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console with Neon Red/Blue Joy-Con", "https://www.ebgames.ca/Switch/Games/730034/nintendo-switch-with-neon-blue-and-neon-red-joy-con", new Double("369.99")), timestampOlder);
		principalService.persist(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console with Neon Red/Blue Joy-Con", "https://www.ebgames.ca/Switch/Games/730034/nintendo-switch-with-neon-blue-and-neon-red-joy-con", new Double("359.99")), timestampNewer);
		principalService.persist(new UnitOfWork("Nintendo", "Switch", "Nintendo Switch Console with Neon Red/Blue Joy-Con", "https://www.ebgames.ca/Switch/Games/730034/nintendo-switch-with-neon-blue-and-neon-red-joy-con", new Double("370.99")), timestampNow);
		
	}
	
	
}
