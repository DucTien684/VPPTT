package com.springboot.vpp1849.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.vpp1849.model.Province;
import com.springboot.vpp1849.repository.ProvinceRepository;
import com.springboot.vpp1849.service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	ProvinceRepository provinceRepository;
	
	@Override
	public List<Province> getProvince() {
		return provinceRepository.findAll();
	}

	@Override
	public String getNameByProvinceId(String provinceId) {
		return provinceRepository.getNameByProvinceId(provinceId);
	}
}
