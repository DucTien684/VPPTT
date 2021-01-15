package com.springboot.vpp1849.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.vpp1849.model.SanPham;

public interface SanPhamService {

	Page<SanPham> searchSP(String tenSP, String idNhom, Pageable pageable);

	List<SanPham> getAllSP();

	Page<SanPham> getAllSP(Pageable pageable);

	List<SanPham> getSPMoi();

	List<SanPham> getSPBanChay();

	List<SanPham> getSPTheoDM(String idNhomSP);

	Page<SanPham> getSPByIdNhom(String idNhomSP, Pageable pageable);

	List<SanPham> getSPDacSac();

	List<SanPham> getSPByIdNhomCon(String idNhomSPCon);

	Page<SanPham> getSPByIdNhomCon(String idNhomCon, Pageable pageable);

	List<SanPham> getSanPhamById(String idSanPham);

	List<SanPham> getSPTuongTu(String idSanPham);

	List<SanPham> getAll();

	Page<SanPham> getSPByGiaTien(Double giaTien1, Double giaTien2, Pageable pageable);

	List<SanPham> getSPNgayXoaNotNull();

	SanPham deleteDateSP(String idSanPham);

	SanPham find(String idSanPham);

	SanPham addSP(SanPham sanPham);

	String customIdSanPham();

	String customMaSP();

	SanPham updateSP(SanPham sanPham);

	Optional<SanPham> findById(String idSanPham);

	// Update SP
	SanPham getSpByIdSanPham(String idSanPham);

	// Tổng sản phẩm (index)
	String countSanPham();

	// Tổng sản phẩm đã xóa
	String countSanPhamDaXoa();

	// Test DataExport
	boolean createPdfSanPham(List<SanPham> sanPhams, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

	boolean createExcelSanPham(List<SanPham> sanPhams, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

	boolean createPdfSanPhamDaXoa(List<SanPham> sanPhamDaXoa, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

	boolean createExcelSanPhamDaXoa(List<SanPham> sanPhamDaXoa, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);
}
