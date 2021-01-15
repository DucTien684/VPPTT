package com.springboot.vpp1849.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.vpp1849.model.ND_SoDiaChi;
import com.springboot.vpp1849.repository.Nd_SoDiaChiRepository;
import com.springboot.vpp1849.service.ND_SoDiaChiService;

@Service
public class ND_SoDiaChiServiceImpl implements ND_SoDiaChiService {

	@Autowired
	private Nd_SoDiaChiRepository nd_SoDiaChiRepository;

	@Override
	public String customIdSoDiaChi() {
		int tongIdSodiaChi = Integer.parseInt(nd_SoDiaChiRepository.countND_SDC());
		String idSoDiaChi = "";
		if (tongIdSodiaChi < 100) {
			idSoDiaChi = "SDC0" + String.valueOf(tongIdSodiaChi + 1);
		} else {
			idSoDiaChi = "SDC" + String.valueOf(tongIdSodiaChi + 1);
		}

		return idSoDiaChi;
	}

	@Override
	public ND_SoDiaChi addSoDiaChi(ND_SoDiaChi nd_SoDiaChi) {
		return nd_SoDiaChiRepository.save(nd_SoDiaChi);
	}

	@Override
	public ND_SoDiaChi updateSoDiaChi(ND_SoDiaChi nd_SoDiaChi) {
		return nd_SoDiaChiRepository.save(nd_SoDiaChi);
	}

	@Override
	public ND_SoDiaChi getSoDiaChiByIdSoDiaChi(String idSoDiaChi) {
		return nd_SoDiaChiRepository.getSoDiaChiByIdSoDiaChi(idSoDiaChi);
	}

	@Override
	public List<ND_SoDiaChi> findByIdKhachHang(String idKhachHang) {
		return nd_SoDiaChiRepository.findByIdKhachHang(idKhachHang);
	}
	
	@Override
	public void deleteSoDiaChi(String idSoDiaChi) {
		ND_SoDiaChi soDiaChi = nd_SoDiaChiRepository.getSoDiaChiByIdSoDiaChi(idSoDiaChi);
		if(soDiaChi != null) {
			nd_SoDiaChiRepository.delete(soDiaChi);
		}
	}
}
