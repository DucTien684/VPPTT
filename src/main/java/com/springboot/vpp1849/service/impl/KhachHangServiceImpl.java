package com.springboot.vpp1849.service.impl;

import com.springboot.vpp1849.model.KhachHang;
import com.springboot.vpp1849.repository.KhachHangRepository;
import com.springboot.vpp1849.service.KhachHangService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {
	@Autowired
	private KhachHangRepository khachHangRepository;

	@Override
	public Optional<KhachHang> checkLogin(String email, String password) {
		return khachHangRepository.checkLogin(email, password);
	}

	@Override
	public KhachHang addKhachHang(KhachHang khachHang) {
		return khachHangRepository.save(khachHang);
	}

	@Override
	public String countKH() {
		int tsKH = Integer.parseInt(khachHangRepository.countKH());

		String idKH = "";
		if (tsKH % 100 == 0) {
			idKH = "KH0" + String.valueOf(tsKH);
		} else {
			idKH = "KH" + String.valueOf(tsKH + 1);
		}

		return idKH;
	}

	@Override
	public List<KhachHang> findByNgayXoaNull() {
		return khachHangRepository.findByNgayXoaNull();
	}

	@Override
	public List<KhachHang> findByNgayXoaNotNull() {
		return khachHangRepository.findByNgayXoaNotNull();
	}

	@Override
	public List<KhachHang> findByKhachHangChuaDK() {
		return khachHangRepository.findByKhachHangChuaDK();
	}

	@Override
	public List<KhachHang> findByKhachHangDK() {
		return khachHangRepository.findByKhachHangDK();
	}

	@Override
	public KhachHang deleteDateKH(String idKhachHang) {
		KhachHang spUpdate = null;
		// Kiểm tra xem idKhachHang có tồn tại chưa bằng hàm existById
		if (khachHangRepository.existsById(idKhachHang)) {
			KhachHang kh = khachHangRepository.getOne(idKhachHang);
			kh.setNgayXoa(new Date());
			spUpdate = khachHangRepository.saveAndFlush(kh);
		}
		return spUpdate;
	}

	@Override
	public KhachHang findByIdKhachHang(String idKhachHang) {
		return khachHangRepository.findByIdKhachHang(idKhachHang);
	}

	@Override
	public KhachHang getKHByEmail(String email) {
		return khachHangRepository.findByEmail(email);
	}

	@Override
	public KhachHang getKHBySDT(String sdt) {
		return khachHangRepository.findBySdt(sdt);
	}

	@Override
	public KhachHang updateKhachHang(KhachHang khachHang) {
		return khachHangRepository.save(khachHang);
	}

	@Override
	public KhachHang findByIdKhachHangAndMatKhau(String idKhachHang, String matKhau) {
		return khachHangRepository.findByIdKhachHangAndMatKhau(idKhachHang, matKhau);
	}

	// Tổng khách hàng index
	@Override
	public String countKhachHang() {
		return khachHangRepository.countKhachHang();
	}

	// Tổng khách hàng chưa đăng ký
	@Override
	public String countKhachHangChuaDangKy() {
		return khachHangRepository.countKhachHangChuaDangKy();
	}

	// Tổng khách hàng đã đăng ký
	@Override
	public String countKhachHangDangKy() {
		return khachHangRepository.countKhachHangDangKy();
	}

	// Tổng khách hàng đã xóa
	@Override
	public String countKhachHangDaXoa() {
		return khachHangRepository.countKhachHangDaXoa();
	}
}
