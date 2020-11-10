package com.deloitte.interview.icecream.resource;

/**
 * @author Prudhvi
 *
 */
public enum Coins {
	QUARTER(25),
	DIME(10),
	NICKLE(5),
	CENT(1);
	
	
	private int value;
	
	Coins(int value) {
		this.value =value;
	}
	
	public int getValue() {
		return value;
	}	
}
