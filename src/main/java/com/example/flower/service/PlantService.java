package com.example.flower.service;

import com.example.flower.bean.PlantDTO;
import com.example.flower.entities.Plant;
import com.example.flower.repository.PlantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PlantService {
	static Logger logger = LoggerFactory.getLogger(PlantService.class);

	@Autowired
	private PlantRepository plantRepository;

	public PlantDTO getPlantByName (String name) throws NoSuchElementException {
		/*Optional<Plant> optionalPlant = plantRepository.findByName(name);
		if (optionalPlant.isPresent()) {
			Plant plant = optionalPlant.get();
			PlantDTO plantDTO = new PlantDTO();
			plantDTO.convertEntityToPlantDTO(plant);
			return plantDTO;
		} else {
			logger.debug("No plant entity is found with name: {}", name);
			throw new NoSuchElementException("No plant entity is found with name: " + name);
		}*/
		Plant plant = plantRepository.findByName(name);
		if (plant != null) {
			PlantDTO plantDTO = new PlantDTO();
			return plantDTO.convertEntityToPlantDTO(plant);
		}
		else {
			logger.debug("No plant entity is found with name: {}", name);
			throw new NoSuchElementException("No plant entity is found with name: " + name);
		}

	}
	public Boolean delivered (Long id) throws NoSuchElementException {
		//return plantRepository.existsPlantByIdAndDeliveryCompleted(id, true);
		Plant plant = plantRepository.findById(id).orElse(null);
		if (plant == null) {
			throw new NoSuchElementException("No plant Entity with id: " + id);
		}
		return plantRepository.deliveryCompleted(id);
	}

	public List<Plant> findPlantsBelowPrice (double price) {
		return plantRepository.findByPriceLessThan(price);
	}

}

