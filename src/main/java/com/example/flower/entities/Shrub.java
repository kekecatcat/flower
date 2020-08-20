package com.example.flower.entities;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Shrub extends Plant{

	@Column
	private Double height;
	@Column
	private Double width;


}
