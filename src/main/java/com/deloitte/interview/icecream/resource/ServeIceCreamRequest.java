package com.deloitte.interview.icecream.resource;

import java.util.List;

public class ServeIceCreamRequest {
	private List<Coins> coins;
	private Flavour flavour;
	
	public ServeIceCreamRequest(List<Coins> coins, Flavour flavour) {
		this.coins = coins;
		this.flavour = flavour;
	}

	public List<Coins> getCoins() {
		return coins;
	}
	
	public void setCoins(List<Coins> coins) {
		this.coins = coins;
	}
	
	public Flavour getFlavour() {
		return flavour;
	}
	
	public void setFlavour(Flavour flavour) {
		this.flavour = flavour;
	}
		
}
