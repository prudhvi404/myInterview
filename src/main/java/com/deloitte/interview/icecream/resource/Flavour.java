package com.deloitte.interview.icecream.resource;

public enum Flavour {
	CHOCOLATE(40),VANILLA(20),STRAWBERRY(50);	
	
	private int value;
	
	Flavour(int value) {
		this.value =value;
	}
	
	public int getValue() {
		return value;
	}		
}
