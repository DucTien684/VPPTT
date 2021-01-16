package com.springboot.vpp1849.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.vpp1849.URL.custom.CustomChiTietDonHang;
import com.springboot.vpp1849.model.DH_ChiTiet;
import com.springboot.vpp1849.model.SanPham;
import com.springboot.vpp1849.repository.DH_ChiTietRepository;
import com.springboot.vpp1849.service.DH_ChiTietService;

@Service
public class DH_ChiTietServiceImpl implements DH_ChiTietService{
	@Autowired
	DH_ChiTietRepository dh_ChiTietRepository;
	
	@Override
	public DH_ChiTiet findByIdDonHangChiTiet(String idDonHang) {
		return dh_ChiTietRepository.findByIdDonHangChiTiet(idDonHang);
	}
	
	@Override
	public DH_ChiTiet addDHChiTiet(DH_ChiTiet dhChiTiet) {
		return dh_ChiTietRepository.save(dhChiTiet);
	}

	@Override
	public String countDHCT() {
		int tsDHCT = Integer.parseInt(dh_ChiTietRepository.countDHCT());
		String idDHCT = "";
		if(tsDHCT < 1000) {
			idDHCT = "DHCT0" + String.valueOf(tsDHCT + 1);
		} else {
			idDHCT = "DHCT" + String.valueOf(tsDHCT + 1);
		}	
		return idDHCT;
	}

	
	  @Override public List<CustomChiTietDonHang> getDHChiTiet(String idDonHangChiTiet) {
	  
	  List<CustomChiTietDonHang> sanPham = new ArrayList<CustomChiTietDonHang>();
	 
	  List<DH_ChiTiet> spham = dh_ChiTietRepository.getAllSPByIdDonHang(idDonHangChiTiet);
	  
	  for (DH_ChiTiet d : spham) {
	  
	  CustomChiTietDonHang sp = new CustomChiTietDonHang();
	  sp.setTenSP(dh_ChiTietRepository.getBLTenSP(d.getIdDonHangChiTiet()));
	  sp.setSoLuong(d.getSoLuong()); sp.setDonGia(d.getDonGia()); sanPham.add(sp);
	  }
	  
	  return sanPham; }

	
	 

}
