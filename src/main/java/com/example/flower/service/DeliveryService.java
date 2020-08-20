package com.example.flower.service;

import com.example.flower.bean.DeliveryDTO;
import com.example.flower.bean.RecipientAndPrice;
import com.example.flower.entities.Delivery;
import com.example.flower.repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeliveryService {

	static Logger logger = LoggerFactory.getLogger(DeliveryService.class);
	@Autowired
	public DeliveryRepository deliveryRepository;

	/*public void delete(Long id) throws EmptyResultDataAccessException{
		logger.debug("Try to delete Delivery with id: {}", id);
		try {
			deliveryRepository.deleteById(id);
		} catch (EmptyResultDataAccessException exception) {
			logger.debug("No entity with exists with id: {}", id);
			throw exception;
		}
	}*/

	/*public DeliveryDTO find(Long id) throws NoSuchElementException {
		Optional<Delivery> deliveryOptional = deliveryRepository.findById(id);
		if (deliveryOptional.isPresent()) {
			DeliveryDTO deliveryDTO = new DeliveryDTO();
			deliveryDTO.convertEntityToDeliveryDTO(deliveryOptional.get());
			return deliveryDTO;
		} else {
			logger.debug("No Delivery object with id: {}", id);
			throw new NoSuchElementException("No Delivery object with id: " + id);
		}
	}*/

	public void delete(Long id) throws InvalidDataAccessApiUsageException {
		deliveryRepository.delete(id);
	}


	@Transactional
	public DeliveryDTO find(Long id) {
		Delivery delivery = deliveryRepository.find(id);
		if (delivery == null) {
			return null;
		}
		DeliveryDTO deliveryDTO = new DeliveryDTO();
		deliveryDTO.convertEntityToDeliveryDTO(delivery);
		return deliveryDTO;
	}

	public RecipientAndPrice getBill (Long deliveryId) {
		return deliveryRepository.getBill(deliveryId);
	}

	public List<Delivery> findByName (String name) {
		return deliveryRepository.findByName(name);
	}

	public Delivery save (Delivery delivery) {
		return deliveryRepository.save(delivery);
	}

	public void test() {
		return;
	}
	public void test2() {
		return;
	}
	public void test3() {
		return;
	}
	public void test4() {
		return;
	}
	public void test5() {
		return;
	}
	public void test6() {
		return;
	}
}
