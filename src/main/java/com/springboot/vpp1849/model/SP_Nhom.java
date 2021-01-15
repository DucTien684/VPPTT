package com.springboot.vpp1849.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SP_Nhom")
public class SP_Nhom {

	@Id
	@Column(name = "IdNhomSP")
	@NotNull
	private String idNhomSP;

	@Column(name = "Name")
	@NotNull
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "sp_Nhom")
	private List<SP_NhomCon> sp_NhomCon;

	public SP_Nhom() {
		
	}

	public SP_Nhom(@NotNull String idNhomSP, @NotNull String name, List<SP_NhomCon> sp_NhomCon) {
		super();
		this.idNhomSP = idNhomSP;
		this.name = name;
		this.sp_NhomCon = sp_NhomCon;
	}

	public String getIdNhomSP() {
		return idNhomSP;
	}

	public void setIdNhomSP(String idNhomSP) {
		this.idNhomSP = idNhomSP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SP_NhomCon> getSp_NhomCon() {
		return sp_NhomCon;
	}

	public void setSp_NhomCon(List<SP_NhomCon> sp_NhomCon) {
		this.sp_NhomCon = sp_NhomCon;
	}

}
