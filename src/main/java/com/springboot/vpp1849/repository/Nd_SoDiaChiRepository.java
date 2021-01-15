package com.springboot.vpp1849.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.vpp1849.model.ND_SoDiaChi;

@Repository
public interface Nd_SoDiaChiRepository extends JpaRepository<ND_SoDiaChi, String> {

	// Tổng sổ địa chỉ
	@Query(value = "SELECT COUNT(IdSoDiaChi) FROM ND_SoDiaChi", nativeQuery = true)
	String countND_SDC();

	// Lấy Sổ địa chỉ theo Id để update Sổ địa chỉ
	@Query("FROM ND_SoDiaChi WHERE idSoDiaChi =?1")
	ND_SoDiaChi getSoDiaChiByIdSoDiaChi(String idSoDiaChi);

	@Query(value = "SELECT * FROM ND_SoDiaChi WHERE IdKhachHang = ?1", nativeQuery = true)
	List<ND_SoDiaChi> findByIdKhachHang(String idKhachHang);
}
