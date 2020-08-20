package com.example.flower.controller;


import com.example.flower.bean.PlantDTO;
import com.example.flower.bean.Views;
import com.example.flower.entities.Plant;
import com.example.flower.service.PlantService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/plant")
public class PlantController {
	@Autowired
	private PlantService plantService;

	@RequestMapping(method = RequestMethod.PUT)
	public PlantDTO getPlantDTO(@RequestParam String name){
		return plantService.getPlantByName(name);
	}

	@RequestMapping(value = "/delivered/{id}", method = RequestMethod.GET)
	public Boolean delivered (@PathVariable Long id) {
		return plantService.delivered(id);
	}

	@JsonView(Views.Public.class)
	@RequestMapping(value = "underPrice/{price}", method = RequestMethod.GET)
	public List<Plant> plantsCheaperThan (@PathVariable double price) {
		return plantService.findPlantsBelowPrice(price);
	}

	/*@JsonView(Views.Public.class)
	@RequestMapping(method = RequestMethod.POST)
	public Plant getFilteredPlant(@RequestParam String name){
		return plantService.getPlantByName(name);
	}*/
}
