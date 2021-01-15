package com.springboot.vpp1849.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.vpp1849.model.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
	@Query(value = "SELECT COUNT(IdNhanVien) FROM NhanVien", nativeQuery = true)
	String countNhanVien();

	NhanVien getSpByIdNhanVien(String idNhanVien);

	// Tổng nhân viên chưa xóa - index
	@Query(value = "SELECT COUNT(IdNhanVien) FROM NhanVien WHERE NgayXoa IS NULL", nativeQuery = true)
	String countNhanVienCX();

	// Tổng nhân viên đã xóa
	@Query(value = "SELECT COUNT(IdNhanVien) FROM NhanVien WHERE NgayXoa IS NOT NULL", nativeQuery = true)
	String countNhanVienDaXoa();

	@Query(value = "FROM NhanVien s WHERE s.ngayXoa IS NULL")
	List<NhanVien> getAllNV();

	@Query("FROM NhanVien n WHERE n.email = ?1 AND n.matKhau = ?2")
	Optional<NhanVien> checkLogin(String email, String password);

	NhanVien findByIdNhanVien(String idNhanVien);

	// Check update NhanVien
	NhanVien findByIdNhanVienAndMatKhau(String idNhanVien, String matKhau);

	// Check Email
	NhanVien findByEmail(String email);
}
