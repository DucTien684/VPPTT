package com.springboot.vpp1849.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.vpp1849.model.SP_Nhom;

@Repository
public interface SP_NhomRepository extends JpaRepository<SP_Nhom, String> {

	// Title - Shop
	@Query(value = "SELECT DISTINCT n.Name FROM SP_Nhom n, SP_NhomCon nc WHERE n.IdNhomSP = ?1 OR n.IdNhomSP in (Select nc.IdNhomSP FROM SP_NhomCon nc where nc.IdNhomSPCon = ?2)", nativeQuery = true)
	String getTenNhomById(String idNhom, String idNhomCon);

	@Query(value = "SELECT DISTINCT n.* FROM SP_Nhom n, SP_NhomCon nc WHERE n.IdNhomSP = ?1 OR n.IdNhomSP in (Select nc.IdNhomSP FROM SP_NhomCon nc where nc.IdNhomSPCon = ?2)", nativeQuery = true)
	List<SP_Nhom> getNhomById(String idNhom, String idNhomCon);

	// Lấy Nhóm từ idSanPham - Breadcrumb Prodcut
	@Query(value = "SELECT DISTINCT n.* FROM SP_Nhom n, SP_NhomCon nc WHERE n.IdNhomSP = nc.IdNhomSP AND nc.IdNhomSPCon IN "
			+ "(SELECT nc.IdNhomSPCon FROM SP_NhomCon , SanPham sp WHERE nc.IdNhomSPCon = sp.IdNhomSPCon AND sp.IdSanPham = ?1 )", nativeQuery = true)
	List<SP_Nhom> getNhomByIdSP(String idSanPham);

}
