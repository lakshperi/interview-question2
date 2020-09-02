package com.example.demo.storeretrieve;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;


import com.example.demo.DemoApplication;

@SpringBootTest(classes=DemoApplication.class)
@Tag("integration-test")
@AutoConfigureMockMvc
class StoreAndRetrieveControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	String testString1="7,6,5,4,2,3";
	String testString2="76";
	String testString3="46,43,21,29,23,22,12,28,23,102,21,10000";
	
	@Test
	void checkPermutationNotFoundException() throws Exception{
		
		MvcResult mvcResult= mockMvc.perform(get("/permutation?id=10")).andReturn();
		assertEquals("Id not found Exception", mvcResult.getResolvedException().getMessage());
		assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	void checkStoreBadRequestException() throws Exception{
		
		MvcResult mvcResult= mockMvc.perform(get("/store?numbers=ABCD")).andReturn();
		assertEquals("Input Validation Failed- Only numbers are allowed", mvcResult.getResolvedException().getMessage());
		assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
	}
	

	
	@Test
	void storeAndRetrieveTestForSize12() throws Exception {

		this.mockMvc.perform(get("/store?numbers=" + testString3)).andDo(print()).andExpect(status().isOk())
				.andExpect(new ResultMatcher() {

					@Override
					public void match(MvcResult result) throws Exception {
						assertEquals("2", result.getResponse().getContentAsString());
					}

				});

		this.mockMvc.perform(get("/permutation?id=2")).andDo(print()).andExpect(status().isOk())
				.andExpect(new ResultMatcher() {

					@Override
					public void match(MvcResult result) throws Exception {

						String response = result.getResponse().getContentAsString();
						String[] responseArray = response.split(",");

						String[] testStringArray = testString3.split(",");
						List<String> stringNumberList = Arrays.asList(testStringArray);

						for (String s : responseArray) {
							if (stringNumberList.contains(s)) {
								continue;
							} else {
								fail("Test Failed: Shuffled Response not same as Test String");
							}
						}

					}

				});
	}
	
	@Test
	void storeAndRetrieveTestForSize6() throws Exception {

		this.mockMvc.perform(get("/store?numbers=" + testString1)).andDo(print()).andExpect(status().isOk())
				.andExpect(new ResultMatcher() {

					@Override
					public void match(MvcResult result) throws Exception {
						assertEquals("1", result.getResponse().getContentAsString());
					}

				});

		this.mockMvc.perform(get("/permutation?id=1")).andDo(print()).andExpect(status().isOk())
				.andExpect(new ResultMatcher() {

					@Override
					public void match(MvcResult result) throws Exception {

						String response = result.getResponse().getContentAsString();
						String[] responseArray = response.split(",");

						String[] testStringArray = testString1.split(",");
						List<String> stringNumberList = Arrays.asList(testStringArray);

						for (String s : responseArray) {
							if (stringNumberList.contains(s)) {
								continue;
							} else {
								fail("Test Failed: Shuffled Response not same as Test String");
							}
						}

					}

				});
	}
	
	@Test
	void storeAndRetrieveTestForSize1() throws Exception {

		this.mockMvc.perform(get("/store?numbers=" + testString2)).andDo(print()).andExpect(status().isOk())
				.andExpect(new ResultMatcher() {

					@Override
					public void match(MvcResult result) throws Exception {
						assertEquals("0", result.getResponse().getContentAsString());
					}

				});

		this.mockMvc.perform(get("/permutation?id=0")).andDo(print()).andExpect(status().isOk())
				.andExpect(new ResultMatcher() {

					@Override
					public void match(MvcResult result) throws Exception {

						String response = result.getResponse().getContentAsString();
						String[] responseArray = response.split(",");

						String[] testStringArray = testString2.split(",");
						List<String> stringNumberList = Arrays.asList(testStringArray);

						for (String s : responseArray) {
							if (stringNumberList.contains(s)) {
								continue;
							} else {
								fail("Test Failed: Shuffled Response not same as Test String");
							}
						}

					}

				});
	}

}
