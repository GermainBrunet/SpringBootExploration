package ca.gb.sf.repositories.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import ca.gb.sf.Start;
import ca.gb.sf.models.Product;
import ca.gb.sf.repositories.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Start.class, webEnvironment = WebEnvironment.DEFINED_PORT)

public class SpringDataProjectionLiveProductTest {
    private static final String PRODUCT_ENDPOINT = "http://localhost:8080/products";

    @Autowired
    private ProductRepository productRepo;

    @Before
    public void setup() {
    	
    	Optional<Product> optionalProduct = productRepo.findById(1L);
    	
    	if (!optionalProduct.isPresent()) {
    		Product product = new Product("product1");
    		product.setModelNumber("abc123");
    		product = productRepo.save(product);
        }
    }

    @Test
    public void whenGetUser_thenOK() {
        final Response response = RestAssured.get(PRODUCT_ENDPOINT + "/1");

        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("name"));
        assertFalse(response.asString().contains("userCount"));

        System.out.println(response.prettyPrint());

    }
    
    @Test
    public void createProductWithManufacturer() {
    	
    	
    	
    }

}
