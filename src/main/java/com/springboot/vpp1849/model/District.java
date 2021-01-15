package com.springboot.vpp1849.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "District")
public class District {
	@Id
	@Column(name = "DistrictId")
	private String districtId;

	@Column(name = "Name")
	private String name;

	@Column(name = "Type")
	private String type;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ProvinceId")
	private Province province;

	@JsonIgnore
	@OneToMany(mappedBy = "district")
	private List<Ward> ward;

	public District() {

	}

	public District(String districtId, String name, String type, Province province, List<Ward> ward) {
		super();
		this.districtId = districtId;
		this.name = name;
		this.type = type;
		this.province = province;
		this.ward = ward;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
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

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Ward> getWard() {
		return ward;
	}

	public void setWard(List<Ward> ward) {
		this.ward = ward;
	}
}
