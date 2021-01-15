package com.springboot.vpp1849.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "NhaCungCap")
public class NhaCungCap {

	@Id
	@Column(name = "IdNCC")
	@NotNull
	private String idNCC;

	@Column(name = "TenNCC")
	@NotNull
	private String tenNCC;

	@Column(name = "DiaChi")
	@NotNull
	private String diaChi;

	@Column(name = "NgayXoa")
	private Date ngayXoa;

	@JsonIgnore
	@OneToMany(mappedBy = "nhaCungCap")
	private List<SanPham> sanPham;

	public NhaCungCap() {

	}

	public NhaCungCap(@NotNull String idNCC, @NotNull String tenNCC, @NotNull String diaChi, List<SanPham> sanPham,
			Date ngayXoa) {
		super();
		this.idNCC = idNCC;
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.sanPham = sanPham;
		this.ngayXoa = ngayXoa;
	}

	public String getIdNCC() {
		return idNCC;
	}

	public void setIdNCC(String idNCC) {
		this.idNCC = idNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public List<SanPham> getSanPham() {
		return sanPham;
	}

	public void setSanPham(List<SanPham> sanPham) {
		this.sanPham = sanPham;
	}

	public Date getNgayXoa() {
		return ngayXoa;
	}

	public void setNgayXoa(Date ngayXoa) {
		this.ngayXoa = ngayXoa;
	}

}
