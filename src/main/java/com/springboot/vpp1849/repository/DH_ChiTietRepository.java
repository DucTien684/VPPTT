package com.springboot.vpp1849.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.vpp1849.model.DH_ChiTiet;
import com.springboot.vpp1849.model.SanPham;

@Repository
public interface DH_ChiTietRepository extends JpaRepository<DH_ChiTiet, String> {

	DH_ChiTiet findByIdDonHangChiTiet(String idDonHang);

	@Query(value = "SELECT COUNT(IdDonHangChiTiet) FROM DH_ChiTiet", nativeQuery = true)
	String countDHCT();

	 //LẤY ALL SẢN PHẨM THEO IDDONHANG
	
	  @Query(value = "SELECT dct.idDonhang, dct.idsanpham, sp.tensp, \r\n" +
	  "	dct.soluong, dct.dongia, SUM(dct.dongia*dct.soluong)\r\n" + "	from SANPHAM sp\r\n" +
	  "	INNER JOIN DH_ChiTiet dct ON dct.IDSANPHAM = sp.IDSANPHAM\r\n" +
	  "	WHERE dct.idDonHangChiTiet = ?1\r\n" +
	  "	GROUP BY dct.idDonhang, dct.idsanpham, sp.tensp, dct.soluong, dct.dongia\r\n" +
	  "	ORDER BY dct.soluong ASC;", nativeQuery = true) 
	  List<DH_ChiTiet> getAllSPByIdDonHang(String idDonHangChiTiet);
	 
	  @Query(value = "SELECT s.TenSP FROM SanPham s, DH_ChiTiet d Where s.IdSanPham = d.IdSanPham AND d.IdDonHang = ?1 ", nativeQuery = true)
		String getBLTenSP(String idDonHang);
	  
	  

}
