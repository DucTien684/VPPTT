package com.springboot.vpp1849.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Ward")
public class Ward {
	@Id
	@Column(name = "WardId")
	private String wardId;

	@Column(name = "Name")
	private String name;

	@Column(name = "Type")
	private String type;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "DistrictId")
	private District district;

	public Ward() {
		
	}

	public Ward(String wardId, String name, String type, District district) {
		super();
		this.wardId = wardId;
		this.name = name;
		this.type = type;
		this.district = district;
	}

	public String getWardId() {
		return wardId;
	}

	public void setWardId(String wardId) {
		this.wardId = wardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
}
