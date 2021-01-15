package com.springboot.vpp1849.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.vpp1849.model.NV_ChucVu;
import com.springboot.vpp1849.repository.NV_ChucVuRepository;
import com.springboot.vpp1849.service.NV_ChucVuService;

@Service
public class NV_ChucVuServiceImpl implements NV_ChucVuService{
	@Autowired
    NV_ChucVuRepository nvChucVuRepository;

    @Override
    public List<NV_ChucVu> findAllCV() {
        return nvChucVuRepository.findAll();
    }
}
