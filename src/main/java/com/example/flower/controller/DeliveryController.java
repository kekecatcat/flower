package com.example.flower.controller;


import com.example.flower.bean.DeliveryDTO;
import com.example.flower.bean.PlantDTO;
import com.example.flower.bean.RecipientAndPrice;
import com.example.flower.entities.Delivery;
import com.example.flower.entities.Plant;
import com.example.flower.service.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

	static Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	@Autowired
	public DeliveryService deliveryService;

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public DeliveryDTO find (@PathVariable("id") Long id) {
		return deliveryService.find(id);
	}

	@RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
	public void delete (@PathVariable("id") Long id) {
		try {
			deliveryService.delete(id);
		} catch (InvalidDataAccessApiUsageException exception) {
			logger.debug("can not delete");
		}
	}

	@RequestMapping(value = "/bill/{deliveryId}", method = RequestMethod.GET)
	public RecipientAndPrice getBill (@PathVariable("deliveryId") Long deliveryId) {
		return deliveryService.getBill(deliveryId);
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public List<Delivery> findByName (@PathVariable("name") String name) {
		return deliveryService.findByName(name);
	}

	@PostMapping
	public Delivery scheduleDelivery(@RequestBody DeliveryDTO deliveryDTO) {
		Delivery delivery = new Delivery();
		delivery.setName(deliveryDTO.getName());
		delivery.setAddress(deliveryDTO.getAddress());
		delivery.setCompleted(deliveryDTO.isCompleted());
		delivery.setDeliveryTime(deliveryDTO.getDeliveryTime());
		delivery.setPlants(new HashSet<>());
		for (PlantDTO plantDTO : deliveryDTO.getPlants()) {
			Plant plant = new Plant();
			plant.setName(plantDTO.getName());
			plant.setPrice(plantDTO.getPrice());
			delivery.getPlants().add(plant);
		}
		return deliveryService.save(delivery);
	}
	/*@RequestMapping(value = "/ee", method =  RequestMethod.GET)
	public DeliveryDTO f() {
		Delivery delivery = deliveryService.find(new Long(1));
		DeliveryDTO deliveryDTO = new DeliveryDTO();
		deliveryDTO.convertEntityToDeliveryDTO(delivery);
		return deliveryDTO;
	}*/
}
