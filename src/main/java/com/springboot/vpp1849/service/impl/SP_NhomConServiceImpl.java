package com.springboot.vpp1849.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.vpp1849.model.SP_NhomCon;
import com.springboot.vpp1849.repository.SP_NhomConRepository;
import com.springboot.vpp1849.service.NhomConService;

@Service
public class SP_NhomConServiceImpl implements NhomConService {

	@Autowired
	private SP_NhomConRepository nhomConRepository;

	@Override
	public List<SP_NhomCon> getNhomCon(String idNhomSP) {
		// TODO Auto-generated method stub
		return nhomConRepository.getNhomCon(idNhomSP);
	}

	@Override
	public List<SP_NhomCon> getBCNhomCon(String idNhomSPCon) {
		// TODO Auto-generated method stub
		return nhomConRepository.getBCNhomCon(idNhomSPCon);
	}

	@Override
	public List<SP_NhomCon> getBCNhomConByIdSP(String idSanPham) {
		// TODO Auto-generated method stub
		return nhomConRepository.getBCNhomConByIdSP(idSanPham);
	}
}
