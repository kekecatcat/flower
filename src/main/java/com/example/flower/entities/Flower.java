package com.example.flower.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Flower extends Plant {

	@Column
	private String color;
}
