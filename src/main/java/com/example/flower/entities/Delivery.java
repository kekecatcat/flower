package com.example.flower.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@NamedQueries({
		@NamedQuery(
				name = "Delivery.findByName",
				query = "select d from Delivery d where d.name = :name")
})

@Entity
public class Delivery {

	@Id
	@GeneratedValue
	private Long id;

	@Nationalized
	@Column
	private String name;

	@Column(name = "address_full", length = 500)
	private String address;

	@Column
	private LocalDateTime deliveryTime;

	@Column(columnDefinition="boolean default false")
	//@Type(type = "yes_no")
	private boolean completed;

	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.ALL)
	@JsonIgnore
	@OneToMany(targetEntity = Plant.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Collection<Plant> plants;

	public Delivery() {

	}
	public Delivery(String name, String address, LocalDateTime time, boolean isCompleted, List<Plant> plants) {
		this.name = name;
		this.address = address;
		this.deliveryTime = time;
		this.completed = isCompleted;
		this.plants = plants;

	}

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
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

	public void setAddress (String address) {
		this.address = address;
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

	public void setPlants (Collection<Plant> plants) {
		this.plants = plants;
	}

	public Collection<Plant> getPlants () {
		return plants;
	}
}
