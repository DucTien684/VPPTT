package com.springboot.vpp1849.service;

import java.util.List;

import com.springboot.vpp1849.URL.custom.CustomChiTietDonHang;
import com.springboot.vpp1849.model.DH_ChiTiet;

public interface DH_ChiTietService {

	DH_ChiTiet findByIdDonHangChiTiet(String idDonHang);

	DH_ChiTiet addDHChiTiet(DH_ChiTiet dhChiTiet);

	String countDHCT();
	
	 List<CustomChiTietDonHang> getDHChiTiet(String idDonHangChiTiet); 
	
}
