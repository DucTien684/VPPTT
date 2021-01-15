package com.springboot.vpp1849.service;

import java.util.List;
import java.util.Optional;

import com.springboot.vpp1849.model.KhachHang;

public interface KhachHangService {

	Optional<KhachHang> checkLogin(String email, String password);

	KhachHang addKhachHang(KhachHang khachHang);

	String countKH();

	List<KhachHang> findByNgayXoaNull();

	List<KhachHang> findByNgayXoaNotNull();

	List<KhachHang> findByKhachHangChuaDK();

	List<KhachHang> findByKhachHangDK();

	KhachHang deleteDateKH(String idKhachHang);

	KhachHang findByIdKhachHang(String idKhachHang);

	KhachHang getKHByEmail(String email);

	KhachHang getKHBySDT(String sdt);

	KhachHang updateKhachHang(KhachHang khachHang);

	KhachHang findByIdKhachHangAndMatKhau(String idKhachHang, String matKhau);

	// Tổng khách hàng chưa xóa index
	String countKhachHang();

	// Tổng khách hàng chưa đăng ký
	String countKhachHangChuaDangKy();

	// Tổng khách hàng đã đăng ký
	String countKhachHangDangKy();

	// Tổng khách hàng đã xóa
	String countKhachHangDaXoa();

}
