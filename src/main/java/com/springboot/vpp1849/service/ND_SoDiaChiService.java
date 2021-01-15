package com.springboot.vpp1849.service;

import java.util.List;

import com.springboot.vpp1849.model.ND_SoDiaChi;

public interface ND_SoDiaChiService {

	String customIdSoDiaChi();

	ND_SoDiaChi addSoDiaChi(ND_SoDiaChi nd_SoDiaChi);

	ND_SoDiaChi updateSoDiaChi(ND_SoDiaChi nd_SoDiaChi);

	ND_SoDiaChi getSoDiaChiByIdSoDiaChi(String idSoDiaChi);

	List<ND_SoDiaChi> findByIdKhachHang(String idKhachHang);

	void deleteSoDiaChi(String idSoDiaChi);

}
