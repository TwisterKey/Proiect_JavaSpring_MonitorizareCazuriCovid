package com.proiectspringsasflorin.sms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "p_number")
	private String pnumber;
	
	@Column(name = "details")
	private String details;
	
	public Patient() {
		
	}
	
	public Patient(String name, String pnumber, String details) {
		super();
		this.name = name;
		this.pnumber = pnumber;
		this.details = details;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPnumber() {
		return pnumber;
	}
	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
