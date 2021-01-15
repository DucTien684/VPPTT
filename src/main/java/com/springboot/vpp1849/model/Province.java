package com.springboot.vpp1849.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Province")
public class Province {
	@Id
	@Column(name = "ProvinceId")
	private String provinceId;

	@Column(name = "Name")
	private String name;

	@Column(name = "Type")
	private String type;

	@JsonIgnore
	@OneToMany(mappedBy = "province")
	private List<District> district;

	public Province() {

	}

	public Province(String provinceId, String name, String type, List<District> district) {
		super();
		this.provinceId = provinceId;
		this.name = name;
		this.type = type;
		this.district = district;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
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

	public List<District> getDistrict() {
		return district;
	}

	public void setDistrict(List<District> district) {
		this.district = district;
	}

}
