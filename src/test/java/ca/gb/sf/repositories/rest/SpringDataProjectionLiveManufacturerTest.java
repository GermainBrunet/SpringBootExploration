package ca.gb.sf.repositories.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

import java.util.Optional;

// import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gb.sf.Start;
import ca.gb.sf.models.Manufacturer;
import ca.gb.sf.repositories.ManufacturerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Start.class, webEnvironment = WebEnvironment.DEFINED_PORT)

public class SpringDataProjectionLiveManufacturerTest {
    private static final String MANUFACTURER_ENDPOINT = "http://localhost:8080/manufacturers";

    @Autowired
    private ManufacturerRepository manufacturerRepo;

    @Before
    public void setup() {
    	
    	Optional<Manufacturer> optionalManufacturer = manufacturerRepo.findById(1L);
    	
    	if (!optionalManufacturer.isPresent()) {
    		Manufacturer manufacturer = new Manufacturer("manufacturer1");
    		manufacturer = manufacturerRepo.save(manufacturer);
        }
    }

    @Test
    public void whenGetManufacturer_thenOK() {
        final Response response = RestAssured.get(MANUFACTURER_ENDPOINT + "/1");

        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("name"));
        assertFalse(response.asString().contains("userCount"));

        System.out.println(response.prettyPrint());

    }

    @Test
    public void whenPostManufacturer_thenOK() {

    	// Create a record.
    	
    	RestAssured.baseURI = MANUFACTURER_ENDPOINT;
    	RequestSpecification request = RestAssured.given();
    	
    	JSONObject requestParams = new JSONObject();
    	requestParams.put("name", "manufacturer2"); 
    	
    	request.header("Content-Type", "application/json");
    	request.body(requestParams.toJSONString());
    	 
    	final Response createResponse = request.post();

        assertEquals(201, createResponse.getStatusCode());
        assertTrue(createResponse.asString().contains("name"));
        assertFalse(createResponse.asString().contains("userCount"));

    	// Find that record.
    	
    	final Response response = RestAssured.get(MANUFACTURER_ENDPOINT + "/search/findByName?name=manufacturer2");

        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("name"));
        assertFalse(response.asString().contains("userCount"));
    	
        assertEquals(200, response.getStatusCode());

        System.out.println(response.prettyPrint());

        // Update that record.
        
        
        
        
    }
    
}
