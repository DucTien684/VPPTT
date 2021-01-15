package com.springboot.vpp1849.URL.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.vpp1849.URL.custom.CustomBinhLuan;
import com.springboot.vpp1849.URL.custom.CustomCategory;
import com.springboot.vpp1849.URL.custom.CustomNhomCon;
import com.springboot.vpp1849.model.BinhLuan;
import com.springboot.vpp1849.model.District;
import com.springboot.vpp1849.model.DH_ChiTiet;
import com.springboot.vpp1849.model.DonHang;
import com.springboot.vpp1849.model.KhachHang;
import com.springboot.vpp1849.model.ND_SoDiaChi;
import com.springboot.vpp1849.model.Province;
import com.springboot.vpp1849.model.SP_Nhom;
import com.springboot.vpp1849.model.SP_NhomCon;
import com.springboot.vpp1849.model.SanPham;
import com.springboot.vpp1849.model.Ward;
import com.springboot.vpp1849.model.DTO.DonHangDTO;
import com.springboot.vpp1849.model.DTO.Item;
import com.springboot.vpp1849.service.ND_SoDiaChiService;
import com.springboot.vpp1849.service.SanPhamService;
import com.springboot.vpp1849.service.impl.BinhLuanServiceImpl;
import com.springboot.vpp1849.service.impl.DistrictServiceImpl;
import com.springboot.vpp1849.service.impl.DH_ChiTietServiceImpl;
import com.springboot.vpp1849.service.impl.DonHangServiceImpl;
import com.springboot.vpp1849.service.impl.KhachHangServiceImpl;
import com.springboot.vpp1849.service.impl.ProvinceServiceImpl;
import com.springboot.vpp1849.service.impl.SP_NhomConServiceImpl;
import com.springboot.vpp1849.service.impl.SP_NhomServiceImpl;
import com.springboot.vpp1849.service.impl.WardServiceImpl;

import utils.MyPageRequest;
import utils.PriceUtil;

@Controller
//@RequestMapping("/user")
public class UserURL {

	@Autowired
	SP_NhomServiceImpl sp_NhomService;

	@Autowired
	SP_NhomConServiceImpl sp_NhomConService;

	@Autowired
	SanPhamService sanPhamService;

	@Autowired
	BinhLuanServiceImpl binhLuanService;

	@Autowired
	KhachHangServiceImpl khachHangService;

	@Autowired
	DonHangServiceImpl donHangService;

	@Autowired
	DH_ChiTietServiceImpl dh_ChiTietService;

	@Autowired
	DistrictServiceImpl districtService;

	@Autowired
	ProvinceServiceImpl provinceService;

	@Autowired
	WardServiceImpl wardService;

	@Autowired
	ND_SoDiaChiService nd_SoDiaChiService;

	@Autowired
	public JavaMailSender emailSender;

	@GetMapping("")
	public String index(Model model) {
		return "redirect:/index";
	}

	@GetMapping("/index")
	public String getNhom(Model model) {

		// Sản phẩm mới
		List<SanPham> sanPhamMoi = sanPhamService.getSPMoi();
		model.addAttribute("sanPhamMoiView", sanPhamMoi);

		// Danh mục sản phẩm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		List<CustomCategory> spTheoNhom = new ArrayList<CustomCategory>();
		// Sản phẩm mới theo từng danh mục
		for (SP_Nhom s : sp_Nhom) {
			List<SanPham> sp = sanPhamService.getSPTheoDM(s.getIdNhomSP());
			CustomCategory cs = new CustomCategory();
			cs.setIdNhom(s.getIdNhomSP());
			cs.setTenNhom(s.getName());
			cs.setSanPham(sp);
			spTheoNhom.add(cs);
		}
		model.addAttribute("spTheoNhomView", spTheoNhom);

		// Sản phẩm bán chạy
		List<SanPham> spBanchay = sanPhamService.getSPBanChay();
		model.addAttribute("spBanChayView", spBanchay);
		return "user/index";
	}

	public List<CustomNhomCon> spnhom() {
		// Nhóm con theo nhóm cha
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();

		List<CustomNhomCon> sp_NhomCon = new ArrayList<CustomNhomCon>();

		for (SP_Nhom s : sp_Nhom) {
			List<SP_NhomCon> nhomCon = sp_NhomConService.getNhomCon(s.getIdNhomSP());
			CustomNhomCon cs = new CustomNhomCon();
			cs.setIdNhomSP(s.getIdNhomSP());
			cs.setTenNhomSP(s.getName());
			cs.setSp_NhomCon(nhomCon);
			sp_NhomCon.add(cs);
		}
		return sp_NhomCon;
	}

	@GetMapping("/searchTop")
	public String search(HttpServletRequest request, @RequestParam("tenSP") String tenSP, Model model,
			@RequestParam("idNhom") String idNhom, @RequestParam(defaultValue = "1") int page,
			@RequestParam(name = "sort", defaultValue = "ngayDang,desc") String orderBy) {

		request.getSession().setAttribute("sanPhamList", null);

		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		// Nhóm con theo nhóm cha
		List<CustomNhomCon> sp_NhomCon = this.spnhom();
		model.addAttribute("sp_Nhom_NhomCon", sp_NhomCon);

		// Sản phẩm bán chạy
		List<SanPham> spDacSac = sanPhamService.getSPDacSac();
		model.addAttribute("spDacSacView", spDacSac);

		// Show sản phẩm
		if (tenSP.equals("")) {
			return "redirect:/shop";
		}

		int pagesize = 12;
		PageRequest pageRequest = MyPageRequest.of(page - 1, orderBy);
		Page<SanPham> sanPhams = null;
		if (idNhom.equals("")) {
			sanPhams = sanPhamService.searchSP(tenSP, "", pageRequest);
		} else {
			sanPhams = sanPhamService.searchSP(tenSP, idNhom, pageRequest);
		}

		long current = page;
		long begin = Math.max(1, current - 3);
		long end = Math.max(4, current);
		long endSP = Math.min(current * pagesize, sanPhams.getTotalElements());
		long beginSP = endSP - (pagesize - (current * pagesize - endSP)) + 1;
		long totalPageCount = sanPhams.getTotalPages();
		String baseUrl = "/search?idNhom=" + idNhom + "&tenSP=" + tenSP;

		model.addAttribute("totalProduct", sanPhams.getTotalElements());
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("beginSP", beginSP);
		model.addAttribute("endSP", endSP);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrlSort", baseUrl + "?sort=");
		model.addAttribute("baseUrlPaging", baseUrl + "?sort=" + orderBy + "&page=");
		model.addAttribute("allSanPham", sanPhams);

		return "user/shop";
	}

	@GetMapping("/shop")
	public String shop(Model model, HttpServletRequest request) {
		request.getSession().setAttribute("sanPhamList", null);

		return "redirect:/shop/all";
	}

	@GetMapping("/shop/all")
	public String shop(HttpServletRequest request, Model model,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "sort", defaultValue = "ngayDang,desc") String orderBy) {

		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		// Nhóm con theo nhóm cha

		List<CustomNhomCon> sp_NhomCon = this.spnhom();
		model.addAttribute("sp_Nhom_NhomCon", sp_NhomCon);

		// Sản phẩm bán chạy
		List<SanPham> spDacSac = sanPhamService.getSPDacSac();
		model.addAttribute("spDacSacView", spDacSac);

		// Show sản phẩm
		int pagesize = 12;
		PageRequest pageRequest = MyPageRequest.of(page - 1, orderBy);
		Page<SanPham> sanPhams = sanPhamService.getAllSP(pageRequest);

		long current = page;
		long begin = Math.max(1, current - 3);
		long end = Math.max(4, current);
		long endSP = Math.min(current * pagesize, sanPhams.getTotalElements());
		long beginSP = endSP - (pagesize - (current * pagesize - endSP)) + 1;
		long totalPageCount = sanPhams.getTotalPages();
		String baseUrl = "/shop/all";

		model.addAttribute("totalProduct", sanPhams.getTotalElements());
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("beginSP", beginSP);
		model.addAttribute("endSP", endSP);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrlSort", baseUrl + "?sort=");
		model.addAttribute("baseUrlPaging", baseUrl + "?sort=" + orderBy + "&page=");
		model.addAttribute("allSanPham", sanPhams);

		return "user/shop";
	}

	@GetMapping("/shop/{idNhom}")
	public String shop_Nhom(Model model, HttpServletRequest request, @PathVariable("idNhom") String idNhom) {
		request.getSession().setAttribute("sanPhamList", null);

		return "redirect:/shop/nhom={idNhom}";
	}

	@GetMapping("/shop/nhom={idNhom}")
	public String showSPByIdNhom(HttpServletRequest request, @PathVariable("idNhom") String idNhom, Model model,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "sort", defaultValue = "ngayDang,desc") String orderBy) {

		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		// Nhóm con theo nhóm cha
		List<CustomNhomCon> sp_NhomCon = this.spnhom();
		model.addAttribute("sp_Nhom_NhomCon", sp_NhomCon);

		// Sản phẩm bán chạy
		List<SanPham> spDacSac = sanPhamService.getSPDacSac();
		model.addAttribute("spDacSacView", spDacSac);

		// Tiêu đề
		String tieuDe = sp_NhomService.getTenNhomById(idNhom, "");
		model.addAttribute("tieuDeView", tieuDe);

		// Breadcrum
		List<SP_Nhom> nhom = sp_NhomService.getNhomById(idNhom, "");
		model.addAttribute("nhomView", nhom);

		// Show sản phẩm
		int pagesize = 12;
		PageRequest pageRequest = MyPageRequest.of(page - 1, orderBy);
		Page<SanPham> sanPhams = sanPhamService.getSPByIdNhom(idNhom, pageRequest);

		long current = page;
		long begin = Math.max(1, current - 3);
		long end = Math.max(4, current);
		long endSP = Math.min(current * pagesize, sanPhams.getTotalElements());
		long beginSP = endSP - (pagesize - (current * pagesize - endSP)) + 1;
		long totalPageCount = sanPhams.getTotalPages();
		String baseUrl = "/shop/nhom=" + idNhom;

		model.addAttribute("totalProduct", sanPhams.getTotalElements());
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("beginSP", beginSP);
		model.addAttribute("endSP", endSP);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrlSort", baseUrl + "?sort=");
		model.addAttribute("baseUrlPaging", baseUrl + "?sort=" + orderBy + "&page=");
		model.addAttribute("allSanPham", sanPhams);

		return "user/shop";
	}

	@GetMapping("/shop/con={idNhomCon}")
	public String shop_NhomCon(Model model, HttpServletRequest request, @PathVariable("idNhomCon") String idNhomCon) {
		request.getSession().setAttribute("sanPhamList", null);

		return "redirect:/shop/nhom-con={idNhomCon}";
	}

	@GetMapping("/shop/nhom-con={idNhomCon}")
	public String showSPByIdNhomSPCon(HttpServletRequest request, Model model,
			@PathVariable("idNhomCon") String idNhomCon, @RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "sort", defaultValue = "ngayDang,desc") String orderBy) {

		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		// Nhóm con theo nhóm cha
		List<CustomNhomCon> sp_NhomCon = this.spnhom();
		model.addAttribute("sp_Nhom_NhomCon", sp_NhomCon);

		// Sản phẩm bán chạy
		List<SanPham> spDacSac = sanPhamService.getSPDacSac();
		model.addAttribute("spDacSacView", spDacSac);

		// Tiêu đề
		String tieuDe = sp_NhomService.getTenNhomById("", idNhomCon);
		model.addAttribute("tieuDeView", tieuDe);

		// Breadcrum
		List<SP_Nhom> nhom = sp_NhomService.getNhomById("", idNhomCon);
		model.addAttribute("nhomView", nhom);

		List<SP_NhomCon> bcNhomCon = sp_NhomConService.getBCNhomCon(idNhomCon);
		model.addAttribute("bcNhomConView", bcNhomCon);

		// Show sản phẩm
		int pagesize = 12;
		PageRequest pageRequest = MyPageRequest.of(page - 1, orderBy);
		Page<SanPham> sanPhams = sanPhamService.getSPByIdNhomCon(idNhomCon, pageRequest);

		long current = page;
		long begin = Math.max(1, current - 3);
		long end = Math.max(4, current);
		long endSP = Math.min(current * pagesize, sanPhams.getTotalElements());
		long beginSP = endSP - (pagesize - (current * pagesize - endSP)) + 1;
		long totalPageCount = sanPhams.getTotalPages();
		String baseUrl = "/shop/nhom-con=" + idNhomCon;

		model.addAttribute("totalProduct", sanPhams.getTotalElements());
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("beginSP", beginSP);
		model.addAttribute("endSP", endSP);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrlSort", baseUrl + "?sort=");
		model.addAttribute("baseUrlPaging", baseUrl + "?sort=" + orderBy + "&page=");
		model.addAttribute("allSanPham", sanPhams);

		return "user/shop";
	}

	@GetMapping("/shop/price={price}")
	public String showSPByPrice(HttpServletRequest request, @PathVariable("price") String price, Model model,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "sort", defaultValue = "ngayDang,desc") String orderBy) {

		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		// Nhóm con theo nhóm cha
		List<CustomNhomCon> sp_NhomCon = this.spnhom();
		model.addAttribute("sp_Nhom_NhomCon", sp_NhomCon);

		// Sản phẩm bán chạy
		List<SanPham> spDacSac = sanPhamService.getSPDacSac();
		model.addAttribute("spDacSacView", spDacSac);

		// Tiêu đề
		String tieuDe = sp_NhomService.getTenNhomById(price, "");
		model.addAttribute("tieuDeView", tieuDe);
		// Tiêu đề
		List<SP_Nhom> nhom = sp_NhomService.getNhomById(price, "");
		model.addAttribute("nhomView", nhom);

		// Show sản phẩm
		int pagesize = 12;
		PageRequest pageRequest = MyPageRequest.of(page - 1, orderBy);
		Double startPrice = PriceUtil.getStart(price);
		Double endPrice = PriceUtil.getEnd(price);
		Page<SanPham> sanPhams = sanPhamService.getSPByGiaTien(startPrice, endPrice, pageRequest);

		long current = page;
		long begin = Math.max(1, current - 3);
		// TODO: Noted
		long end = Math.min(sanPhams.getTotalPages(), begin + 3);
//		long end = Math.max(4, current);
		long endSP = Math.min(current * pagesize, sanPhams.getTotalElements());
		long beginSP = endSP - (pagesize - (current * pagesize - endSP)) + 1;
		long totalPageCount = sanPhams.getTotalPages();
		String baseUrl = "/shop/price=" + price;

		model.addAttribute("totalProduct", sanPhams.getTotalElements());
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("beginSP", beginSP);
		model.addAttribute("endSP", endSP);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrlSort", baseUrl + "?sort=");
		model.addAttribute("baseUrlPaging", baseUrl + "?sort=" + orderBy + "&page=");
		model.addAttribute("allSanPham", sanPhams);

		return "user/shop";
	}

	@GetMapping("/about")
	public String about(Model model) {
		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		return "user/about-us";
	}

	@GetMapping("/404")
	public String error(Model model) {
		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		return "user/404";
	}

	// Cart start

	@GetMapping("/cart")
	public String cart(HttpSession session, Model model) {

		if (session.getAttribute("cart") == null) {
			return "redirect:/shop";
		}

		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		// Cart free-ship
		model.addAttribute("total", total(session));

		if (total(session) < 5000000) {
			model.addAttribute("totalMoney", total(session));
			model.addAttribute("discount", "Không áp dụng!");
		}
		/*
		 * else if (total(session) >= 5000000) { model.addAttribute("ship",
		 * "Miễn phí!"); model.addAttribute("totalMoney", total(session)); }
		 */

		// Cart discount-rate
		/*
		 * else if (total(session) < 5000000) { model.addAttribute("discount",
		 * "Không áp dụng!"); model.addAttribute("totalMoney", total(session)); }
		 */
		else if (total(session) >= 5000000 && total(session) < 10000000) {
			
			model.addAttribute("discount", 0.05);
			model.addAttribute("totalMoney", total(session) - (total(session) * 5 / 100));
		} else if (total(session) >= 10000000 && total(session) < 20000000) {
			
			model.addAttribute("discount", 0.1);
			model.addAttribute("totalMoney", total(session) - (total(session) * 10 / 100));
		} else if (total(session) >= 20000000 && total(session) < 40000000) {
			
			model.addAttribute("discount", 0.15);
			model.addAttribute("totalMoney", total(session) - (total(session) * 15 / 100));
		} else if (total(session) >= 40000000 && total(session) < 60000000) {
			
			model.addAttribute("discount", 0.2);
			model.addAttribute("totalMoney", total(session) - (total(session) * 20 / 100));
		} else if (total(session) >= 60000000 && total(session) < 100000000) {
			
			model.addAttribute("discount", 0.25);
			model.addAttribute("totalMoney", total(session) - (total(session) * 25 / 100));
		} else {
			
			model.addAttribute("discount", 0.3);
			model.addAttribute("totalMoney", total(session) - (total(session) * 30 / 100));
		}

		return "user/cart";
	}

	@GetMapping("/cart/buy/{idSanPham}")
	public String buy(HttpSession session, HttpServletRequest request, @PathVariable("idSanPham") String idSanPham) {

		String reqQuantity = request.getParameter("reqQuantity");

		if (session.getAttribute("cart") == null) {
			List<Item> cart = new ArrayList<Item>();
			if (reqQuantity == null) {
				cart.add(new Item(sanPhamService.find(idSanPham), 1));
			} else {
				cart.add(new Item(sanPhamService.find(idSanPham), Integer.parseInt(reqQuantity)));
			}

			session.setAttribute("soLuong", cart.size());
			session.setAttribute("cart", cart);
		} else {
			@SuppressWarnings("unchecked")
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = isExists(idSanPham, cart);
			if (index == -1) {
				if (reqQuantity == null) {
					cart.add(new Item(sanPhamService.find(idSanPham), 1));
				} else {
					cart.add(new Item(sanPhamService.find(idSanPham), Integer.parseInt(reqQuantity)));
				}

			} else {
				if (reqQuantity == null) {
					int quantity = cart.get(index).getQuantity() + 1;
					cart.get(index).setQuantity(quantity);
				} else {
					int quantity = cart.get(index).getQuantity() + Integer.parseInt(reqQuantity);
					cart.get(index).setQuantity(quantity);
				}

			}
			session.setAttribute("soLuong", cart.size());
			session.setAttribute("cart", cart);

		}

		return "redirect:/index";
	}

	@GetMapping("/cart/remove/{idSanPham}")
	public String remove(HttpSession session, @PathVariable("idSanPham") String idSanPham) {

		@SuppressWarnings("unchecked")
		List<Item> cart = (List<Item>) session.getAttribute("cart");

		int index = isExists1(idSanPham, cart);
		int soLuong = cart.size();
		cart.remove(index);
		session.setAttribute("soLuong", --soLuong);
		session.setAttribute("cart", cart);

		return "redirect:/cart";
	}

	@PostMapping("/cart/update")
	public String remove(HttpSession session, HttpServletRequest request) {

		String[] quantites = request.getParameterValues("quantity");

		@SuppressWarnings("unchecked")
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		for (int i = 0; i < cart.size(); i++) {
			cart.get(i).setQuantity(Integer.parseInt(quantites[i]));
		}
		session.setAttribute("cart", cart);
		return "redirect:/cart";
	}

	private int isExists(String idSanPham, List<Item> cart) {

		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getSanPham().getIdSanPham().equals(idSanPham)) {

				return i;
			}
		}
		return -1;
	}

	private int isExists1(String idSanPham, List<Item> cart) {

		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getSanPham().getIdSanPham().equals(idSanPham)) {

				return i;
			}
		}
		return 0;
	}

	private double total(HttpSession session) {

		@SuppressWarnings("unchecked")
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		double s = 0;
		for (Item item : cart) {
			s += item.getQuantity() * item.getSanPham().getGiaTien();

		}
		return s;
	}

	// Cart End

	@GetMapping("/checkout")
	public String checkout(Model model, HttpSession session) {

		KhachHang kh = (KhachHang) session.getAttribute("SESSION");
		if (session.getAttribute("cart") == null) {
			return "redirect:/shop";
		}

		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		/*
		 * // Cart free-ship model.addAttribute("total", total(session)); if
		 * (total(session) < 5000000) { model.addAttribute("ship", 300000);
		 * model.addAttribute("totalMoney", (total(session) + total(session) * 0.1) +
		 * 300000); } else { model.addAttribute("ship", "Miễn phí!");
		 * model.addAttribute("totalMoney", total(session) + total(session) * 0.1); }
		 * 
		 * // Cart discount-rate if (total(session) < 5000000) {
		 * model.addAttribute("discount", "Không áp dụng!");
		 * model.addAttribute("totalMoney", total(session)); } else if(total(session) >=
		 * 5000000 && total(session) < 10000000) { model.addAttribute("discount", 0.05);
		 * model.addAttribute("totalMoney", total(session) - (total(session) * 5/100));
		 * } else if(total(session) >= 10000000 && total(session) < 20000000) {
		 * model.addAttribute("discount", 0.1); model.addAttribute("totalMoney",
		 * total(session) - (total(session) * 10/100)); } else if(total(session) >=
		 * 20000000 && total(session) < 40000000) { model.addAttribute("discount",
		 * 0.15); model.addAttribute("totalMoney", total(session) - (total(session) *
		 * 15/100)); } else if(total(session) >= 40000000 && total(session) < 60000000)
		 * { model.addAttribute("discount", 0.2); model.addAttribute("totalMoney",
		 * total(session) - (total(session) * 20/100)); } else if(total(session) >=
		 * 60000000 && total(session) < 100000000) { model.addAttribute("discount",
		 * 0.25); model.addAttribute("totalMoney", total(session) - (total(session) *
		 * 25/100)); } else { model.addAttribute("discount", 0.3);
		 * model.addAttribute("totalMoney", total(session) - (total(session) * 30/100));
		 * }
		 */
		model.addAttribute("total", total(session));
		if (total(session) < 5000000) {
			
			model.addAttribute("totalMoney", (total(session) + total(session) * 0.05));
			model.addAttribute("discount", "Không áp dụng!");
		}
		/*
		 * else if (total(session) >= 5000000) { model.addAttribute("ship",
		 * "Miễn phí!"); model.addAttribute("totalMoney", total(session)); }
		 */

		// Cart discount-rate
		/*
		 * else if (total(session) < 5000000) { model.addAttribute("discount",
		 * "Không áp dụng!"); model.addAttribute("totalMoney", total(session)); }
		 */
		else if (total(session) >= 5000000 && total(session) < 10000000) {
			
			model.addAttribute("discount", 0.05);
			model.addAttribute("totalMoney", (total(session) - (total(session) * 5 / 100)) + total(session) * 0.05);
		} else if (total(session) >= 10000000 && total(session) < 20000000) {
			
			model.addAttribute("discount", 0.1);
			model.addAttribute("totalMoney", (total(session) - (total(session) * 10 / 100)) + total(session) * 0.05);
		} else if (total(session) >= 20000000 && total(session) < 40000000) {
			
			model.addAttribute("discount", 0.15);
			model.addAttribute("totalMoney", (total(session) - (total(session) * 15 / 100)) + total(session) * 0.05);
		} else if (total(session) >= 40000000 && total(session) < 60000000) {
			
			model.addAttribute("discount", 0.2);
			model.addAttribute("totalMoney", (total(session) - (total(session) * 20 / 100)) + total(session) * 0.05);
		} else if (total(session) >= 60000000 && total(session) < 100000000) {
			
			model.addAttribute("discount", 0.25);
			model.addAttribute("totalMoney", (total(session) - (total(session) * 25 / 100)) + total(session) * 0.05);
		} else {
			
			model.addAttribute("discount", 0.3);
			model.addAttribute("totalMoney", (total(session) - (total(session) * 30 / 100)) + total(session) * 0.05);
		}

		// Sổ địa chỉ của khách hàng
		if (kh != null) {
			List<ND_SoDiaChi> listSoDiaChi = nd_SoDiaChiService.findByIdKhachHang(kh.getIdKhachHang());
			model.addAttribute("listSoDiaChi", listSoDiaChi);
		}

		// List Tỉnh
		List<Province> listProvince = provinceService.getProvince();
		model.addAttribute("listProvince", listProvince);

		// List Quận
		List<District> listDistrict = new ArrayList<District>();
		model.addAttribute("listDistrict", listDistrict);

		// List Đường
		List<Ward> listWard = new ArrayList<Ward>();
		model.addAttribute("listWard", listWard);

		return "user/checkout";
	}

	@PostMapping("/checkout")
	public String buy(KhachHang khachHang, RedirectAttributes rd, HttpSession session, HttpServletRequest req,
			BindingResult result, Model model) {

		KhachHang kh = (KhachHang) session.getAttribute("SESSION");
		ND_SoDiaChi soDiaChi = new ND_SoDiaChi();
		DonHang donHang = new DonHang();
		DH_ChiTiet dhChiTiet = new DH_ChiTiet();

		Date date = new Date();
		String diaChiGiaoHang = "";

		// Trường hợp chưa đăng nhập
		// Khách hàng
		if (kh == null) {
			khachHang.setHoTen(req.getParameter("ten"));
			khachHang.setSdt(req.getParameter("sdt"));
			khachHang.setEmail(req.getParameter("email"));
			if (req.getParameter("matKhau") != null
					&& req.getParameter("checkMatKhau") == req.getParameter("matKhau")) {
				khachHang.setMatKhau(req.getParameter("matKhau"));
			}
			khachHang.setIdKhachHang(khachHangService.countKH());
			khachHang.setNgayDK(date);
			khachHang.setTrangThai(1);

			khachHangService.addKhachHang(khachHang);

			// Set khách hàng cho hóa đơn nếu khách hàng đã đăng nhập
			donHang.setKhachHang(khachHang);

			// Sổ địa chỉ
			soDiaChi.setIdSoDiaChi(nd_SoDiaChiService.customIdSoDiaChi());
			soDiaChi.setKhachHang(khachHang);
			soDiaChi.setDiaChiCuThe(req.getParameter("diaChi"));
			soDiaChi.setTinh(provinceService.getNameByProvinceId(req.getParameter("tinh")));
			soDiaChi.setHuyen(districtService.getNameByDistrictId(req.getParameter("huyen")));
			soDiaChi.setXa(wardService.getNameByWardId(req.getParameter("xa")));
			soDiaChi.setHoTen(req.getParameter("ten"));
			soDiaChi.setSdt(req.getParameter("sdt"));
			nd_SoDiaChiService.addSoDiaChi(soDiaChi);

			diaChiGiaoHang = req.getParameter("diaChi") + ", " + wardService.getNameByWardId(req.getParameter("xa"))
					+ ", " + districtService.getNameByDistrictId(req.getParameter("huyen")) + ", "
					+ provinceService.getNameByProvinceId(req.getParameter("tinh")) + ", ";

		}

		// Trường hợp khách hàng đã đăng nhập
		// Don Hang
		if (kh != null) {
			// Set khách hàng cho hóa đơn nếu khách hàng đã đăng nhập
			donHang.setKhachHang(kh);

			if (kh.getSoDiaChi().size() == 0) {
				// Sổ địa chỉ
				soDiaChi.setIdSoDiaChi(nd_SoDiaChiService.customIdSoDiaChi());
				soDiaChi.setKhachHang(kh);
				soDiaChi.setDiaChiCuThe(req.getParameter("diaChi"));
				soDiaChi.setTinh(provinceService.getNameByProvinceId(req.getParameter("tinh")));
				soDiaChi.setHuyen(districtService.getNameByDistrictId(req.getParameter("huyen")));
				soDiaChi.setXa(wardService.getNameByWardId(req.getParameter("xa")));
				soDiaChi.setHoTen(req.getParameter("ten"));
				soDiaChi.setSdt(req.getParameter("sdt"));
				nd_SoDiaChiService.addSoDiaChi(soDiaChi);

				diaChiGiaoHang = req.getParameter("diaChi") + ", " + wardService.getNameByWardId(req.getParameter("xa"))
						+ ", " + districtService.getNameByDistrictId(req.getParameter("huyen")) + ", "
						+ provinceService.getNameByProvinceId(req.getParameter("tinh")) + ", ";

			} else if (kh.getSoDiaChi().size() > 0) {
				ND_SoDiaChi getSoDiaChi = nd_SoDiaChiService.getSoDiaChiByIdSoDiaChi(req.getParameter("diaChi"));

				diaChiGiaoHang = getSoDiaChi.getDiaChiCuThe() + ", " + getSoDiaChi.getXa() + ", "
						+ getSoDiaChi.getHuyen() + ", " + getSoDiaChi.getTinh();
			}
		}

		donHang.setIdDonHang(donHangService.countDonHang());
		donHang.setNgayLapDH(date);
		donHang.setNhanVien(null);
		donHang.setTongSoTien(Double.parseDouble(req.getParameter("totalMoney")));
		donHang.setThue(5);
		donHang.setDiaChiGiaoHang(diaChiGiaoHang);
		donHang.setTrangThai(3);

		donHangService.addDonHang(donHang);

		// DonHang chi tiết

		@SuppressWarnings("unchecked")
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		for (int i = 0; i < cart.size(); i++) {
			dhChiTiet.setIdDonHangChiTiet(dh_ChiTietService.countDHCT());
			dhChiTiet.setDonHang(donHang);
			dhChiTiet.setSanPham(cart.get(i).getSanPham());
			dhChiTiet.setDonGia(cart.get(i).getSanPham().getGiaTien());
			dhChiTiet.setSoLuong(cart.get(i).getQuantity());
			dh_ChiTietService.addDHChiTiet(dhChiTiet);
		}

		rd.addFlashAttribute("successMessage", true);

		cart.clear();
		session.setAttribute("soLuong", 0);
		session.setAttribute("cart", cart);

		return "redirect:/checkout";
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		return "user/contact";
	}

	@GetMapping("/login")
	public String login(Model model) {
		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		return "user/login";
	}

	// -- Login tạo session Người Dùng, dùng session lấy thông tin + địa chỉ người
	// dùng
	@PostMapping("/login")
	public String login(HttpSession session, RedirectAttributes rd, @ModelAttribute("email") String email,
			@ModelAttribute("pass") String pass) {
		Optional<KhachHang> kh = khachHangService.checkLogin(email, pass);
		if (kh.isPresent()) {
			session.setAttribute("SESSION", kh.get());
			return "redirect:/index";
		}
		rd.addFlashAttribute("email", email);
		rd.addFlashAttribute("error", "Email hoặc mật khẩu không đúng!");
		return "redirect:/login";
	}

	@PostMapping("/login-checkout")
	public String login_checkout(HttpSession session, RedirectAttributes rd, @ModelAttribute("emailCK") String emailCK,
			@ModelAttribute("pass") String pass, HttpServletRequest req) {

		Optional<KhachHang> kh = khachHangService.checkLogin(emailCK, pass);
		if (kh.isPresent()) {
			session.setAttribute("SESSION", kh.get());
			return "redirect:/checkout";
		}
		rd.addFlashAttribute("errorLoginCheck", "Email hoặc mật khẩu không đúng!");

		return "redirect:/checkout";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("SESSION");
		return "redirect:/index";
	}

	@GetMapping("/product")
	public String product(Model model, @RequestParam("id") String id, BinhLuan binhLuan, HttpSession session) {

		session.setAttribute("sp", null);
		session.setAttribute("idSP", null);

		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		// Breadcrumb
		List<SP_Nhom> linkToNhom = sp_NhomService.getTenNhomByIdSP(id);
		model.addAttribute("linkToNhom", linkToNhom);

		List<SP_NhomCon> linkToNhomCon = sp_NhomConService.getBCNhomConByIdSP(id);
		model.addAttribute("linkToNhomCon", linkToNhomCon);

		// Chi tiết sản phẩm
		List<SanPham> chiTiet = sanPhamService.getSanPhamById(id);
		model.addAttribute("chiTietSanPhamView", chiTiet);

		// Sản phẩm tương tự
		List<SanPham> spTuongTu = sanPhamService.getSPTuongTu(id);
		model.addAttribute("spTuongTuView", spTuongTu);

		// Bình Luận
		String blSanPham = binhLuanService.getBLSanPham(id);
		model.addAttribute("spDuocBL", blSanPham);

		List<CustomBinhLuan> binhLuanView = binhLuanService.getBinhLuan(id);

		if (binhLuanView.isEmpty()) {
			model.addAttribute("binhLuanView", null);
		} else {
			model.addAttribute("binhLuanView", binhLuanView);
		}

		SanPham sp = sanPhamService.find(id);
		session.setAttribute("sp", sp);
		session.setAttribute("idSP", sp.getIdSanPham());

		return "user/product";
	}

	@PostMapping("/addBinhLuan")
	public String addBinhLuan(HttpSession session, @ModelAttribute BinhLuan binhLuan, HttpServletRequest req) {

		Date date = new Date();
		binhLuan.setIdBinhLuan(binhLuanService.countBL());
		binhLuan.setThoiGian(date);
		binhLuan.setSanPham((SanPham) session.getAttribute("sp"));
		binhLuan.setKhachHang((KhachHang) session.getAttribute("SESSION"));
		binhLuanService.addBinhLuan(binhLuan);

		String re = (String) session.getAttribute("idSP");

		return "redirect:/product?id=" + re;
	}

	@GetMapping("/register")
	public String register(Model model, KhachHang khachHang, HttpSession session) {
		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);
		session.setAttribute("email", null);

		return "user/register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("khachHang") KhachHang khachHang, BindingResult result,
			RedirectAttributes rd, HttpServletRequest req, HttpSession session) {

		KhachHang existEmail = khachHangService.getKHByEmail(khachHang.getEmail());
		KhachHang existSDT = khachHangService.getKHBySDT(khachHang.getSdt());

		if (existEmail != null) {
			rd.addFlashAttribute("errorEmail", "Emai đã tồn tại !!" + "Vui lòng đăng ký email khác.");
			return "redirect:/register";
		} else if (existSDT != null) {
			rd.addFlashAttribute("errorSDT", "Số điện thoại đã tồn tại !!" + "Vui lòng nhập Số điện thoại khác.");
			return "redirect:/register";
		}
		if (result.hasErrors()) {
			return "redirect:/register";
		}
		Date date = new Date();
		khachHang.setIdKhachHang(khachHangService.countKH());
		khachHang.setNgayDK(date);
		khachHang.setTrangThai(2);
		khachHangService.addKhachHang(khachHang);

		rd.addFlashAttribute("successMessage", true);
		session.setAttribute("email", khachHang.getEmail());

		return "redirect:/register";
	}

	@PostMapping("/forGotPass")
	public String forGotPassWord(HttpServletRequest req, RedirectAttributes rd, String content)
			throws MessagingException {

		MimeMessage message = emailSender.createMimeMessage();

		boolean multipart = true;

		MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "UTF-8");

		String newPass = RandomStringUtils.randomAlphanumeric(6);

		KhachHang existKH = khachHangService.getKHByEmail(req.getParameter("emailForgot"));

		if (existKH == null) {
			rd.addFlashAttribute("errorEmail", "Email của bạn chưa được đăng ký!");
			return "redirect:/login";
		}

		String htmlMsg = "<img src='https://i.imgur.com/ebgC6Ka.png'>" + "<p>Xin chào " + existKH.getHoTen() + "!</p>"
				+ "<p>Mật khẩu bạn cần để truy cập vào tài khoản Văn Phòng Phẩm Thịnh Vượng của mình "
				+ existKH.getEmail() + " là: " + "<b>" + newPass + "</b></p>"
				+ "<p>Nếu bạn không yêu cầu mật khẩu này thì có thể là ai đó đang tìm cách truy cập vào "
				+ "tài khoản Văn Phòng Phẩm Thịnh Vượng. Không chuyển tiếp hoặc cung cấp mật khẩu này cho bất kỳ ai. </p>"
				+ "<p>Trân trọng!</p>" + "<p>Văn Phòng Phẩm Thịnh Vượng.</p>";

		message.setContent(htmlMsg, "text/html;charset=utf-8");

		helper.setTo(req.getParameter("emailForgot"));

		helper.setSubject("Test send HTML email");

		// Send Message!
		this.emailSender.send(message);

		existKH.setMatKhau(newPass);
		khachHangService.updateKhachHang(existKH);

		rd.addFlashAttribute("sendMailToTakePass", true);

		return "redirect:/login";

	}

	@GetMapping("/account_address")
	public String account_address(Model model, HttpSession session) {

		KhachHang kh = (KhachHang) session.getAttribute("SESSION");

		if (kh == null) {
			return "redirect:/login";
		}

		// List Tỉnh
		List<Province> listProvince = provinceService.getProvince();
		model.addAttribute("listProvince", listProvince);

		// List Quận
		List<District> listDistrict = new ArrayList<District>();
		model.addAttribute("listDistrict", listDistrict);

		// List Đường
		List<Ward> listWard = new ArrayList<Ward>();
		model.addAttribute("listWard", listWard);

		ND_SoDiaChi soDiaChi = new ND_SoDiaChi();
		model.addAttribute("soDiaChi", soDiaChi);

		if (kh != null) {
			List<ND_SoDiaChi> listSoDiaChi = nd_SoDiaChiService.findByIdKhachHang(kh.getIdKhachHang());
			model.addAttribute("listSoDiaChi", listSoDiaChi);
		}

		return "account-user/account-address";
	}

	@PostMapping("/account_address")
	public String addSoDiaChi(@ModelAttribute ND_SoDiaChi soDiaChi, HttpSession session, HttpServletRequest req,
			RedirectAttributes rd) {
		soDiaChi.setIdSoDiaChi(nd_SoDiaChiService.customIdSoDiaChi());
		// Lấy SESSION hiện tại của khách hàng
		KhachHang kh = (KhachHang) session.getAttribute("SESSION");
		soDiaChi.setKhachHang(kh);
		soDiaChi.setTinh(provinceService.getNameByProvinceId(req.getParameter("province")));
		soDiaChi.setHuyen(districtService.getNameByDistrictId(req.getParameter("district")));
		soDiaChi.setXa(wardService.getNameByWardId(req.getParameter("ward")));
		nd_SoDiaChiService.addSoDiaChi(soDiaChi);
		rd.addFlashAttribute("addSuccess", true);

		return "redirect:/account_address";
	}

	@GetMapping("/updateSoDiaChi/{idSoDiaChi}")
	public String getFormUpdateSoDiaChi(@PathVariable("idSoDiaChi") String idSoDiaChi, Model model,
			HttpSession session) {
		ND_SoDiaChi soDiaChi = nd_SoDiaChiService.getSoDiaChiByIdSoDiaChi(idSoDiaChi);
		model.addAttribute("soDiaChi", soDiaChi);

		// List Tỉnh
		List<Province> listProvince = provinceService.getProvince();
		model.addAttribute("listProvince", listProvince);

		// List Quận
		List<District> listDistrict = new ArrayList<District>();
		model.addAttribute("listDistrict", listDistrict);

		// List Xã
		List<Ward> listWard = new ArrayList<Ward>();
		model.addAttribute("listWard", listWard);

		return "user/updateSoDiaChi";
	}

	@PostMapping("/updateSoDiaChi/{idSoDiaChi}")
	public String updateSoDiachi(@PathVariable("idSoDiaChi") String idSoDiaChi, HttpSession session,
			HttpServletRequest req, @ModelAttribute ND_SoDiaChi soDiaChi, RedirectAttributes rd) {
		ND_SoDiaChi ndSoDiaChi = nd_SoDiaChiService.getSoDiaChiByIdSoDiaChi(idSoDiaChi);
		KhachHang kh = (KhachHang) session.getAttribute("SESSION");
		ndSoDiaChi.setIdSoDiaChi(soDiaChi.getIdSoDiaChi());
		ndSoDiaChi.setKhachHang(kh);
		ndSoDiaChi.setHoTen(soDiaChi.getHoTen());
		ndSoDiaChi.setSdt(soDiaChi.getSdt());
		ndSoDiaChi.setDiaChiCuThe(soDiaChi.getDiaChiCuThe());
		ndSoDiaChi.setTinh((provinceService.getNameByProvinceId(soDiaChi.getTinh())));
		ndSoDiaChi.setHuyen(districtService.getNameByDistrictId(soDiaChi.getHuyen()));
		ndSoDiaChi.setXa((wardService.getNameByWardId(soDiaChi.getXa())));

		nd_SoDiaChiService.updateSoDiaChi(ndSoDiaChi);
		rd.addFlashAttribute("updateSuccess", true);

		return "redirect:/updateSoDiaChi/{idSoDiaChi}";
	}

	@GetMapping("/deleteSoDiaChi/{idSoDiaChi}")
	public String deleteSoDiaChi(@PathVariable("idSoDiaChi") String idSoDiaChi) {
		nd_SoDiaChiService.deleteSoDiaChi(idSoDiaChi);
		return "redirect:/account_address";
	}

	@GetMapping("/account_info")
	public String account_info(Model model, HttpSession session) {

		KhachHang kh = (KhachHang) session.getAttribute("SESSION");

		if (kh == null) {
			return "redirect:/login";
		}

		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		// List Tỉnh
		List<Province> listProvince = provinceService.getProvince();
		model.addAttribute("listProvince", listProvince);

		// List Quận
		List<District> listDistrict = new ArrayList<District>();
		model.addAttribute("listDistrict", listDistrict);

		// List Đường
		List<Ward> listWard = new ArrayList<Ward>();
		model.addAttribute("listWard", listWard);

		return "account-user/account-info";
	}

	@SuppressWarnings("unused")
	@PostMapping("/account_info")
	public String update_account_info(HttpSession session, RedirectAttributes rd, HttpServletRequest req) {

		KhachHang kh = (KhachHang) session.getAttribute("SESSION");

		String name = req.getParameter("hoTen");
		// String email = req.getParameter("email");
		String sdt = req.getParameter("sdt");
		String oldPass = req.getParameter("mkcu");
		String newPass = req.getParameter("mkmoi");

		KhachHang checkKH = khachHangService.findByIdKhachHangAndMatKhau(kh.getIdKhachHang(), oldPass);

		if (kh == null) {
			return "redirect:/login";
		}
		rd.addFlashAttribute("errorPass", "");

		if (oldPass != "") {
			if (checkKH == null) {
				rd.addFlashAttribute("errorPass", "Mật khẩu cũ không chính xác!");
				return "redirect:/account_info";
			}
		}

		if (newPass != "") {
			kh.setMatKhau(newPass);
		}

		kh.setHoTen(name);
		kh.setSdt(sdt);
		khachHangService.updateKhachHang(kh);

		rd.addFlashAttribute("updateSuccess", true);

		return "redirect:/account_info";
	}

	@GetMapping("/account_qldh")
	public String account_qldh(HttpSession session, Model model, String idKhachHang) throws ParseException {

		KhachHang kh = (KhachHang) session.getAttribute("SESSION");

		if (kh == null) {
			return "redirect:/login";
		}

		// Nhóm
		List<SP_Nhom> sp_Nhom = sp_NhomService.getAllNhom();
		model.addAttribute("sp_NhomView", sp_Nhom);

		// Lấy SESSION hiện tại của NguoiDung
		idKhachHang = kh.getIdKhachHang();
		List<DonHangDTO> listDonHang = donHangService.findAll(idKhachHang);
		model.addAttribute("listDonHang", listDonHang);
		return "account-user/account-qldh";
	}

	@GetMapping("/getDistrict")
	@ResponseBody
	public List<District> getDistrictByProvinceId(@RequestParam String provinceId) {
		List<District> listDistrict = districtService.getDistrict(provinceId);
		return listDistrict;
	}

	@GetMapping("/getWard")
	@ResponseBody
	public List<Ward> getWardByDistrictId(@RequestParam String districtId) {
		List<Ward> listWard = wardService.getWard(districtId);
		return listWard;
	}

	@PostMapping("/deleteDHCT/{id}")
	@ResponseBody
	public String deleteDHCT(@PathVariable("id") String idDonHang) {
		return donHangService.deleteDHCT(idDonHang) != null ? "1" : "0";
	}
}
