package com.example.flower.repository;


import com.example.flower.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

	public Plant findByName(String name);

	public Optional<Plant> findById(Long id);
	public boolean existsPlantByIdAndDeliveryCompleted(Long id, Boolean delivered);

	@Query("select new java.lang.Boolean(p.delivery.completed) from Plant p where p.id = :plantId")
	Boolean deliveryCompletedBoolean(Long plantId);

	@Query("select p.delivery.completed from Plant p where p.id = :plantId")
	Boolean deliveryCompleted(Long plantId);

	public List<Plant> findByPriceLessThan(double price);
}
