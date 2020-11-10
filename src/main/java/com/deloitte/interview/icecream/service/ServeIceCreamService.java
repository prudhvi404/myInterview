package com.deloitte.interview.icecream.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.deloitte.interview.icecream.resource.Coins;
import com.deloitte.interview.icecream.resource.ServeIceCreamRequest;
import com.deloitte.interview.icecream.resource.ServeIceCreamResponse;
import com.deloitte.interview.icecream.resource.Status;

import lombok.NoArgsConstructor;

/**
 * @author Prudhvi
 *
 */
@Service
@NoArgsConstructor
public class ServeIceCreamService {

	/**
	 * This method is used to calculate the input amount
	 * and return opted ice cream is served or not along with the change amount
	 * @param serveIceCreamRequest
	 * @return ServeIceCreamResponse 
	 */
	public ServeIceCreamResponse serveIceCream(ServeIceCreamRequest serveIceCreamRequest) {
		ServeIceCreamResponse response = new ServeIceCreamResponse();
		int amount = serveIceCreamRequest.getCoins().stream().mapToInt(i -> i.getValue()).sum();
		int cost = serveIceCreamRequest.getFlavour().getValue();
		int change;
		if (amount >= cost) {
			change = amount - cost;
			List<Coins> changeList = countChange(change);
			response.setChange(changeList);
			response.setStatus(Status.SERVED);
			response.setFlavourName(serveIceCreamRequest.getFlavour().name());
		} else {
			change = amount;
			response.setChange(serveIceCreamRequest.getCoins());
			response.setStatus(Status.NOT_SERVED);
			response.setFlavourName(serveIceCreamRequest.getFlavour().name());
		}
		return response;

	}

	/**
	 * This method is used to calculate the change amount
	 * based on amount inserted and ice cream opted
	 * @param change
	 * @return List This returns the change in coins
	 */
	private List<Coins> countChange(int change) {
		List<Coins> changeList = new ArrayList<>();

		for (Coins coin : Coins.values()) {
			int value = change / coin.getValue();
			change = change % coin.getValue();

			for (int i = 1; i <= value; i++) {
				changeList.add(coin);
			}
		}

		return changeList;
	}
}
