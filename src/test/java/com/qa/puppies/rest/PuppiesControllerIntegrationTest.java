package com.qa.puppies.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.puppies.domain.Puppies;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // loads the context
@AutoConfigureMockMvc
public class PuppiesControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;
//	{
//	    "name": "Nikko",
//	    "age": 3,
//	    "breed": "Chihuahua"
//	    }

	@Test
	void testcreate() throws Exception {
		// create a puppy
		Puppies newPup = new Puppies("Nikko", 3, "Chihuahua");
		// convert it to JSON string - instead of writing it out, e.g: RequestBuilder...
		// content("...")
		String newPupJSON = this.mapper.writeValueAsString(newPup);// add the throws declaration - in method name line
		// build mock request
		RequestBuilder mockRequest = post("/createPups").contentType(MediaType.APPLICATION_JSON).content(newPupJSON);

		// create "saved" puppy
		Puppies savedPup = new Puppies(1L, "Nikko", 3, "Chihuahua");
		// convert "saved" puppy to JSON
		String savedPupJSON = this.mapper.writeValueAsString(savedPup);

		// check status is 201 - CREATED
		ResultMatcher matchStatus = status().isCreated();
		// check that response body is correct puppy
		ResultMatcher matchBody = content().json(savedPupJSON);
		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);// add the throws declaration - in
																						// method name line

//		// DEMO purposes: can do all the above in one line of code like here:
//		this.mockMVC
//				.perform(post("/createPups").contentType(MediaType.APPLICATION_JSON)
//						.content(this.mapper.writeValueAsString(new Puppies("Nikko", 3, "Chihuahua"))))
//				.andExpect(status().isCreated())
//				.andExpect(content().json(this.mapper.writeValueAsString(new Puppies(1L, "Nikko", 3, "Chihuahua"))));
	}
}
