package com.springboot.vpp1849.service.impl;

import com.springboot.vpp1849.model.DTO.DonHangDTO;
import com.springboot.vpp1849.model.DTO.DonHangDTOs;
import com.springboot.vpp1849.model.DH_ChiTiet;
import com.springboot.vpp1849.model.DonHang;
import com.springboot.vpp1849.model.KhachHang;
import com.springboot.vpp1849.model.SanPham;
import com.springboot.vpp1849.repository.DH_ChiTietRepository;
import com.springboot.vpp1849.repository.DonHangRepository;
import com.springboot.vpp1849.repository.KhachHangRepository;
import com.springboot.vpp1849.service.DonHangService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DonHangServiceImpl implements DonHangService {

	@Autowired
	private DonHangRepository donHangRepository;

	@Autowired
	private KhachHangRepository khachHangRepository;

	@Autowired
	private DH_ChiTietRepository dh_ChiTietRepository;

	@Override
	public List<DonHangDTO> findAll(String id) throws ParseException {
		List<DonHang> result = donHangRepository.getDonHangByUser(id);
		List<DonHangDTO> finalList = new ArrayList<>();
		for (DonHang item : result) {
			List<DH_ChiTiet> tenSPList = donHangRepository.getListChiTietByIdDonHang(item.getIdDonHang());
			String tenSP = tenSPList.get(0).getSanPham().getTenSP();
			if (tenSPList.size() > 1) {
				int size = tenSPList.size() - 1;
				tenSP += " và " + size + " sản phẩm";
			}
			String trangThai = "Hàng trong kho!";
			if (item.getTrangThai() == 1) {
				trangThai = "Giao hàng thành công!";
			}
			if (item.getTrangThai() == 2) {
				trangThai = "Giao hàng thất bại!";
			}
			if (item.getTrangThai() == 3) {
				trangThai = "Đang chờ xác nhận!";
			}
			if (item.getTrangThai() == 4) {
				trangThai = "Đang chờ giao hàng!";
			}
			;
			finalList.add(new DonHangDTO(item.getIdDonHang(), item.getNgayLapDH(), tenSP, (float) item.getTongSoTien(),
					trangThai));
		}
		return finalList;
	}

	// Tìm tất cả DonHang
	@Override
	public List<DonHang> findAllDonHang() {
		return donHangRepository.findAllDonHang();
	}

	// Tìm tất cả DonHang có trạng thái là 3 ( Đang chờ)
	@Override
	public List<DonHang> findAllDonHangDangCho() {
		return donHangRepository.findAllDonHangDangCho();
	}

	// Tìm tất cả DonHang có trạng thái là 4 ( Đã xác nhận)
	@Override
	public List<DonHang> findAllDonHangDaXacNhan() {
		return donHangRepository.findAllDonHangDaXacNhan();
	}

	// Tìm tất cả DonHang có trạng thái là 1 ( Giao thành công)
	@Override
	public List<DonHang> findAllDonHangGiaoThanhCong() {
		return donHangRepository.findAllDonHangGiaoThanhCong();
	}

	// Tìm tất cả DonHang có trạng thái là 2 (Giao thất bại)
	@Override
	public List<DonHang> findAllDonHangGiaoThatBai() {
		return donHangRepository.findAllDonHangGiaoThatBai();
	}

	// Xác nhận DonHang
	@Override
	public DonHang xacNhanDonHang(String idDonHang) {
		DonHang dhUpdate = null;
		if (donHangRepository.existsById(idDonHang)) {
			DonHang dh = donHangRepository.getOne(idDonHang);
			dh.setTrangThai(4);
			dhUpdate = donHangRepository.saveAndFlush(dh);
		}
		return dhUpdate;
	}

	// Tiếp nhận DonHang
	@Override
	public DonHang tiepNhanDonHang(String idDonHang) {
		DonHang dhUpdate = null;
		if (donHangRepository.existsById(idDonHang)) {
			DonHang dh = donHangRepository.getOne(idDonHang);
			dh.setTrangThai(1);
			dhUpdate = donHangRepository.saveAndFlush(dh);
		}
		return dhUpdate;
	}

	// DonHang giao thất bại
	@Override
	public DonHang tuChoiDonHang(String idDonHang) {
		DonHang dhUpdate = null;
		if (donHangRepository.existsById(idDonHang)) {
			DonHang dh = donHangRepository.getOne(idDonHang);
			dh.setTrangThai(2);
			dhUpdate = donHangRepository.saveAndFlush(dh);
		}
		return dhUpdate;
	}

	@Override
	public String findTenSPByIdDonHang(String idDonHang) {
		return donHangRepository.findTenSPByIdDonHang(idDonHang);
	}

	@Override
	public DonHangDTOs findByIdDonHang(String idDonHang) {
		DonHang donHang = donHangRepository.findByIdDonHang(idDonHang);
		KhachHang khachHang = khachHangRepository.findByIdKhachHang(donHang.getKhachHang().getIdKhachHang());
		DH_ChiTiet dh_ChiTiet = dh_ChiTietRepository
				.findByIdDonHangChiTiet(donHang.getDh_ChiTiet().get(0).getIdDonHangChiTiet());
		String tenSP = donHangRepository.findTenSPByIdDonHang(idDonHang);
		DonHangDTOs donHangDTOs = new DonHangDTOs();
		donHangDTOs.setTenSP(tenSP);
		donHangDTOs.setDonHang(donHang);
		donHangDTOs.setKhachHang(khachHang);
		donHangDTOs.setDh_ChiTiet(dh_ChiTiet);
		return donHangDTOs;
	}

	@Override
	public DonHang addDonHang(DonHang donHang) {
		return donHangRepository.save(donHang);
	}

	@Override
	public String countDonHang() {
		int tsDH = Integer.parseInt(donHangRepository.countDonHang());
		String idDH = "";
		if (tsDH < 1000) {
			idDH = "DH0" + String.valueOf(tsDH + 1);
		} else if (tsDH >= 1000) {
			idDH = "DH" + String.valueOf(tsDH + 1);
		}
		return idDH;
	}

	// Tổng DonHang mới
	@Override
	public String countDonHangDangCho() {
		String tongDonHangDangCho = donHangRepository.countDonHangDangCho();
		return tongDonHangDangCho;
	}

	// Tổng DonHang giao thành công
	@Override
	public String countDonHangThanhCong() {
		String tongDonHangThanhCong = donHangRepository.countDonHangThanhCong();
		return tongDonHangThanhCong;
	}

	// Tổng DonHang giao thất bại
	@Override
	public String countDonHangThatBai() {
		String tongDonHangThatBai = donHangRepository.countDonHangThatBai();
		return tongDonHangThatBai;
	}

	// Tổng tình trạng DonHang
	@Override
	public String countDonHangXacNhan() {
		String tongDonHangXacNhan = donHangRepository.countDonHangXacNhan();
		return tongDonHangXacNhan;
	}

	@Override
	public List<DonHang> findAllLichSuDonHang() {
		return donHangRepository.findAllLichSuDonHang();
	}

	@Override
	public DonHang deleteDHCT(String idDonHang) {
		DonHang deleteDHCT = null;
		if (donHangRepository.existsById(idDonHang)) {
			DonHang donHang = donHangRepository.findByIdDH(idDonHang);
			donHang.setNgayXoa(new Date());
			deleteDHCT = donHangRepository.saveAndFlush(donHang);
		}
		return deleteDHCT;
	}

	@Override
	public DonHang find(String idDonHang) {
		return donHangRepository.getSPByIdDH(idDonHang);
	}
	

}
