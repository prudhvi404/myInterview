package com.deloitte.interview.icecream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

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
public class ServeIceCreamServiceTest {

	@Before
	public void doSetup() {
		MockitoAnnotations.initMocks(this);
	}
	// Served scenario - amount 41 cents and cost of chocolate is 40
	@Test
	public void testServeIceCream(){
		ServeIceCreamService serveIceCreamService = new ServeIceCreamService();
		ServeIceCreamResponse serveIceCreamResponse = new ServeIceCreamResponse();
		List<Coins> inputCoinsList = new ArrayList<>();
		inputCoinsList.add(Coins.NICKLE);
		inputCoinsList.add(Coins.NICKLE);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.CENT);
		ServeIceCreamRequest serveIceCreamRequest = new ServeIceCreamRequest(inputCoinsList,Flavour.CHOCOLATE);
		serveIceCreamResponse = serveIceCreamService.serveIceCream(serveIceCreamRequest);
		assertEquals(serveIceCreamResponse.getStatus().name(), Status.SERVED.name());
		assertEquals(serveIceCreamResponse.getChange().get(0), Coins.CENT);
	}
	//Not served scenario - amount 41 cents and cost of strawberry is 50
	@Test
	public void testServeStrawberryIceCream(){
		ServeIceCreamService serveIceCreamService = new ServeIceCreamService();
		ServeIceCreamResponse serveIceCreamResponse = new ServeIceCreamResponse();
		List<Coins> inputCoinsList = new ArrayList<>();
		inputCoinsList.add(Coins.NICKLE);
		inputCoinsList.add(Coins.NICKLE);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.CENT);
		ServeIceCreamRequest serveIceCreamRequest = new ServeIceCreamRequest(inputCoinsList,Flavour.STRAWBERRY);
		serveIceCreamResponse = serveIceCreamService.serveIceCream(serveIceCreamRequest);
		int amount = serveIceCreamResponse.getChange().stream().mapToInt(i -> i.getValue()).sum();
		assertEquals(serveIceCreamResponse.getStatus().name(), Status.NOT_SERVED.name());
		assertEquals(amount, 41);
	}
	//served scenario - amount 41 cents and cost of Vanilla is 50
	@Test
	public void testServeVenillaIceCream(){
		ServeIceCreamService serveIceCreamService = new ServeIceCreamService();
		ServeIceCreamResponse serveIceCreamResponse = new ServeIceCreamResponse();
		List<Coins> inputCoinsList = new ArrayList<>();
		inputCoinsList.add(Coins.NICKLE);
		inputCoinsList.add(Coins.NICKLE);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.DIME);
		inputCoinsList.add(Coins.CENT);
		ServeIceCreamRequest serveIceCreamRequest = new ServeIceCreamRequest(inputCoinsList,Flavour.VANILLA);
		serveIceCreamResponse = serveIceCreamService.serveIceCream(serveIceCreamRequest);
		int amount = serveIceCreamResponse.getChange().stream().mapToInt(i -> i.getValue()).sum();
		assertEquals(serveIceCreamResponse.getStatus().name(), Status.SERVED.name());
		assertEquals(amount, 21);
	}
}
