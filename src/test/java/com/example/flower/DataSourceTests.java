/*package com.example.flower;

import com.example.flower.entities.Delivery;
import com.example.flower.entities.Plant;
import com.example.flower.repository.DeliveryRepository;
import com.example.flower.repository.PlantRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
//@DataJpaTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class DataSourceTests {
*//*
	@Autowired
	TestEntityManager testEntityManager;*//*

	@Autowired
	public PlantRepository plantRepository;

	@Autowired
	public DeliveryRepository deliveryRepository;

	private static Plant plant1;
	private static Plant plant2;

	@BeforeAll
	public static void init () {
		plant1 = new Plant("plant1", 10, null);
		plant2 = new Plant("plant2", 20, null);
	}


	@Test
	public void testPriceLessThan() throws Exception {
		plantRepository.save(plant1);
		plantRepository.save(plant2);
		List<Plant> plants = plantRepository.findByPriceLessThan(25);
		Assert.assertEquals(2, plants.size());
	}

	@Test
	//@Transactional
	public void testDeliveryCompleted () throws Exception {
		List<Plant> plants = new ArrayList<>();
		plants.add(plant1);
		plants.add(plant2);
		Delivery delivery = new Delivery("delivery1", "new address", null, false, plants);
		delivery = deliveryRepository.save(delivery);
		for (Plant plant : delivery.getPlants()) {
			boolean isDelivered = plantRepository.deliveryCompleted(plant.getId());
			Assert.assertEquals(false, isDelivered);
		}
		delivery.setCompleted(true);
		deliveryRepository.merge(delivery);
		for (Plant plant : delivery.getPlants()) {
			boolean isDelivered = plantRepository.deliveryCompleted(plant.getId());
			Assert.assertEquals(true, isDelivered);

		}
	}
}*/
