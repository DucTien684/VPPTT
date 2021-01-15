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
@Table(name = "NV_ChucVu")
public class NV_ChucVu {
	@Id
	@Column(name = "IdChucVu")
	@NotNull
	private String idChucVu;

	@Column(name = "Name")
	@NotNull
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "nv_ChucVu")
	private List<NhanVien> nhanVien;

	@JsonIgnore
	@OneToMany(mappedBy = "nv_ChucVu")
	private List<NV_PhanQuyen> nv_PhanQuyen;

	public NV_ChucVu() {

	}

	public NV_ChucVu(@NotNull String idChucVu, @NotNull String name, List<NhanVien> nhanVien,
			List<NV_PhanQuyen> nv_PhanQuyen) {
		super();
		this.idChucVu = idChucVu;
		this.name = name;
		this.nhanVien = nhanVien;
		this.nv_PhanQuyen = nv_PhanQuyen;
	}

	public String getIdChucVu() {
		return idChucVu;
	}

	public void setIdChucVu(String idChucVu) {
		this.idChucVu = idChucVu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NhanVien> getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(List<NhanVien> nhanVien) {
		this.nhanVien = nhanVien;
	}

	public List<NV_PhanQuyen> getNv_PhanQuyen() {
		return nv_PhanQuyen;
	}

	public void setNv_PhanQuyen(List<NV_PhanQuyen> nv_PhanQuyen) {
		this.nv_PhanQuyen = nv_PhanQuyen;
	}

}
