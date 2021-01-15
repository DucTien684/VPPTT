package com.springboot.vpp1849.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SP_NhomCon")
public class SP_NhomCon {

	@Id
	@Column(name = "IdNhomSPCon")
	@NotNull
	private String idNhomSPCon;

	@Column(name = "Name")
	@NotNull
	private String name;

	@JsonIgnore
	@NotNull
	@ManyToOne
	@JoinColumn(name = "IdNhomSP")
	private SP_Nhom sp_Nhom;

	@JsonIgnore
	@OneToMany(mappedBy = "sp_NhomCon")
	private List<SanPham> sanPham;

	public SP_NhomCon() {
		
	}

	public SP_NhomCon(@NotNull String idNhomSPCon, @NotNull String name, @NotNull SP_Nhom sp_Nhom,
			List<SanPham> sanPham) {
		super();
		this.idNhomSPCon = idNhomSPCon;
		this.name = name;
		this.sp_Nhom = sp_Nhom;
		this.sanPham = sanPham;
	}

	public String getIdNhomSPCon() {
		return idNhomSPCon;
	}

	public void setIdNhomSPCon(String idNhomSPCon) {
		this.idNhomSPCon = idNhomSPCon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SP_Nhom getSp_Nhom() {
		return sp_Nhom;
	}

	public void setSp_Nhom(SP_Nhom sp_Nhom) {
		this.sp_Nhom = sp_Nhom;
	}

	public List<SanPham> getSanPham() {
		return sanPham;
	}

	public void setSanPham(List<SanPham> sanPham) {
		this.sanPham = sanPham;
	}

}
