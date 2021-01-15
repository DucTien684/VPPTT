package com.springboot.vpp1849.service;

import java.util.List;

import com.springboot.vpp1849.model.District;

public interface DistrictService {
	
	 List<District> getDistrict(String provinceId);
	
	 String getNameByDistrictId(String districtId);
}

