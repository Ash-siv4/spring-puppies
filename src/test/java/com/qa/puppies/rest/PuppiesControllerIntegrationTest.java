package com.qa.puppies.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.puppies.domain.Puppies;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // loads the context
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:puppies-schema.sql",
		"classpath:puppies-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD) // pre-populate db
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

	// RUN SCRIPTS -> RUN TEST -> RUN SCRIPTS -> RUN TEST -> REPEAT

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
		Puppies savedPup = new Puppies(2L, "Nikko", 3, "Chihuahua");
		// convert "saved" puppy to JSON
		String savedPupJSON = this.mapper.writeValueAsString(savedPup);

		// check status is 201 - CREATED
		ResultMatcher matchStatus = status().isCreated();
		// check that response body is correct puppy
		ResultMatcher matchBody = content().json(savedPupJSON);
		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);// add the throws declaration - in
																						// method name line

//		// FOR DEMO PURPOSES - can do all the above in one line of code like here:
//		this.mockMVC
//				.perform(post("/createPups").contentType(MediaType.APPLICATION_JSON)
//						.content(this.mapper.writeValueAsString(new Puppies("Nikko", 3, "Chihuahua"))))
//				.andExpect(status().isCreated())
//				.andExpect(content().json(this.mapper.writeValueAsString(new Puppies(1L, "Nikko", 3, "Chihuahua"))));
	}

	@Test
	void readTest() throws Exception {
		// [{puppies}] - expecting an array
		Puppies readPup = new Puppies(1L, "Peppy", 4, "Chihuahua");
		// can do this instead for post Java 9 versions
		List<Puppies> allPups = List.of(readPup);
//		// for Java 9 or before versions
//		List<Puppies> allPups = new ArrayList<>();
//		allPups.add(readPup);
		String readPupJSON = this.mapper.writeValueAsString(allPups);
		RequestBuilder mockRequest = get("/getPuppies");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(readPupJSON);

		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void readOneTest() throws Exception {
		Puppies readAPup = new Puppies(1L, "Peppy", 4, "Chihuahua");
		String readAPupJSON = this.mapper.writeValueAsString(readAPup);
		
		Long idIn = 1L;

		RequestBuilder mockRequest = get("/getPup/" + idIn);

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(readAPupJSON);

		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void updateTest() throws Exception {
		Puppies updatePup = new Puppies("Lucky", 13, "Jack Russell");
		String updatePupJSON = this.mapper.writeValueAsString(updatePup);
		Long id4URL = 1L;

		RequestBuilder mockRequest = put("/updatePup/" + id4URL).contentType(MediaType.APPLICATION_JSON)
				.content(updatePupJSON);

		Puppies retUpdatedPup = new Puppies(1L, "Lucky", 13, "Jack Russell");
		String retUpdatedPupJSON = this.mapper.writeValueAsString(retUpdatedPup);

		ResultMatcher retStatus = status().isOk();
		ResultMatcher retBody = content().json(retUpdatedPupJSON);

		this.mockMVC.perform(mockRequest).andExpect(retStatus).andExpect(retBody);
	}

	@Test
	void deleteTest() throws Exception {
		Long remId = 1L;
		RequestBuilder mockRequest = delete("/removePup/" + remId);
		ResultMatcher Status = status().isOk();
		ResultMatcher Body = content().string("false");

		this.mockMVC.perform(mockRequest).andExpect(Status).andExpect(Body);
	}

}
