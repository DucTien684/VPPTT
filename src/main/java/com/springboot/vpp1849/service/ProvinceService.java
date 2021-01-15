package com.springboot.vpp1849.service;

import java.util.List;

import com.springboot.vpp1849.model.Province;

public interface ProvinceService {

	List<Province> getProvince();

	String getNameByProvinceId(String provinceId);
}
