package com.example.flower.DAO;

import com.example.flower.entities.CandyData;

import java.util.List;

public interface CandyDAO {
	public List<CandyData> findAllCandy();
	public void addToDelivery(Long candyId, Long deliveryId);
	public List<CandyData> findByDelivery(Long delieryId);


}
