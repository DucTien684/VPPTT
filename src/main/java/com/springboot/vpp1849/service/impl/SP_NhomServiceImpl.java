package com.springboot.vpp1849.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.vpp1849.model.SP_Nhom;
import com.springboot.vpp1849.repository.SP_NhomRepository;
import com.springboot.vpp1849.service.SP_NhomService;

@Service
public class SP_NhomServiceImpl implements SP_NhomService {

	@Autowired
	private SP_NhomRepository sp_NhomRepository;

	@Override
	public List<SP_Nhom> getAllNhom() {
		return sp_NhomRepository.findAll();
	}

	@Override
	public List<SP_Nhom> getNhomById(String idNhom, String idNhomCon) {
		// TODO Auto-generated method stub
		return sp_NhomRepository.getNhomById(idNhom, idNhomCon);
	}

	@Override
	public List<SP_Nhom> getTenNhomByIdSP(String idSanPham) {
		// TODO Auto-generated method stub
		return sp_NhomRepository.getNhomByIdSP(idSanPham);
	}

	@Override
	public String getTenNhomById(String idNhom, String idNhomCon) {
		// TODO Auto-generated method stub
		return sp_NhomRepository.getTenNhomById(idNhom, idNhomCon);
	}

}
