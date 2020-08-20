package com.example.flower.repository;

import com.example.flower.bean.RecipientAndPrice;
import com.example.flower.entities.Delivery;
import com.example.flower.entities.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class DeliveryRepository {

	private static final String GET_NAME_AND_PRICE =
			"select new com.example.flower.entity.RecipientAndPrice(p.name, p.price) " +
					"from Delivery d " +
					"where p.name = :name";


	@PersistenceContext
	public EntityManager entityManager;

	@Autowired
	public PlantRepository plantRepository;

	public void persist (Delivery delivery) {
		entityManager.persist(delivery);
	}

	public Delivery find (Long id) {
		Delivery delivery = entityManager.find(Delivery.class, id);
		delivery.getPlants();
		return delivery;
	}

	public Delivery merge(Delivery delivery){
		entityManager.merge(delivery);
		for (Plant plant : delivery.getPlants()) {
			plant.setDelivery(delivery);
			if (plant.getId() != null && plantRepository.findById(plant.getId()).orElse(null) != null) {
				entityManager.merge(plant);
			}
			else {
				entityManager.persist(plant);
			}
		}
		return delivery;
	}

	public RecipientAndPrice getBill (Long deliveryId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecipientAndPrice> query = cb.createQuery(RecipientAndPrice.class);
		Root<Plant> root = query.from(Plant.class);
		query.select(
				cb.construct(
						RecipientAndPrice.class,
						root.get("delivery").get("name"),
						cb.sum(root.get("price"))))
				.where(cb.equal(root.get("delivery").get("id"), deliveryId));
		return entityManager.createQuery(query).getSingleResult();
	}

	public List<Delivery> findByName (String name) {
		List<Delivery> deliveries = new ArrayList<Delivery>();
		Query query = entityManager.createNamedQuery("Delivery.findByName", Delivery.class);
		deliveries = query.setParameter("name", name).getResultList();
		return deliveries;
	}

	public void delete (Long id) {
		Delivery delivery = entityManager.find(Delivery.class, id);
		entityManager.remove(delivery);
	}

	public Delivery save (Delivery delivery) {
		entityManager.persist(delivery);
		for (Plant plant : delivery.getPlants()) {
			plant.setDelivery(delivery);
			if (plant.getId() != null && plantRepository.findById(plant.getId()).orElse(null) != null) {
				entityManager.merge(plant);
			}
			else {
				entityManager.persist(plant);
			}
		}
		return delivery;
	}



}
