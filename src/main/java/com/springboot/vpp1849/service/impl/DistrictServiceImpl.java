package com.springboot.vpp1849.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.vpp1849.model.District;
import com.springboot.vpp1849.repository.DistrictRepository;
import com.springboot.vpp1849.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	DistrictRepository districRepository;

	@Override
	public List<District> getDistrict(String provinceId) {
		return districRepository.getDistrict(provinceId);
	}

	@Override
	public String getNameByDistrictId(String districtId) {
		return districRepository.getNameByDistrictId(districtId);
	}

}