package com.example.flower.bean;


import com.example.flower.entities.Plant;
import org.springframework.beans.BeanUtils;

public class PlantDTO {
	private String name;
	private Double price;
	/*private DeliveryDTO delivery;

	public DeliveryDTO getDelivery () {
		return delivery;
	}

	public void setDelivery (Delivery delivery) {
		DeliveryDTO deliveryDTO = new DeliveryDTO();
		deliveryDTO.convertEntityToDeliveryDTO(delivery);
		this.delivery = deliveryDTO;
	}*/

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public Double getPrice () {
		return price;
	}

	public void setPrice (Double price) {
		this.price = price;
	}

	public PlantDTO convertEntityToPlantDTO(Plant plant) {
		BeanUtils.copyProperties(plant, this);
		return this;
	}
}
