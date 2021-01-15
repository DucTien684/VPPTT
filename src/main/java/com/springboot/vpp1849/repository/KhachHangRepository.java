package com.springboot.vpp1849.repository;

import com.springboot.vpp1849.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, String> {

	@Query("FROM KhachHang n WHERE n.email = ?1 AND n.matKhau = ?2")
	Optional<KhachHang> checkLogin(String email, String password);

	@Query(value = "SELECT COUNT(IdKhachHang) FROM KhachHang", nativeQuery = true)
	String countKH();

	// Danh sách khách hàng đã đăng ký
	List<KhachHang> findByNgayXoaNull();

	// Danh sách khách hàng đã xóa
	List<KhachHang> findByNgayXoaNotNull();

	// Danh sách khách hàng đã đăng ký
	@Query("FROM KhachHang k WHERE k.trangThai =1")
	List<KhachHang> findByKhachHangChuaDK();

	// Danh sách khách hàng đã đăng ký
	@Query("FROM KhachHang k WHERE k.trangThai =2")
	List<KhachHang> findByKhachHangDK();

	KhachHang findByIdKhachHang(String idKhachHang);

	// Check register
	KhachHang findByEmail(String email);

	KhachHang findBySdt(String sdt);

	// check update KH
	KhachHang findByIdKhachHangAndMatKhau(String idKhachHang, String matKhau);

	// Tổng khách hàng chưa xóa index
	@Query(value = "SELECT COUNT(IdKhachHang) FROM KhachHang WHERE NgayXoa IS NULL", nativeQuery = true)
	String countKhachHang();

	// Tổng khách hàng chưa đăng ký
	@Query(value = "SELECT COUNT(IdKhachHang) FROM KhachHang WHERE TrangThai = 1", nativeQuery = true)
	String countKhachHangChuaDangKy();

	// Tổng khách hàng đã đăng ký
	@Query(value = "SELECT COUNT(IdKhachHang) FROM KhachHang WHERE TrangThai = 2", nativeQuery = true)
	String countKhachHangDangKy();

	// Tổng khách hàng đã xóa
	@Query(value = "SELECT COUNT(IdKhachHang) FROM KhachHang WHERE NgayXoa IS NOT NULL", nativeQuery = true)
	String countKhachHangDaXoa();
}
