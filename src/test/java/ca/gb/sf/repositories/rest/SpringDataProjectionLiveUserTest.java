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
import ca.gb.sf.models.User;
import ca.gb.sf.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Start.class, webEnvironment = WebEnvironment.DEFINED_PORT)

public class SpringDataProjectionLiveUserTest {
    private static final String USER_ENDPOINT = "http://localhost:8080/users";

    @Autowired
    private UserRepository userRepo;

    @Before
    public void setup() {
    	
    	Optional<User> user1 = userRepo.findById(1L);
    	
        // if (userRepo.findById(1L) == null) {
    	if (!user1.isPresent()) {
            User user = new User();
            user.setDisplayName("displayName");
            user.setEmail("abc@abc.abc");
            user = userRepo.save(user);
        }
    }

    @Test
    public void whenGetUser_thenOK() {
        final Response response = RestAssured.get(USER_ENDPOINT + "/1");
        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("name"));
        assertFalse(response.asString().contains("userCount"));
    }

}
