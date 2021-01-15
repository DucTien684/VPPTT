package com.springboot.vpp1849.service;

import java.util.List;

import com.springboot.vpp1849.model.Ward;

public interface WardService {

	List<Ward> getWard(String districtId);
	
	String getNameByWardId(String wardId);

}
