package com.springboot.vpp1849.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.vpp1849.URL.custom.CustomBinhLuan;
import com.springboot.vpp1849.model.BinhLuan;
import com.springboot.vpp1849.repository.BinhLuanRepository;
import com.springboot.vpp1849.service.BinhLuanService;

@Service
public class BinhLuanServiceImpl implements BinhLuanService {

	@Autowired
	private BinhLuanRepository binhLuanRepository;

	@Override
	public String getBLSanPham(String idSanPham) {
		return binhLuanRepository.getBLSanPham(idSanPham);

	}

	@Override
	public List<CustomBinhLuan> getBinhLuan(String idSanPham) {

		List<CustomBinhLuan> binhLuan = new ArrayList<CustomBinhLuan>();
		
		List<BinhLuan> bluan = binhLuanRepository.getBLByIdSP(idSanPham);
		
		for (BinhLuan b : bluan) {
		
			CustomBinhLuan cs = new CustomBinhLuan();
			cs.setTenKH(binhLuanRepository.getBLTenKH(b.getIdBinhLuan()));
			cs.setNgayBL(b.getThoiGian());
			cs.setNoiDungBL(b.getNoiDung());
			binhLuan.add(cs);
		}

		return binhLuan;
	}

	@Override
	public String countBL() {
		int countBL = Integer.parseInt(binhLuanRepository.countBL()) + 1;
		String idBinhLuan = "";
		if(countBL < 100) {
			idBinhLuan = "BL0" + String.valueOf(countBL);
		} else {
			idBinhLuan = "BL" + String.valueOf(countBL);
		}
		return idBinhLuan;
	}

	@Override
	public BinhLuan addBinhLuan(BinhLuan binhLuan) {
		return binhLuanRepository.save(binhLuan);
	}
}
