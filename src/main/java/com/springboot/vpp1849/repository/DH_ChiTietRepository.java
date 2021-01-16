package com.springboot.vpp1849.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.vpp1849.model.BinhLuan;
import com.springboot.vpp1849.model.DH_ChiTiet;


@Repository
public interface DH_ChiTietRepository extends JpaRepository<DH_ChiTiet, String> {


	DH_ChiTiet findByIdDonHangChiTiet(String idDonHang);

	@Query(value = "SELECT COUNT(IdDonHangChiTiet) FROM DH_ChiTiet", nativeQuery = true)
	String countDHCT();

	 //LẤY ALL SẢN PHẨM THEO IDDONHANG
		
	@Query(value = "SELECT dct.idDonHangChiTiet,\r\n"+ " sp.tensp, \r\n" +
			  "	dct.soluong, dct.dongia\r\n" + "	from DH_ChiTiet dct\r\n" +
			  "	INNER JOIN DonHang dh ON dh.idDonHang = dct.idDonHang \r\n" +
			  "	INNER JOIN SanPham sp ON sp.idSanPham = dct.idSanPham \r\n" +
			  "	WHERE dh.idDonHang = ?1\r\n" +
			  "	GROUP BY sp.tensp, dct.soluong, dct.dongia\r\n" +
			  "	ORDER BY dct.soluong ASC;", nativeQuery = true) 
			  List<DH_ChiTiet> getAllSPByIdDonHang(String idDonHang);
		
		 
	 
	  @Query(value = "SELECT s.TenSP FROM SanPham s, DH_ChiTiet d Where s.IdSanPham = d.IdSanPham AND d.IdDonHang = ?1 ", nativeQuery = true)
		String getBLTenSP(String idDonHang);
	  
	  

}