package com.springboot.vpp1849.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.vpp1849.model.NhanVien;
import com.springboot.vpp1849.repository.NhanVienRepository;
import com.springboot.vpp1849.service.NhanVienService;

@Service
public class NhanVienServiceImpl implements NhanVienService {
	@Autowired
	NhanVienRepository nhanVienRepository;

	public List<NhanVien> findNVAll() {
		return nhanVienRepository.getAllNV();
	}

	@Override
	public String countNhanVien() {
		return nhanVienRepository.countNhanVien();
	}

	@Override
	public NhanVien getSpByIdNhanVien(String idNhanVien) {
		return nhanVienRepository.getSpByIdNhanVien(idNhanVien);
	}

	@Override
	public NhanVien updateNhanVien(NhanVien nhanVien) {
		return nhanVienRepository.save(nhanVien);
	}

	@Override
	public NhanVien deleteDateNV(String idNhanVien) {
		NhanVien deleteNV = null;
		if (nhanVienRepository.existsById(idNhanVien)) {
			NhanVien nhanVien = nhanVienRepository.getOne(idNhanVien);
			nhanVien.setNgayXoa(new Date());
			deleteNV = nhanVienRepository.saveAndFlush(nhanVien);
		}
		return deleteNV;
	}

	@Override
	public String countNhanVienDaXoa() {
		return nhanVienRepository.countNhanVienDaXoa();
	}

	@Override
	public NhanVien addNV(NhanVien nhanVien) {
		return nhanVienRepository.save(nhanVien);
	}

	public String customIdNhanVien() {
		int tongSoNhanVien = Integer.parseInt(nhanVienRepository.countNhanVien());
		String idNhanVien = "US" + String.valueOf(tongSoNhanVien + 1);
		return idNhanVien;
	}

	@Override
	public List<NhanVien> findAllNV() {
		return nhanVienRepository.getAllNV();
	}

	// Tổng nhân viên chưa xóa - index
	@Override
	public String countNhanVienCX() {
		return nhanVienRepository.countNhanVienCX();
	}

	@Override
	public Optional<NhanVien> checkLogin(String email, String password) {
		return nhanVienRepository.checkLogin(email, password);
	}

	@Override
	public NhanVien findByIdNhanVien(String idNhanVien) {
		return nhanVienRepository.findByIdNhanVien(idNhanVien);
	}

	@Override
	public NhanVien findByIdNhanVienAndMatKhau(String idNhanVien, String matKhau) {
		return nhanVienRepository.findByIdNhanVienAndMatKhau(idNhanVien, matKhau);
	}

	@Override
	public NhanVien getNVByEmail(String email) {
		return nhanVienRepository.findByEmail(email);
	}
}
