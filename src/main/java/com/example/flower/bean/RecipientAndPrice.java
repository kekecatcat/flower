package com.example.flower.bean;

public class RecipientAndPrice {
	private Double price;
	private String name;

	public Double getPrice () {
		return price;
	}

	public void setPrice (Double price) {
		this.price = price;
	}

	public RecipientAndPrice (String name, Double price) {
		this.price = price;
		this.name = name;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}
}
