package com.deloitte.interview.icecream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.deloitte.interview.icecream.resource.Coins;
import com.deloitte.interview.icecream.resource.Flavour;
import com.deloitte.interview.icecream.resource.ServeIceCreamRequest;
import com.deloitte.interview.icecream.resource.ServeIceCreamResponse;
import com.deloitte.interview.icecream.resource.Status;
import com.deloitte.interview.icecream.service.ServeIceCreamService;

/**
 * @author Prudhvi
 *
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class IceCreamControllerTest {

	@Mock
	private ServeIceCreamService serveIceCreamService;
	
	@InjectMocks
	private IceCreamController iceCreamController;

	@Before
	public void doSetup() {
		MockitoAnnotations.initMocks(this);
	}
	//passing total amount as 41 cents and opting for Chocolate
	@Test
	public void testPostExample() throws Exception {
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
			
		assertEquals(response.getBody().getStatus().toString(), "SERVED");

	}
	
	@Test
	public void testPostExampleWithLessAmount() throws Exception {
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
		
		assertEquals(response.getBody().getStatus().toString(), "NOT_SERVED");

	}

}
