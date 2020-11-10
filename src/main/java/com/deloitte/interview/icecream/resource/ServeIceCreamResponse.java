package com.deloitte.interview.icecream.resource;

import java.util.List;


/**
 * @author Prudhvi
 *
 */
public class ServeIceCreamResponse {
	private List<Coins> change;
	private Status status;
	private String flavourName;
	
	public List<Coins> getChange() {
		return change;
	}
	
	public void setChange(List<Coins> change) {
		this.change = change;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

	public String getFlavourName() {
		return flavourName;
	}

	public void setFlavourName(String flavourName) {
		this.flavourName = flavourName;
	}
}
