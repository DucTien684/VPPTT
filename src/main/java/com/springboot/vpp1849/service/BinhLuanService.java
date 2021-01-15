package com.springboot.vpp1849.service;

import java.util.List;

import com.springboot.vpp1849.URL.custom.CustomBinhLuan;
import com.springboot.vpp1849.model.BinhLuan;

public interface BinhLuanService {

	String getBLSanPham(String idSanPham);

	List<CustomBinhLuan> getBinhLuan(String idSanPham);

	String countBL();

	BinhLuan addBinhLuan(BinhLuan binhLuan);

}
