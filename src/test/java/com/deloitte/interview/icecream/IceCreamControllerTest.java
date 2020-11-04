package com.deloitte.interview.icecream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.deloitte.interview.icecream.resource.*;
import com.deloitte.interview.icecream.service.ServeIceCreamService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class IceCreamControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ServeIceCreamService serveIceCreamService;
	
	@InjectMocks
	@Autowired
	private IceCreamController iceCreamController;
	
	private static ObjectMapper mapper = new ObjectMapper();

	@Before
	public void doSetup() {
		MockitoAnnotations.initMocks(this);
	}
	//passing total amount as 41 cents and opting for Chocolate
	@Test
	public void testPostExample() throws Exception {
		String uri = "/serveIceCream";
		ServeIceCreamResponse serveIceCreamResponse = new ServeIceCreamResponse();
		List<Coins> inputCoinsList = new ArrayList<>();
		inputCoinsList.add(Coins.NICKLE);
		inputCoinsList.add(Coins.NICKLE);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.CENT);
		ServeIceCreamRequest serveIceCreamRequest = new ServeIceCreamRequest(inputCoinsList,Flavour.CHOCOLATE);
		serveIceCreamResponse.setStatus(Status.SERVED);
		List<Coins> change = new ArrayList<>();
		change.add(Coins.CENT);
		serveIceCreamResponse.setChange(change);
		Mockito.when(serveIceCreamService.serveIceCream(Mockito.any(ServeIceCreamRequest.class))).thenReturn(serveIceCreamResponse);
		ResponseEntity<ServeIceCreamResponse> response = iceCreamController.serveIceCream(serveIceCreamRequest);
			
		/*
		 * String json = mapper.writeValueAsString(serveIceCreamRequest); String content
		 * = mockMvc .perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.
		 * APPLICATION_JSON_VALUE).content(json))
		 * .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		 */

		//final ServeIceCreamResponse response = mapper.readValue(content, ServeIceCreamResponse.class);
		assertEquals(response.getBody().getStatus().toString(), "SERVED");

	}
	
	@Test
	public void testPostExampleWithLessAmount() throws Exception {
		String uri = "/serveIceCream";
		ServeIceCreamResponse serveIceCreamResponse = new ServeIceCreamResponse();
		List<Coins> inputCoinsList = new ArrayList<>();
		inputCoinsList.add(Coins.NICKLE);
		inputCoinsList.add(Coins.NICKLE);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.DIME);
		ServeIceCreamRequest serveIceCreamRequest = new ServeIceCreamRequest(inputCoinsList,Flavour.CHOCOLATE);
		serveIceCreamResponse.setStatus(Status.NOT_SERVED);
		serveIceCreamResponse.setChange(inputCoinsList);
		Mockito.when(serveIceCreamService.serveIceCream(Mockito.any(ServeIceCreamRequest.class))).thenReturn(serveIceCreamResponse);
		ResponseEntity<ServeIceCreamResponse> response = iceCreamController.serveIceCream(serveIceCreamRequest);
			
		/*
		 * String json = mapper.writeValueAsString(serveIceCreamRequest); String content
		 * = mockMvc .perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.
		 * APPLICATION_JSON_VALUE).content(json))
		 * .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		 */

		//final ServeIceCreamResponse response = mapper.readValue(content, ServeIceCreamResponse.class);
		assertEquals(response.getBody().getStatus().toString(), "NOT_SERVED");

	}

}
