package com.springboot.vpp1849.repository;

import com.springboot.vpp1849.model.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, String> {
	// Tổng nhà cung cấp - chung(đã xóa + chưa xóa)
	@Query(value = "SELECT COUNT(IdNCC) FROM NhaCungCap", nativeQuery = true)
	String countNCC();

	NhaCungCap findByIdNCC(String idNCC);

	// Tổng nhà cung cấp chưa xóa - index
	@Query(value = "SELECT COUNT(IdNCC) FROM NhaCungCap WHERE NgayXoa IS NULL", nativeQuery = true)
	String countNCCCX();

	// Tổng nhà cung cấp đã xóa
	@Query(value = "SELECT COUNT(IdNCC) FROM NhaCungCap WHERE NgayXoa IS NOT NULL", nativeQuery = true)
	String countNCCDaXoa();

	List<NhaCungCap> findByNgayXoaNotNull();

	@Query(value = "FROM NhaCungCap s WHERE s.ngayXoa IS NULL")
	List<NhaCungCap> getAllNCC();
}
