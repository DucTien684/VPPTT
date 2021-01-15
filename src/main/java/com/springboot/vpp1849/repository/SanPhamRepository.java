package com.springboot.vpp1849.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.vpp1849.model.SanPham;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, String> {

	// Tìm kiếm sản phẩm theo tên sản phẩm và tên danh mục - Shop
	@Query("FROM SanPham s WHERE s.tenSP LIKE %?1% AND s.sp_NhomCon.sp_Nhom.idNhomSP = ?2 AND s.ngayXoa IS NULL")
	Page<SanPham> searchSPByIdNhomAndTenSP(String tenSP, String idNhom, Pageable pageable);

	// Tìm kiếm sản phẩm theo tên sản phẩm - Shop
	@Query("FROM SanPham s WHERE s.tenSP LIKE %?1% AND s.ngayXoa IS NULL")
	Page<SanPham> searchSPByTenSP(String tenSP, Pageable pageable);

	// Tất cả sản phẩm - shop
	@Query("FROM SanPham s WHERE s.ngayXoa IS NULL")
	List<SanPham> getAllSP();

	@Query("FROM SanPham s WHERE s.ngayXoa IS NULL")
	Page<SanPham> getAllSP(Pageable pageable);

	// Sản phẩm mới - index
	@Query(value = "SELECT Top 10 * FROM SanPham s WHERE s.NgayXoa IS NULL ORDER BY s.NgayDang DESC", nativeQuery = true)
	List<SanPham> getSPMoi();

	// Sản bán chạy - index
	@Query(value = "SELECT Top 10 * FROM SanPham s LEFT JOIN DH_ChiTiet h ON s.IdSanPham  =  h.IdSanPham WHERE s.NgayXoa IS NULL ORDER BY h.SoLuong DESC", nativeQuery = true)
	List<SanPham> getSPBanChay();

	// Sản phẩm theo danh mục - index
	@Query("FROM SanPham s WHERE s.sp_NhomCon.sp_Nhom.idNhomSP = ?1 AND s.ngayXoa IS NULL Order by s.ngayDang DESC")
	List<SanPham> getSPTheoDM(String id, Pageable pageable);

	// Sản phẩm theo nhóm - Shop
	@Query("FROM SanPham s WHERE s.sp_NhomCon.sp_Nhom.idNhomSP = ?1 AND s.ngayXoa IS NULL")
	Page<SanPham> getSPByIdNhom(String idNhom, Pageable pageable);

	// Sản phẩm đặc sắc - Shop
	@Query(value = "SELECT Top 3 * FROM SanPham s LEFT JOIN DH_ChiTiet h ON s.IdSanPham  =  h.IdSanPham WHERE s.NgayXoa IS NULL ORDER BY h.SoLuong DESC", nativeQuery = true)
	List<SanPham> getSPDacSac();

	// Sản phẩm theo danh mục - Shop
	@Query("FROM SanPham s WHERE s.sp_NhomCon.idNhomSPCon = ?1 AND s.ngayXoa IS NULL Order by s.ngayDang DESC")
	List<SanPham> getSPByIdNhomCon(String idNhomSPCon);

	@Query("FROM SanPham s WHERE s.sp_NhomCon.idNhomSPCon = ?1 AND s.ngayXoa IS NULL")
	Page<SanPham> getSPByIdNhomCon(String idNhomCon, Pageable pageable);

	// Lấy sản phẩm theo Id - Product
	List<SanPham> findByIdSanPham(String idSanPham);

	// Sản phẩm tương tự theo Id - Product
	@Query(value = "SELECT TOP 10 s.* FROM SanPham s, SP_NhomCon nc WHERE s.IdNhomSPCon = nc.IdNhomSPCon AND s.IdNhomSPCon IN"
			+ "(SELECT s.IdNhomSPCon FROM SanPham s WHERE s.IdSanPham = ?1) AND s.NgayXoa IS NULL", nativeQuery = true)
	List<SanPham> getSPTuongTu(String idSanPham);

	// Sản phẩm theo Ngày Xóa Not Null
	List<SanPham> findByNgayXoaNotNull();

	// Sản phẩm theo ngày xóa Null
	List<SanPham> findByNgayXoaNull();

	// Sản phẩm theo giá tiền - Shop
	@Query("FROM SanPham s WHERE s.ngayXoa IS NULL AND s.giaTien >= :giaTien1 AND s.giaTien <= :giaTien2")
	Page<SanPham> getSPByGiaTien(Double giaTien1, Double giaTien2, Pageable pageable);

	@Query("FROM SanPham s WHERE s.ngayXoa IS NULL AND s.giaTien <= :giaTien1")
	Page<SanPham> getSPByGiaTien1(Double giaTien1, Pageable pageable);

	@Query("FROM SanPham s WHERE s.ngayXoa IS NULL AND s.giaTien >= :giaTien2")
	Page<SanPham> getSPByGiaTien2(Double giaTien2, Pageable pageable);

	// Tổng sản phẩm
	@Query(value = "SELECT COUNT(IdSanPham) FROM SanPham", nativeQuery = true)
	String countSP();

	// Update Sản phẩm
	@Query(value = "FROM SanPham s WHERE s.idSanPham =?1")
	SanPham getSpByIdSanPham(String idSanPham);

	// Get lớp sản phẩm theo id Sản phẩm
	@Query("FROM SanPham s WHERE s.idSanPham = ?1")
	SanPham getSPByIdSP(String idSP);

	// Tổng sản phẩm
	@Query(value = "SELECT COUNT(IdSanPham) FROM SanPham WHERE NgayXoa IS NULL")
	String countSanPham();

	// Tổng sản phẩm đã xóa
	@Query(value = "SELECT COUNT(IdSanPham) FROM SanPham WHERE NgayXoa IS NOT NULL")
	String countSanPhamDaXoa();

}
