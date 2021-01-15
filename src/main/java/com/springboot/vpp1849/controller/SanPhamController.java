package com.springboot.vpp1849.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.vpp1849.model.SanPham;
import com.springboot.vpp1849.service.SanPhamService;

@RestController
//@RequestMapping("shops")
public class SanPhamController {

	@Autowired
	private SanPhamService sanPhamService;

	@GetMapping("search")
	public Page<SanPham> showSPByIdNhom(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "12") int size,
			@RequestParam(name = "giaTien1", defaultValue = "0") Double giaTien1,
			@RequestParam("giaTien2") Double giaTien2) {
		Sort sort = Sort.by("ngayDang").descending();
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		return sanPhamService.getSPByGiaTien(giaTien1, giaTien2, pageRequest);
	}
}
