package com.springboot.vpp1849.service;

import java.util.List;

import com.springboot.vpp1849.model.SP_Nhom;

public interface SP_NhomService {

	List<SP_Nhom> getAllNhom();

	String getTenNhomById(String idNhom, String idNhomCon);

	List<SP_Nhom> getNhomById(String idNhom, String idNhomCon);

	public List<SP_Nhom> getTenNhomByIdSP(String idSanPham);

}
