package com.springboot.vpp1849.service;

import java.text.ParseException;
import java.util.List;

import com.springboot.vpp1849.model.DonHang;
import com.springboot.vpp1849.model.DTO.DonHangDTO;
import com.springboot.vpp1849.model.DTO.DonHangDTOs;

public interface DonHangService {

	List<DonHangDTO> findAll(String id) throws ParseException;

	// Tìm tất cả hóa đơn
	List<DonHang> findAllDonHang();

	// Tìm tất cả hóa đơn có trạng thái là 3 ( Đang chờ)
	List<DonHang> findAllDonHangDangCho();

	// Tìm tất cả hóa đơn có trạng thái là 4 ( Đã xác nhận)
	List<DonHang> findAllDonHangDaXacNhan();

	// Tìm tất cả hóa đơn có trạng thái là 1 ( Giao thành công)
	List<DonHang> findAllDonHangGiaoThanhCong();

	// Tìm tất cả hóa đơn có trạng thái là 2 (Giao thất bại)
	List<DonHang> findAllDonHangGiaoThatBai();

	// Xác nhận đơn hàng
	DonHang xacNhanDonHang(String idDonHang);

	// Tiếp nhận đơn hàng
	DonHang tiepNhanDonHang(String idDonHang);

	// Đơn hàng giao thất bại
	DonHang tuChoiDonHang(String idDonHang);

	// DonHang theo id
	public DonHangDTOs findByIdDonHang(String idDonHang);

	String findTenSPByIdDonHang(String idDonHang);

	// Thêm DonHang
	DonHang addDonHang(DonHang DonHang);

	// Count DonHang
	String countDonHang();

	// Tổng DonHang giao thành công
	String countDonHangThanhCong();

	// Tổng DonHang giao thất bại
	String countDonHangThatBai();

	// Tổng DonHang mới
	String countDonHangDangCho();

	// Tổng tình trạng DonHang
	String countDonHangXacNhan();

	List<DonHang> findAllLichSuDonHang();

	DonHang deleteDHCT(String idDonHang);

}
