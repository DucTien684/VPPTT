package com.springboot.vpp1849.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.vpp1849.model.NhaCungCap;
import com.springboot.vpp1849.repository.NhaCungCapRepository;
import com.springboot.vpp1849.service.NhaCungCapService;

@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {
	@Autowired
	NhaCungCapRepository nhaCungCapRepository;

	@Override
	public List<NhaCungCap> findNCCAll() {
		return nhaCungCapRepository.getAllNCC();
	}

	@Override
	public String countNCC() {
		return nhaCungCapRepository.countNCC();
	}

	@Override
	public NhaCungCap addNCC(NhaCungCap nhaCungCap) {
		return nhaCungCapRepository.save(nhaCungCap);
	}

	public String customIdNhaCungCap() {
		int tongSoNhaCungCap = Integer.parseInt(nhaCungCapRepository.countNCC());
		String idNhaCungCap = "SUP0" + String.valueOf(tongSoNhaCungCap + 1);
		return idNhaCungCap;
	}

	@Override
	public NhaCungCap deleteDateNCC(String idNCC) {
		NhaCungCap deleteNCC = null;
		if (nhaCungCapRepository.existsById(idNCC)) {
			NhaCungCap nhaCungCap = nhaCungCapRepository.getOne(idNCC);
			nhaCungCap.setNgayXoa(new Date());
			deleteNCC = nhaCungCapRepository.saveAndFlush(nhaCungCap);
		}
		return deleteNCC;
	}

	@Override
	public NhaCungCap findByIdNCC(String idNCC) {
		return nhaCungCapRepository.findByIdNCC(idNCC);
	}

	@Override
	public NhaCungCap updateNCC(NhaCungCap nhaCungCap) {
		return nhaCungCapRepository.save(nhaCungCap);
	}

	// Tổng nhà cung cấp chưa xóa - index
	@Override
	public String countNCCCX() {
		return nhaCungCapRepository.countNCCCX();
	}

	@Override
	public String countNCCDaXoa() {
		return nhaCungCapRepository.countNCCDaXoa();
	}

	@Override
	public List<NhaCungCap> getNCCNgayXoaNotNull() {
		return nhaCungCapRepository.findByNgayXoaNotNull();
	}
}
