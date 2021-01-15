package com.springboot.vpp1849.model.DTO;

import com.springboot.vpp1849.model.SanPham;

public class Item {
	private SanPham sanPham;
	private int quantity;

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(SanPham sanPham, int quantity) {
		super();
		this.sanPham = sanPham;
		this.quantity = quantity;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
