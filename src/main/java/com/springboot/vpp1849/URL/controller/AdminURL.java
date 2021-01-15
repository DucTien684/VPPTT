package com.springboot.vpp1849.URL.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.vpp1849.URL.custom.CustomBinhLuan;
import com.springboot.vpp1849.URL.custom.CustomChiTietDonHang;
import com.springboot.vpp1849.model.BinhLuan;
import com.springboot.vpp1849.model.DH_ChiTiet;
import com.springboot.vpp1849.model.DonHang;
import com.springboot.vpp1849.model.KhachHang;
import com.springboot.vpp1849.model.NV_ChucVu;
import com.springboot.vpp1849.model.NhaCungCap;
import com.springboot.vpp1849.model.NhanVien;
import com.springboot.vpp1849.model.SP_Nhom;
import com.springboot.vpp1849.model.SP_NhomCon;
import com.springboot.vpp1849.model.SanPham;
import com.springboot.vpp1849.model.DTO.DonHangDTOs;
import com.springboot.vpp1849.service.impl.DH_ChiTietServiceImpl;
import com.springboot.vpp1849.service.impl.DonHangServiceImpl;
import com.springboot.vpp1849.service.impl.KhachHangServiceImpl;
import com.springboot.vpp1849.service.impl.NV_ChucVuServiceImpl;
import com.springboot.vpp1849.service.impl.NhaCungCapServiceImpl;
import com.springboot.vpp1849.service.impl.NhanVienServiceImpl;
import com.springboot.vpp1849.service.impl.SP_NhomConServiceImpl;
import com.springboot.vpp1849.service.impl.SP_NhomServiceImpl;
import com.springboot.vpp1849.service.impl.SanPhamServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminURL {
	private static String UPLOADED_FOLDER = "C:\\Users\\dell\\Desktop\\VPP\\vanphongphamthinhvuong\\src\\main\\resources\\static\\user\\img\\upload\\product";
	private static String UPLOADED_FOLDER_NV = "C:\\Users\\dell\\Desktop\\VPP\\vanphongphamthinhvuong\\src\\main\\resources\\static\\admin\\img\\upload\\employeeAvatar";

	@Autowired
	SanPhamServiceImpl sanPhamService;

	@Autowired
	SP_NhomConServiceImpl spNhomConService;

	@Autowired
	SP_NhomServiceImpl spNhomService;

	@Autowired
	KhachHangServiceImpl khachHangService;

	@Autowired
	NhanVienServiceImpl nhanVienService;

	@Autowired
	DonHangServiceImpl donHangService;

	@Autowired
	DH_ChiTietServiceImpl dh_ChiTietService;

	@Autowired
	private ServletContext context;

	@Autowired
	NhaCungCapServiceImpl nhaCungCapService;

	@Autowired
	NV_ChucVuServiceImpl nvChucVuService;

	@Autowired
	public JavaMailSender emailSender;

	/*
	 * @GetMapping("") public String index() { return "redirect:/admin/index"; }
	 */

	@GetMapping("/index")
	public String index1(Model model) {
		sidaBarAdmin(model);
		return "admin/index";
	}

	// Excel and Pdf
	@GetMapping("/createPdfSanPham")
	public void createPdfSanPham(HttpServletRequest request, HttpServletResponse response) {
		List<SanPham> sanPhams = sanPhamService.getAllSP();
		boolean isFlag = sanPhamService.createPdfSanPham(sanPhams, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "sanPham" + ".pdf");
			filedownload(fullPath, response, "sanPham.pdf");
		}
	}

	@GetMapping("/createExcelSanPham")
	public void createExcelSanPham(HttpServletRequest request, HttpServletResponse response) {
		List<SanPham> sanPhams = sanPhamService.getAllSP();
		boolean isFlag = sanPhamService.createExcelSanPham(sanPhams, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "sanPham" + ".xls");
			filedownload(fullPath, response, "sanPham.xls");
		}
	}

	@GetMapping("/createPdfSanPhamXoa")
	public void createPdfSanPhamXoa(HttpServletRequest request, HttpServletResponse response) {
		List<SanPham> sanPhamDaXoa = sanPhamService.getSPNgayXoaNotNull();
		boolean isFlag = sanPhamService.createPdfSanPhamDaXoa(sanPhamDaXoa, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "sanPhamDaXoa" + ".pdf");
			filedownload(fullPath, response, "sanPhamDaXoa.pdf");
		}
	}

	@GetMapping("/createExcelSanPhamXoa")
	public void createExcelSanPhamXoa(HttpServletRequest request, HttpServletResponse response) {
		List<SanPham> sanPhamDaXoa = sanPhamService.getSPNgayXoaNotNull();
		boolean isFlag = sanPhamService.createExcelSanPham(sanPhamDaXoa, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "sanPhamDaXoa" + ".xls");
			filedownload(fullPath, response, "sanPhamDaXoa.xls");
		}
	}

	private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + fileName);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	// End

	@GetMapping("/sanPham")
	public String sanPham(Model model) {
		List<SanPham> listSP = sanPhamService.getAllSP();
		model.addAttribute("listSP", listSP);
		sidaBarAdmin(model);
		return "admin/sanPham/sanPham";
	}

	@GetMapping("/sanPhamXoa")
	public String sanPhamXoa(Model model) {
		List<SanPham> listSPXoa = sanPhamService.getSPNgayXoaNotNull();
		model.addAttribute("listSPXoa", listSPXoa);
		sidaBarAdmin(model);
		return "admin/sanPham/sanPhamXoa";
	}

	@PostMapping("/delete/{id}")
	@ResponseBody
	public String deleteSP(@PathVariable("id") String idSanPham) {
		return sanPhamService.deleteDateSP(idSanPham) != null ? "1" : "0";
	}

	@GetMapping("/addSP")
	public String getFormAddSP(Model model, String idNhomSP) {
		List<SP_Nhom> listNhomSp = spNhomService.getAllNhom();
		model.addAttribute("listNhom", listNhomSp);

		List<SP_NhomCon> listSpNhomCon = spNhomConService.getNhomCon(listNhomSp.get(0).getIdNhomSP());
		model.addAttribute("listNhomSpCon", listSpNhomCon);

		List<NhanVien> listNhanVien = nhanVienService.findAllNV();
		model.addAttribute("listNhanVien", listNhanVien);

		List<NhaCungCap> listNCC = nhaCungCapService.findNCCAll();
		model.addAttribute("listNCC", listNCC);

		model.addAttribute("sanPham", new SanPham());
		sidaBarAdmin(model);

		return "admin/sanPham/add-sp";
	}

	@PostMapping("/addSP")
	public String addSP(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
			HttpServletRequest request, @ModelAttribute SanPham sanPham, BindingResult bindingResult, Model model) {
		String hinh = "upload/product/";
		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");
			System.out.println("Abc");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Date date = new Date();
		sanPham.setIdSanPham(sanPhamService.customIdSanPham());
		sanPham.setMaSP(sanPhamService.customMaSP());
		sanPham.setNgayDang(date);
		sanPham.setNgayCapNhat(null);
		sanPham.setHinhAnh(hinh + file.getOriginalFilename());
		sanPham.setNgayXoa(null);

		sanPhamService.addSP(sanPham);
		redirectAttributes.addFlashAttribute("addSuccess", true);
		return "redirect:/admin/sanPham";

	}

	@GetMapping("/getNhomCon")
	@ResponseBody
	public List<SP_NhomCon> getNhomConSPByIdNhomSP(@RequestParam String idNhomSP) {
		List<SP_NhomCon> listSpNhomCon = spNhomConService.getNhomCon(idNhomSP);
		return listSpNhomCon;
	}

	@GetMapping("/updateSP/{idSanPham}")
	public String getFormUpdateSP(@PathVariable("idSanPham") String idSanPham, Model model, String idNhomSP) {
		SanPham sp = sanPhamService.getSpByIdSanPham(idSanPham);
		model.addAttribute("sanPham", sp);

		String spNhomDefault = sp.getSp_NhomCon().getSp_Nhom().getIdNhomSP();
		model.addAttribute("spNhomDefault", spNhomDefault);

		String spNhomConDefault = sp.getSp_NhomCon().getIdNhomSPCon();
		model.addAttribute("spNhomConDefault", spNhomConDefault);

		List<SP_Nhom> listNhomSp = spNhomService.getAllNhom();
		model.addAttribute("listNhom", listNhomSp);

		List<SP_NhomCon> listSpNhomCon = spNhomConService.getNhomCon(listNhomSp.get(0).getIdNhomSP());
		model.addAttribute("listNhomSpCon", listSpNhomCon);

		List<NhaCungCap> listNCC = nhaCungCapService.findNCCAll();
		model.addAttribute("listNCC", listNCC);

		sidaBarAdmin(model);

		return "admin/sanPham/update-sp";
	}

	@PostMapping("/updateSP/{idSanPham}")
	public String updateSP(@PathVariable("idSanPham") String idSanPham, @RequestParam("file") MultipartFile file,
			@ModelAttribute SanPham sanPham, BindingResult result, RedirectAttributes rd) {
		SanPham sp = sanPhamService.getSpByIdSanPham(idSanPham);
		Date date = new Date();
		String hinh = "upload/product/";
		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			rd.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
			System.out.println("!!!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		sp.setTenSP(sanPham.getTenSP());
		sp.setHinhAnh(hinh + file.getOriginalFilename());
		sp.setSp_NhomCon(sanPham.getSp_NhomCon());
		sp.setThuongHieu(sanPham.getThuongHieu());
		sp.setSanXuat(sanPham.getSanXuat());
		sp.setGiaTien(sanPham.getGiaTien());
		sp.setSoLuongConLai(sanPham.getSoLuongConLai());
		sp.setDonViTinh(sanPham.getDonViTinh());
		sp.setNgayCapNhat(date);
		sp.setThongTin(sanPham.getThongTin());
		sp.setNhaCungCap(sanPham.getNhaCungCap());
		sanPhamService.updateSP(sp);
		rd.addFlashAttribute("updateSuccess", true);
		return "redirect:/admin/updateSP/{idSanPham}";
	}

	@GetMapping("/khachHang")
	public String getFormKhachHang(Model model) {
		List<KhachHang> listKhachHangs = khachHangService.findByNgayXoaNull();
		model.addAttribute("listKhachHang", listKhachHangs);
		sidaBarAdmin(model);

		return "admin/khachHang/khachHang";
	}

	@GetMapping("/khachHangXoa")
	public String getFormKhachHangXoa(Model model) {
		List<KhachHang> listKhachHangXoa = khachHangService.findByNgayXoaNotNull();
		model.addAttribute("listKhachHangXoa", listKhachHangXoa);
		sidaBarAdmin(model);

		return "admin/khachHang/khachHangXoa";
	}

	@GetMapping("/khachHangCDK")
	public String getFormKhachHangChuaDK(Model model) {
		List<KhachHang> listKhachHangChuaDK = khachHangService.findByKhachHangChuaDK();
		model.addAttribute("listKhachHangChuaDK", listKhachHangChuaDK);
		sidaBarAdmin(model);

		return "admin/khachHang/khachHangChuaDK";
	}

	@GetMapping("/khachHangDK")
	public String getFormKhachHangDK(Model model) {
		List<KhachHang> listKhachHangDK = khachHangService.findByKhachHangDK();
		model.addAttribute("listKhachHangDK", listKhachHangDK);
		sidaBarAdmin(model);

		return "admin/khachHang/khachHangDK";
	}

	@GetMapping("/nhanVien")
	public String getFormNhanVien(Model model) {
		List<NhanVien> listNhanVien = nhanVienService.findNVAll();
		model.addAttribute("listNhanVien", listNhanVien);
		sidaBarAdmin(model);

		return "admin/nhanVien/nhanVien";
	}

	@GetMapping("/updateNV/{idNhanVien}")
	public String getFormUpdateNV(@PathVariable("idNhanVien") String idNhanVien, @ModelAttribute NhanVien nhanVien,
			Model model) {
		NhanVien nv = nhanVienService.getSpByIdNhanVien(idNhanVien);
		model.addAttribute("nhanVien", nv);
		List<NV_ChucVu> listCV = nvChucVuService.findAllCV();
		model.addAttribute("listCV", listCV);
		sidaBarAdmin(model);

		return "admin/nhanVien/update-nv";
	}

	@PostMapping("/updateNV/{idNhanVien}")
	public String updateNV(@PathVariable("idNhanVien") String idNhanVien, @ModelAttribute NhanVien nhanVien,
			@RequestParam("file") MultipartFile file, BindingResult result, RedirectAttributes rd) {
		NhanVien nv = nhanVienService.getSpByIdNhanVien(idNhanVien);
		String hinh = "upload/employeeAvatar/";
		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER_NV + file.getOriginalFilename());
			Files.write(path, bytes);

			rd.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
		} catch (IOException e) {
			e.printStackTrace();
		}
		nv.setIdNhanVien(nhanVien.getIdNhanVien());
		nv.setHoTen(nhanVien.getHoTen());
		nv.setGioiTinh(nhanVien.getGioiTinh());
		nv.setNgaySinh(nhanVien.getNgaySinh());
		nv.setDiaChi(nhanVien.getDiaChi());
		nv.setSdt(nhanVien.getSdt());
		nv.setAnh(hinh + file.getOriginalFilename());
		nv.setEmail(nhanVien.getEmail());
		nv.setMatKhau(nhanVien.getMatKhau());
		nv.setNv_ChucVu(nhanVien.getNv_ChucVu());
		nhanVienService.updateNhanVien(nv);
		rd.addFlashAttribute("updateSuccess", true);
		return "redirect:/admin/nhanVien/";
	}

	// Xóa Nhân viên
	@PostMapping("/deleteNV/{id}")
	@ResponseBody
	public String deleteNV(@PathVariable("id") String idNhanVien) {
		return nhanVienService.deleteDateNV(idNhanVien) != null ? "1" : "0";
	}

	@GetMapping("/donHang")
	public String getFormDonHang(Model model, DonHang donHang) {
		List<DonHang> listLichSu = donHangService.findAllLichSuDonHang();
		model.addAttribute("listLichSu", listLichSu);
		// Sidebar
		sidaBarAdmin(model);
		return "admin/donHang/donHang";
	}

	@GetMapping("/donHangDangCho")
	public String getFormDonHangDangCho(Model model, DonHang donHang) {
		List<DonHang> listDonHang = donHangService.findAllDonHangDangCho();
		model.addAttribute("listDonHangDangCho", listDonHang);
		sidaBarAdmin(model);
		return "admin/donHang/donHangDangcho";
	}

	@GetMapping("/donHangDaXacNhan")
	public String getFormDonHangDaXacNhan(Model model) {
		List<DonHang> listDonHang = donHangService.findAllDonHangDaXacNhan();
		model.addAttribute("listDonHangDaXacNhan", listDonHang);
		sidaBarAdmin(model);
		return "admin/donHang/donHangXacNhan";
	}

	@GetMapping("/donHangGiaoThanhCong")
	public String getFormDonHangGiaoThanhCong(Model model) {
		List<DonHang> listDonHang = donHangService.findAllDonHangGiaoThanhCong();
		model.addAttribute("listDonHangGiaoThanhCong", listDonHang);
		sidaBarAdmin(model);
		return "admin/donHang/donHangGiaoThanhCong";
	}

	@GetMapping("/donHangGiaoThatBai")
	public String getFormDonHangGiaoThatBai(Model model) {
		List<DonHang> listDonHang = donHangService.findAllDonHangGiaoThatBai();
		model.addAttribute("listDonHangGiaoThatBai", listDonHang);
		sidaBarAdmin(model);
		return "admin/donHang/donHangGiaoThatBai";
	}

	// Xóa khách hàng
	@PostMapping("/deleteKH/{id}")
	@ResponseBody
	public String deleteKH(@PathVariable("id") String idKhachHang) {
		return khachHangService.deleteDateKH(idKhachHang) != null ? "1" : "0";
	}

	// Chấp nhận đơn hàng
	@PostMapping("/xacNhanDonHang/{id}")
	@ResponseBody
	public String xacNhanDonHang(@PathVariable("id") String idDonHang) {
		return donHangService.xacNhanDonHang(idDonHang) != null ? "1" : "0";
	}

	// Tiếp nhận giao hàng
	@PostMapping("/tiepNhanDonHang/{id}")
	@ResponseBody
	public String tiepNhanDonHang(@PathVariable("id") String idDonHang) {
		return donHangService.tiepNhanDonHang(idDonHang) != null ? "1" : "0";
	}

	// Đơn hàng thất bại
	@PostMapping("/tuChoiDonHang/{id}")
	@ResponseBody
	public String tuChoiDonHang(@PathVariable("id") String idDonHang) {
		return donHangService.tuChoiDonHang(idDonHang) != null ? "1" : "0";
	}

	@GetMapping("khachHang/{idKhachHang}")
	@ResponseBody
	public KhachHang getkhachHangByID(@PathVariable("idKhachHang") String idKhachHang) {
		KhachHang kh = khachHangService.findByIdKhachHang(idKhachHang);
		return kh;
	}

	@GetMapping("donHang/{idDonHang}")
	@ResponseBody
	public DonHangDTOs getDonHangByID(@PathVariable("idDonHang") String idDonHang) {
		DonHangDTOs dh = donHangService.findByIdDonHang(idDonHang);
		return dh;
	}

	public void sidaBarAdmin(Model model) {
		// Tổng khách hàng - index
		String countKhachHang = khachHangService.countKhachHang();
		model.addAttribute("countKhachHang", countKhachHang);

		String countKhachHangChuaDangKy = khachHangService.countKhachHangChuaDangKy();
		model.addAttribute("countKhachHangChuaDangKy", countKhachHangChuaDangKy);

		String countKhachHangDangKy = khachHangService.countKhachHangDangKy();
		model.addAttribute("countKhachHangDangKy", countKhachHangDangKy);

		String countKhachHangDaXoa = khachHangService.countKhachHangDaXoa();
		model.addAttribute("countKhachHangDaXoa", countKhachHangDaXoa);

		// Tổng DonHang
		String dhDangCho = donHangService.countDonHangDangCho();
		model.addAttribute("dhDangCho", dhDangCho);

		String dhXacNhan = donHangService.countDonHangXacNhan();
		model.addAttribute("dhXacNhan", dhXacNhan);

		String dhThanhCong = donHangService.countDonHangThanhCong();
		model.addAttribute("dhThanhCong", dhThanhCong);

		String dhThatBai = donHangService.countDonHangThatBai();
		model.addAttribute("dhGiaoThatBai", dhThatBai);

		// Tổng sản phẩm
		String countSanPham = sanPhamService.countSanPham();
		model.addAttribute("countSanPham", countSanPham);

		String countSanPhamDaXoa = sanPhamService.countSanPhamDaXoa();
		model.addAttribute("countSanPhamDaXoa", countSanPhamDaXoa);

		// Tổng nhân viên chưa xóa - index
		String countNhanVien = nhanVienService.countNhanVienCX();
		model.addAttribute("countNhanVien", countNhanVien);

		// Tổng nhà cung cấp chưa xóa - index
		String countNCC = nhaCungCapService.countNCCCX();
		model.addAttribute("countNCC", countNCC);
	}

	// Thông tin tài khoản
	@GetMapping("/info")
	public String infoAdmin(Model model, HttpSession session) {
		NhanVien nv = (NhanVien) session.getAttribute("SESSION1");

		if (nv == null) {
			return "redirect:/admin/login";
		}

		return "admin/account/info";
	}

	@SuppressWarnings("unused")
	@PostMapping("/info")
	public String updateAccountInfoNV(HttpSession session, RedirectAttributes rd, HttpServletRequest req) {

		NhanVien nv = (NhanVien) session.getAttribute("SESSION1");

		String email = req.getParameter("email");
		String name = req.getParameter("hoTen");
		String diaChi = req.getParameter("diaChi");
		String sdt = req.getParameter("sdt");

		String oldPass = req.getParameter("mkcu");
		String newPass = req.getParameter("mkmoi");

		NhanVien checkNV = nhanVienService.findByIdNhanVienAndMatKhau(nv.getIdNhanVien(), oldPass);

		if (nv == null) {
			return "redirect:/login";
		}
		rd.addFlashAttribute("errorPass", "");

		if (oldPass != "") {
			if (checkNV == null) {
				rd.addFlashAttribute("errorPass", "Mật khẩu cũ không chính xác!");
				return "redirect:/admin/info";
			}
		}

		if (newPass != "") {
			nv.setMatKhau(newPass);
		}

		nv.setHoTen(name);
		nv.setDiaChi(diaChi);
		nv.setSdt(sdt);
		nhanVienService.updateNhanVien(nv);
		rd.addFlashAttribute("updateSuccess", true);

		return "redirect:/admin/info";
	}

	// View danh sách nhà cung cấp
	@GetMapping("/nhaCungCap")
	public String nhaCungCap(Model model) {
		List<NhaCungCap> listNCC = nhaCungCapService.findNCCAll();
		model.addAttribute("listNCC", listNCC);
		sidaBarAdmin(model);
		return "admin/nhaCungCap/nhaCungCap";
	}

	// Thêm nhà cung cấp
	@GetMapping("/addNCC")
	public String getFormAddNNC(Model model) {
		model.addAttribute("nhaCungCap", new NhaCungCap());
		sidaBarAdmin(model);
		return "admin/nhaCungCap/add-ncc";
	}

	@PostMapping("/addNCC")
	public String addNCC(@ModelAttribute NhaCungCap nhaCungCap) {
		nhaCungCap.setIdNCC(nhaCungCapService.customIdNhaCungCap());
		nhaCungCap.setNgayXoa(null);
		nhaCungCapService.addNCC(nhaCungCap);
		return "redirect:/admin/nhaCungCap";
	}

	// Sửa nhà cung cấp
	@GetMapping("/updateNCC/{idNCC}")
	public String getFormUpdateNCC(@PathVariable("idNCC") String idNCC, Model model) {
		NhaCungCap ncc = nhaCungCapService.findByIdNCC(idNCC);
		model.addAttribute("nhaCungCap", ncc);
		sidaBarAdmin(model);
		return "admin/nhaCungCap/update-ncc";
	}

	@PostMapping("/updateNCC/{idNCC}")
	public String updateNCC(@PathVariable("idNCC") String idNCC, @ModelAttribute NhaCungCap nhaCungCap) {
		NhaCungCap ncc = nhaCungCapService.findByIdNCC(idNCC);
		ncc.setIdNCC(nhaCungCap.getIdNCC());
		ncc.setTenNCC(nhaCungCap.getTenNCC());
		ncc.setDiaChi(nhaCungCap.getDiaChi());
		nhaCungCapService.updateNCC(ncc);
		return "redirect:/admin/updateNCC/{idNCC}";
	}

	// Xóa Nhà cung cấp
	@PostMapping("/deleteNCC/{id}")
	@ResponseBody
	public String deleteNCCC(@PathVariable("id") String idNCC) {
		return nhaCungCapService.deleteDateNCC(idNCC) != null ? "1" : "0";
	}

	// Thêm để view doanhThuSanPham
	@GetMapping("/doanhThuSanPham")
	public String DoanhThuSanPham(Model model) {
		return "admin/thongKe/doanhThuSanPham";
	}

	// Thêm nhân viên
	@GetMapping("/addNV")
	public String getFormAddNV(Model model) {
		List<NV_ChucVu> listCV = nvChucVuService.findAllCV();
		model.addAttribute("listCV", listCV);
		model.addAttribute("nhanVien", new NhanVien());
		sidaBarAdmin(model);
		return "admin/nhanVien/add-nv";
	}

	@PostMapping("/addNV")
	public String addNV(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
			HttpServletRequest request, @ModelAttribute NhanVien nhanVien, BindingResult bindingResult, Model model) {
		String hinh = "upload/employeeAvatar/";
		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER_NV + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");
		} catch (IOException e) {
			e.printStackTrace();
		}

		nhanVien.setIdNhanVien(nhanVienService.customIdNhanVien());
		nhanVien.setNgayXoa(null);
		nhanVien.setAnh(hinh + file.getOriginalFilename());
		nhanVienService.addNV(nhanVien);
		redirectAttributes.addFlashAttribute("addSuccess", true);
		return "redirect:/admin/nhanVien";
	}

	@GetMapping("")
	public String LoginAdmin() {
		return "redirect:/admin/login";
	}

	// View login
	@GetMapping("/login")
	public String LoginAdmin1(Model model) {
		return "admin/login";
	}

	// Login tạo session Người Dùng, dùng session lấy thông tin
	@PostMapping("/login")
	public String login(HttpSession session, RedirectAttributes rd, @ModelAttribute("email") String email,
			@ModelAttribute("pass") String pass) {
		Optional<NhanVien> nv = nhanVienService.checkLogin(email, pass);
		if (nv.isPresent()) {
			session.setAttribute("SESSION1", nv.get());
			return "redirect:/admin/index";
		}
		rd.addFlashAttribute("email", email);
		rd.addFlashAttribute("error", "Email hoặc mật khẩu không đúng!");

		return "redirect:/admin/login";
	}

	@PostMapping("/login-checkout1")
	public String login_checkout1(HttpSession session, RedirectAttributes rd, @ModelAttribute("emailCK") String emailCK,
			@ModelAttribute("pass") String pass, HttpServletRequest req) {

		Optional<NhanVien> nv = nhanVienService.checkLogin(emailCK, pass);
		if (nv.isPresent()) {
			session.setAttribute("SESSION1", nv.get());
			return "redirect:/checkout1";
		}
		rd.addFlashAttribute("errorLoginCheck", "Email hoặc mật khẩu không đúng!");

		return "redirect:/checkout1";
	}

	// Trả về trang chủ -> có vấn đề
	@GetMapping("/logout1")
	public String logout(HttpSession session) {
		session.removeAttribute("SESSION1");

		return "redirect:/admin/login";
	}

	// View 404
	@GetMapping("/404")
	public String AdminError(Model model) {
		return "admin/404";
	}

	// Thêm để view add-xnt
	@GetMapping("/addXNT")
	public String TaoXNT(Model model) {
		return "admin/xuatNhapTon/add-xnt";
	}

	// Thêm để view duyetThanhCong
	@GetMapping("/duyetTC")
	public String DuyetThanhCong(Model model) {
		return "admin/xuatNhapTon/duyetThanhCong";
	}

	// Thêm để view duyetThatBai
	@GetMapping("/duyetTB")
	public String DuyetThatBai(Model model) {
		return "admin/xuatNhapTon/duyetThatBai";
	}

	// Thêm để view xuatNhapTon
	@GetMapping("/XNT")
	public String XuatNhapTon(Model model) {
		return "admin/xuatNhapTon/xuatNhapTon";
	}

	/*
	 * @GetMapping("/donHangDangCho1") public String CTDH(Model
	 * model, @RequestParam("id") String id, BinhLuan binhLuan, HttpSession session)
	 * {
	 * 
	 * List<CustomChiTietDonHang> ctdhView = dh_ChiTietService.getDHChiTiet(id);
	 * 
	 * if (ctdhView.isEmpty()) { model.addAttribute("ctdhView", null); } else {
	 * model.addAttribute("ctdhView", ctdhView); }
	 * 
	 * return "admin/donHang/donHangDangCho"; }
	 */

	// Lấy lại mật khẩu
	@PostMapping("/forGotPassNV")
	public String forGotPassWordNV(HttpServletRequest reqNV, RedirectAttributes rdNV, String contentNV)
			throws MessagingException {

		MimeMessage messageNV = emailSender.createMimeMessage();

		boolean multipartNV = true;

		MimeMessageHelper helperNV = new MimeMessageHelper(messageNV, multipartNV, "UTF-8");

		String newPass = RandomStringUtils.randomAlphanumeric(6);

		NhanVien existNV = nhanVienService.getNVByEmail(reqNV.getParameter("emailForgot"));

		if (existNV == null) {
			rdNV.addFlashAttribute("errorEmail", "Email của bạn chưa được đăng ký!");
			return "redirect:/admin/login";
		}

		String htmlMsg = "<img src='https://i.imgur.com/ebgC6Ka.png'>" + "<p>Xin chào " + existNV.getHoTen() + "!</p>"
				+ "<p>Mật khẩu bạn cần để truy cập vào tài khoản quản lý trong Văn Phòng Phẩm Thịnh Vượng của mình "
				+ existNV.getEmail() + " là: " + "<b>" + newPass + "</b></p>"
				+ "<p>Nếu bạn không yêu cầu mật khẩu này thì có thể là ai đó đang tìm cách truy cập vào "
				+ "tài khoản Văn Phòng Phẩm Thịnh Vượng. Không chuyển tiếp hoặc cung cấp mật khẩu này cho bất kỳ ai. </p>"
				+ "<p>Trân trọng!</p>" + "<p>Văn Phòng Phẩm Thịnh Vượng.</p>";

		messageNV.setContent(htmlMsg, "text/html;charset=utf-8");

		helperNV.setTo(reqNV.getParameter("emailForgot"));

		helperNV.setSubject("Test send HTML email");

		// Send Message!
		this.emailSender.send(messageNV);

		existNV.setMatKhau(newPass);
		nhanVienService.updateNhanVien(existNV);

		rdNV.addFlashAttribute("sendMailToTakePassNV", true);

		return "redirect:/admin/login";

	}
	

	@GetMapping("/donhangdangcho1")
	public String product(Model model, @RequestParam("id") String id, DH_ChiTiet dhct, HttpSession session) {


		List<CustomChiTietDonHang> ctdhView = dh_ChiTietService.getDHChiTiet(id);

		if (ctdhView.isEmpty()) {
			model.addAttribute("ctdhView", null);
		} else {
			model.addAttribute("ctdhView", ctdhView);
		}


		return "admin/donhangdangcho1";
	}
}
