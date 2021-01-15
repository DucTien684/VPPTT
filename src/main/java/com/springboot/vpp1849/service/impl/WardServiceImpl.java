package com.springboot.vpp1849.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.vpp1849.model.Ward;
import com.springboot.vpp1849.repository.WardRepository;
import com.springboot.vpp1849.service.WardService;

@Service
public class WardServiceImpl implements WardService {

	@Autowired
	WardRepository wardRepository;

	@Override
	public List<Ward> getWard(String districtId) {
		return wardRepository.getWard(districtId);
	}

	@Override
	public String getNameByWardId(String wardId) {
		return wardRepository.getNameByWardId(wardId);
	}

}
