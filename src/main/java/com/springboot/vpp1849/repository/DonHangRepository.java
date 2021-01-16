package com.springboot.vpp1849.repository;

import com.springboot.vpp1849.model.DH_ChiTiet;
import com.springboot.vpp1849.model.DonHang;
import com.springboot.vpp1849.model.SanPham;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, String> {
	// Lấy đơn hàng theo id người dùng
	@Query(value = "SELECT new com.springboot.vpp1849.model.DonHang(dh.idDonHang, dh.ngayLapDH, dh.tongSoTien, dh.trangThai) FROM DonHang dh WHERE idkhachHang=?1 AND dh.ngayXoa IS NULL")
	List<DonHang> getDonHangByUser(String idNguoiDung);

	// Lấy đơn hàng chi tiết theo id đơn hàng
	@Query(value = "SELECT c FROM DH_ChiTiet c WHERE idDonHang= ?1")
	List<DH_ChiTiet> getListChiTietByIdDonHang(String idDonHang);

	@Query(value = "SELECT * FROM DonHang", nativeQuery = true)
	List<DonHang> findAllDonHang();

	@Query("FROM DonHang dh WHERE dh.trangThai = 1 OR dh.trangThai = 2")
	List<DonHang> findAllLichSuDonHang();

	@Query("FROM DonHang dh WHERE dh.trangThai = 1 AND ngayXoa IS NULL")
	List<DonHang> findAllDonHangGiaoThanhCong();

	@Query("FROM DonHang dh WHERE dh.trangThai = 2")
	List<DonHang> findAllDonHangGiaoThatBai();

	@Query("FROM DonHang dh WHERE dh.trangThai = 3 AND dh.ngayXoa IS NULL")
	List<DonHang> findAllDonHangDangCho();

	@Query("FROM DonHang dh WHERE dh.trangThai = 4")
	List<DonHang> findAllDonHangDaXacNhan();

	@Query(value = "SELECT dh.*, dhc.SoLuong, dhc.DonGia, sp.tenSP  FROM DonHang dh, DH_ChiTiet dhc, SanPham sp where dh.IdDonHang = dhc.IdDonHang AND dhc.IdSanPham = sp.IdSanPham AND dh.TrangThai = 1", nativeQuery = true)
	List<DonHang> findByTest();

	@Query(value = "SELECT s.TenSP FROM SanPham s, DH_ChiTiet ct, DonHang dh "
			+ "WHERE  s.IdSanPham = ct.IdSanPham AND ct.IdDonHang = dh.IdDonHang AND dh.IdDonHang = ?1", nativeQuery = true)
	String findTenSPByIdDonHang(String idDonHang);

	DonHang findByIdDonHang(String idDonHang);

	// Tổng DonHang
	@Query(value = "SELECT COUNT(IdDonHang) FROM DonHang", nativeQuery = true)
	String countDonHang();

	// Tổng DonHang giao thành công
	@Query(value = "SELECT COUNT(IdDonHang) FROM DonHang WHERE TrangThai = 1 AND ngayXoa IS NULL", nativeQuery = true)
	String countDonHangThanhCong();

	// Tổng DonHang giao thất bại
	@Query(value = "SELECT COUNT(IdDonHang) FROM DonHang WHERE TrangThai = 2", nativeQuery = true)
	String countDonHangThatBai();

	// Tổng DonHang mới
	@Query(value = "SELECT COUNT(IdDonHang) FROM DonHang WHERE TrangThai = 3 AND ngayXoa IS NULL", nativeQuery = true)
	String countDonHangDangCho();

	// Tổng tình trạng đơn hàng
	@Query(value = "SELECT COUNT(IdDonHang) FROM DonHang WHERE TrangThai = 4", nativeQuery = true)
	String countDonHangXacNhan();
	
	@Query(value = "FROM DonHang dh WHERE dh.idDonHang = ?1 AND dh.trangThai = 3")
	DonHang findByIdDH(String idDonHang);
	
	// Get lớp sản phẩm theo id Sản phẩm
		@Query("FROM DonHang s WHERE s.idDonHang = ?1")
		DonHang getSPByIdDH(String idDH);

}
