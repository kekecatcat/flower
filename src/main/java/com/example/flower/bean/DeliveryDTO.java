package com.example.flower.bean;

import com.example.flower.entities.Delivery;
import com.example.flower.entities.Plant;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class DeliveryDTO {
	private String name;
	private String address;
	private LocalDateTime deliveryTime;
	private boolean completed;
	private Set<PlantDTO> plants;

	public Set<PlantDTO> getPlants () {
		return plants;
	}

	public void setPlants (Set<PlantDTO> plants) {
		this.plants = plants;
	}



	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getAddress () {
		return address;
	}

	public void setAddress (String address_full) {
		this.address = address_full;
	}

	public LocalDateTime getDeliveryTime () {
		return deliveryTime;
	}

	public void setDeliveryTime (LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public boolean isCompleted () {
		return completed;
	}

	public void setCompleted (boolean completed) {
		this.completed = completed;
	}


	public DeliveryDTO convertEntityToDeliveryDTO(Delivery delivery) {
		this.name = delivery.getName();
		this.address = delivery.getAddress();
		this.deliveryTime = delivery.getDeliveryTime();
		this.completed = delivery.isCompleted();
		if (delivery.getPlants() == null) {
			return this;
		}
		this.plants = new HashSet<>();
		for (Plant plant : delivery.getPlants()) {
			this.plants.add(new PlantDTO().convertEntityToPlantDTO(plant));
		}
		return this;
	}
}
