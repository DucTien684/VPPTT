package com.springboot.vpp1849.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.vpp1849.model.BinhLuan;

@Repository
public interface BinhLuanRepository extends JpaRepository<BinhLuan, String> {

	
	// Bình luận - Product
	@Query("FROM BinhLuan b WHERE b.sanPham.idSanPham = ?1")
	List<BinhLuan> getBLByIdSP(String idSanPham);
	
	@Query(value = "SELECT k.HoTen FROM KhachHang k, BinhLuan b Where k.IdKhachHang = b.IdKhachHang AND b.IdBinhLuan = ?1 ", nativeQuery = true)
	String getBLTenKH(String idBinhLuan);
	
	@Query("SELECT s.tenSP FROM SanPham s WHERE s.idSanPham = ?1")
	String getBLSanPham(String idSanPham);
	
	@Query(value = "SELECT COUNT(IdBinhLuan) FROM BinhLuan", nativeQuery = true)
	String countBL();

}
