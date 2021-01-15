package com.springboot.vpp1849.service;

import java.util.List;

import com.springboot.vpp1849.model.NhaCungCap;

public interface NhaCungCapService {
	public List<NhaCungCap> findNCCAll();

	String countNCC();

	NhaCungCap addNCC(NhaCungCap nhaCungCap);

	//    @Query(value="Delete FROM NhaCungCap n WHERE n.idNCC = ?1")
	NhaCungCap deleteDateNCC(String idNCC);

	NhaCungCap findByIdNCC(String idNCC);

	NhaCungCap updateNCC(NhaCungCap nhaCungCap);

	// Tổng nhà cung cấp chưa xóa - index
	String countNCCCX();

	String countNCCDaXoa();

	List<NhaCungCap> getNCCNgayXoaNotNull();

}