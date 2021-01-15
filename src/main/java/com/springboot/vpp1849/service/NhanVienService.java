package com.springboot.vpp1849.service;

import java.util.List;
import java.util.Optional;

import com.springboot.vpp1849.model.NhanVien;

public interface NhanVienService {
	public List<NhanVien> findAllNV();

	String countNhanVien();

	NhanVien addNV(NhanVien nhanVien);

	NhanVien deleteDateNV(String idNhanVien);

	NhanVien getSpByIdNhanVien(String idNhanVien);

	NhanVien updateNhanVien(NhanVien nhanVien);

	String countNhanVienDaXoa();

	// Tổng nhân viên chưa xóa - index
	String countNhanVienCX();
	
	Optional<NhanVien> checkLogin(String email, String password);

	NhanVien findByIdNhanVien(String idNhanVien);
	
	NhanVien findByIdNhanVienAndMatKhau(String idNhanVien, String matKhau);
	
	NhanVien getNVByEmail(String email);
}
