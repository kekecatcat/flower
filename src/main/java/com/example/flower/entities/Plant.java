package com.example.flower.entities;

import com.example.flower.bean.Views;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Plant {

	@Id
	@GeneratedValue
	private Long id;

	@JsonView(Views.Public.class)
	@Column
	private String name;


	@JsonView(Views.Public.class)
	@Column(precision = 12, scale = 4)
	private double price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="delivery_id", nullable = true, referencedColumnName = "id")
	private Delivery delivery;

	public Plant() {

	}
	public Plant(String name, double price, Delivery delivery) {
		this.name = name;
		this.price = price;
		this.delivery = delivery;
	}

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}


	public double getPrice () {
		return price;
	}

	public Delivery getDelivery () {
		return delivery;
	}

	public void setDelivery (Delivery delivery) {
		this.delivery = delivery;
	}

	public void setPrice (double price) {
		this.price = price;
	}

}
