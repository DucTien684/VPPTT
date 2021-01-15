package com.springboot.vpp1849.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.vpp1849.model.SP_NhomCon;

@Repository
public interface SP_NhomConRepository extends JpaRepository<SP_NhomCon, String> {
	// Lấy nhóm con theo id nhóm- shop
	@Query("FROM SP_NhomCon n WHERE n.sp_Nhom.idNhomSP = ?1")
	List<SP_NhomCon> getNhomCon(String idNhomSP);

	// Lấy nhóm con theo id nhóm con- shop
	@Query("FROM SP_NhomCon nc WHERE nc.idNhomSPCon = ?1")
	List<SP_NhomCon> getBCNhomCon(String idNhomSPCon);

	// Lấy nhóm con theo id sản phẩm- shop
	@Query(value = "SELECT nc.* FROM SP_NhomCon nc, SanPham s WHERE nc.IdNhomSPCon = s.IdNhomSPCon AND s.IdSanPham = ?1", nativeQuery = true)
	List<SP_NhomCon> getBCNhomConByIdSP(String idSanPham);

}
