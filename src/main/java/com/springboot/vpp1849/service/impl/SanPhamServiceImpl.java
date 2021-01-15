package com.springboot.vpp1849.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.springboot.vpp1849.model.SanPham;
import com.springboot.vpp1849.repository.SanPhamRepository;
import com.springboot.vpp1849.service.SanPhamService;

@Service
public class SanPhamServiceImpl implements SanPhamService {

	@Autowired
	private SanPhamRepository sanPhamRepository;

//	@Autowired
//	private SP_NhomServiceImpl sp_NhomService;

	@Override
	public List<SanPham> getAllSP() {
		return sanPhamRepository.getAllSP();
	}

	@Override
	public Page<SanPham> getAllSP(Pageable pageable) {
		return sanPhamRepository.getAllSP(pageable);
	}

	@Override
	public List<SanPham> getSPMoi() {
		return sanPhamRepository.getSPMoi();
	}

	@Override
	public List<SanPham> getSPBanChay() {
		return sanPhamRepository.getSPBanChay();

	}

	@Override
	public List<SanPham> getSPTheoDM(String idNhomSP) {
		return sanPhamRepository.getSPTheoDM(idNhomSP, PageRequest.of(0, 10));
	}

	@Override
	public Page<SanPham> getSPByIdNhom(String idNhomSP, Pageable pageable) {
		return sanPhamRepository.getSPByIdNhom(idNhomSP, pageable);
	}

	@Override
	public List<SanPham> getSPDacSac() {
		return sanPhamRepository.getSPDacSac();
	}

	@Override
	public List<SanPham> getSPByIdNhomCon(String idNhomSPCon) {
		return sanPhamRepository.getSPByIdNhomCon(idNhomSPCon);
	}

	@Override
	public Page<SanPham> getSPByIdNhomCon(String idNhomCon, Pageable pageable) {
		return sanPhamRepository.getSPByIdNhomCon(idNhomCon, pageable);
	}

	@Override
	public List<SanPham> getSanPhamById(String idSanPham) {
		return sanPhamRepository.findByIdSanPham(idSanPham);
	}

	@Override
	public List<SanPham> getSPTuongTu(String idSanPham) {

		return sanPhamRepository.getSPTuongTu(idSanPham);
	}

	@Override
	public List<SanPham> getAll() {
		return sanPhamRepository.findAll();
	}

	@Override
	public Page<SanPham> getSPByGiaTien(Double giaTien1, Double giaTien2, Pageable pageable) {
		if (giaTien1 != null && giaTien2 == null) {
			return sanPhamRepository.getSPByGiaTien1(giaTien1, pageable);
		} else if (giaTien1 != null && giaTien2 != null) {
			return sanPhamRepository.getSPByGiaTien(giaTien1, giaTien2, pageable);
		} else if (giaTien1 == null && giaTien2 != null) {
			return sanPhamRepository.getSPByGiaTien2(giaTien2, pageable);
		}
		return sanPhamRepository.getAllSP(pageable);
	}

	@Override
	public List<SanPham> getSPNgayXoaNotNull() {
		return sanPhamRepository.findByNgayXoaNotNull();
	}

	@Override
	public SanPham deleteDateSP(String idSanPham) {
		SanPham spUpdate = null;
		// Kiểm tra xem idSanPham có tồn tại chưa bằng hàm existById
		if (sanPhamRepository.existsById(idSanPham)) {
			SanPham sp = sanPhamRepository.getOne(idSanPham);
			sp.setNgayXoa(new Date());
			spUpdate = sanPhamRepository.saveAndFlush(sp);
		}
		return spUpdate;
	}

	@Override
	public SanPham addSP(SanPham sanPham) {
		return sanPhamRepository.save(sanPham);
	}

	public String customIdSanPham() {
		int tongSoSanPham = Integer.parseInt(sanPhamRepository.countSP());
		String idSanPham = "SP0" + String.valueOf(tongSoSanPham + 1);
		return idSanPham;
	}

	@Override
	public String customMaSP() {
		int tongSoSanPham = Integer.parseInt(sanPhamRepository.countSP());
		String maSP = "IP0" + String.valueOf(tongSoSanPham + 1);
		return maSP;
	}

	@Override
	public SanPham updateSP(SanPham sanPham) {
		return sanPhamRepository.save(sanPham);
	}

	@Override
	public Optional<SanPham> findById(String idSanPham) {
		return sanPhamRepository.findById(idSanPham);
	}

	@Override
	public SanPham find(String idSanPham) {
		return sanPhamRepository.getSPByIdSP(idSanPham);
	}

	@Override
	public SanPham getSpByIdSanPham(String idSanPham) {
		return sanPhamRepository.getSpByIdSanPham(idSanPham);
	}

	@Override
	public Page<SanPham> searchSP(String tenSP, String idNhom, Pageable pageable) {
		if (idNhom == "") {
			return sanPhamRepository.searchSPByTenSP(tenSP, pageable);
		} else {
			return sanPhamRepository.searchSPByIdNhomAndTenSP(tenSP, idNhom, pageable);
		}

	}

	// Tổng sản phẩm index
	@Override
	public String countSanPham() {
		return sanPhamRepository.countSanPham();
	}

	// Tổng sản phẩm đã xóa
	@Override
	public String countSanPhamDaXoa() {
		return sanPhamRepository.countSanPhamDaXoa();
	}

	// EXCEL và PDF
	@Override
	public boolean createPdfSanPham(List<SanPham> sanPhams, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A3, 15, 15, 45, 30);
		try {
			String filePath = context.getRealPath("/resources/reports");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "sanPham" + ".pdf"));
			document.open();

			Font maintFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);

			Paragraph paragraph = new Paragraph("Tất cả sản phẩm", maintFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);

			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

			float[] columnWidths = { 2f, 2f, 2f, 2f, 2f, 2f };
			table.setWidths(columnWidths);

			PdfPCell maSP = new PdfPCell(new Paragraph("ID sản phẩm", tableHeader));
			maSP.setBorderColor(BaseColor.BLACK);
			maSP.setPaddingLeft(10);
			maSP.setHorizontalAlignment(Element.ALIGN_CENTER);
			maSP.setVerticalAlignment(Element.ALIGN_CENTER);
			maSP.setBackgroundColor(BaseColor.GRAY);
			maSP.setExtraParagraphSpace(5f);
			table.addCell(maSP);

			PdfPCell tenSP = new PdfPCell(new Paragraph("Tên sản phẩm", tableHeader));
			tenSP.setBorderColor(BaseColor.BLACK);
			tenSP.setPaddingLeft(10);
			tenSP.setHorizontalAlignment(Element.ALIGN_CENTER);
			tenSP.setVerticalAlignment(Element.ALIGN_CENTER);
			tenSP.setBackgroundColor(BaseColor.GRAY);
			tenSP.setExtraParagraphSpace(5f);
			table.addCell(tenSP);

			PdfPCell gia = new PdfPCell(new Paragraph("Giá", tableHeader));
			gia.setBorderColor(BaseColor.BLACK);
			gia.setPaddingLeft(10);
			gia.setHorizontalAlignment(Element.ALIGN_CENTER);
			gia.setVerticalAlignment(Element.ALIGN_CENTER);
			gia.setBackgroundColor(BaseColor.GRAY);
			gia.setExtraParagraphSpace(5f);
			table.addCell(gia);

			PdfPCell donViTinh = new PdfPCell(new Paragraph("Đơn vị tính", tableHeader));
			donViTinh.setBorderColor(BaseColor.BLACK);
			donViTinh.setPaddingLeft(10);
			donViTinh.setHorizontalAlignment(Element.ALIGN_CENTER);
			donViTinh.setVerticalAlignment(Element.ALIGN_CENTER);
			donViTinh.setBackgroundColor(BaseColor.GRAY);
			donViTinh.setExtraParagraphSpace(5f);
			table.addCell(donViTinh);

			PdfPCell ngayDang = new PdfPCell(new Paragraph("Ngày đăng", tableHeader));
			ngayDang.setBorderColor(BaseColor.BLACK);
			ngayDang.setPaddingLeft(10);
			ngayDang.setHorizontalAlignment(Element.ALIGN_CENTER);
			ngayDang.setVerticalAlignment(Element.ALIGN_CENTER);
			ngayDang.setBackgroundColor(BaseColor.GRAY);
			ngayDang.setExtraParagraphSpace(5f);
			table.addCell(ngayDang);

			PdfPCell soLuong = new PdfPCell(new Paragraph("Số lượng", tableHeader));
			soLuong.setBorderColor(BaseColor.BLACK);
			soLuong.setPaddingLeft(10);
			soLuong.setHorizontalAlignment(Element.ALIGN_CENTER);
			soLuong.setVerticalAlignment(Element.ALIGN_CENTER);
			soLuong.setBackgroundColor(BaseColor.GRAY);
			soLuong.setExtraParagraphSpace(5f);
			table.addCell(soLuong);

			for (SanPham sanPham : sanPhams) {
				PdfPCell maSPValue = new PdfPCell(new Paragraph(sanPham.getMaSP(), tableBody));
				maSPValue.setBorderColor(BaseColor.BLACK);
				maSPValue.setPaddingLeft(10);
				maSPValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				maSPValue.setVerticalAlignment(Element.ALIGN_CENTER);
				maSPValue.setBackgroundColor(BaseColor.WHITE);
				maSPValue.setExtraParagraphSpace(5f);
				table.addCell(maSPValue);

				PdfPCell tenSPValue = new PdfPCell(new Paragraph(sanPham.getTenSP(), tableBody));
				tenSPValue.setBorderColor(BaseColor.BLACK);
				tenSPValue.setPaddingLeft(10);
				tenSPValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				tenSPValue.setVerticalAlignment(Element.ALIGN_CENTER);
				tenSPValue.setBackgroundColor(BaseColor.WHITE);
				tenSPValue.setExtraParagraphSpace(5f);
				table.addCell(tenSPValue);

				PdfPCell giaValue = new PdfPCell(new Paragraph(String.valueOf(sanPham.getGiaTien()), tableBody));
				giaValue.setBorderColor(BaseColor.BLACK);
				giaValue.setPaddingLeft(10);
				giaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				giaValue.setVerticalAlignment(Element.ALIGN_CENTER);
				giaValue.setBackgroundColor(BaseColor.WHITE);
				giaValue.setExtraParagraphSpace(5f);
				table.addCell(giaValue);

				PdfPCell donViTinhValue = new PdfPCell(new Paragraph(sanPham.getDonViTinh(), tableBody));
				donViTinhValue.setBorderColor(BaseColor.BLACK);
				donViTinhValue.setPaddingLeft(10);
				donViTinhValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				donViTinhValue.setVerticalAlignment(Element.ALIGN_CENTER);
				donViTinhValue.setBackgroundColor(BaseColor.WHITE);
				donViTinhValue.setExtraParagraphSpace(5f);
				table.addCell(donViTinhValue);

				PdfPCell ngayDangValue = new PdfPCell(new Paragraph(String.valueOf(sanPham.getNgayDang()), tableBody));
				ngayDangValue.setBorderColor(BaseColor.BLACK);
				ngayDangValue.setPaddingLeft(10);
				ngayDangValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				ngayDangValue.setVerticalAlignment(Element.ALIGN_CENTER);
				ngayDangValue.setBackgroundColor(BaseColor.WHITE);
				ngayDangValue.setExtraParagraphSpace(5f);
				table.addCell(ngayDangValue);

				PdfPCell soLuongValue = new PdfPCell(
						new Paragraph(String.valueOf(sanPham.getSoLuongConLai()), tableBody));
				soLuongValue.setBorderColor(BaseColor.BLACK);
				soLuongValue.setPaddingLeft(10);
				soLuongValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				soLuongValue.setVerticalAlignment(Element.ALIGN_CENTER);
				soLuongValue.setBackgroundColor(BaseColor.WHITE);
				soLuongValue.setExtraParagraphSpace(5f);
				table.addCell(soLuongValue);
			}

			document.add(table);
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean createExcelSanPham(List<SanPham> sanPhams, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {

		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if (!exists) {
			new File(filePath).mkdirs();

		}
		try {
			FileOutputStream outputStream = new FileOutputStream(file + "/" + "sanPham" + ".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet workSheet = workbook.createSheet("SanPham");
			workSheet.setDefaultColumnWidth(30);

			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			HSSFRow headerRow = workSheet.createRow(0);

			HSSFCell maSP = headerRow.createCell(0);
			maSP.setCellValue("Ma San Pham");
			maSP.setCellStyle(headerCellStyle);

			HSSFCell tenSP = headerRow.createCell(1);
			tenSP.setCellValue("Ten san pham");
			tenSP.setCellStyle(headerCellStyle);

			HSSFCell gia = headerRow.createCell(2);
			gia.setCellValue("Gia");
			gia.setCellStyle(headerCellStyle);

			HSSFCell donViTinh = headerRow.createCell(3);
			donViTinh.setCellValue("Don vi tinh");
			donViTinh.setCellStyle(headerCellStyle);

			HSSFCell ngayDang = headerRow.createCell(4);
			ngayDang.setCellValue("Ngay Dang");
			ngayDang.setCellStyle(headerCellStyle);

			HSSFCell soLuong = headerRow.createCell(5);
			soLuong.setCellValue("SoLuong");
			soLuong.setCellStyle(headerCellStyle);

			int i = 1;
			for (SanPham sanPham : sanPhams) {
				HSSFRow bodyRow = workSheet.createRow(i);

				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
				bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);

				HSSFCell maSPVaLue = bodyRow.createCell(0);
				maSPVaLue.setCellValue(sanPham.getMaSP());
				maSPVaLue.setCellStyle(bodyCellStyle);

				HSSFCell tenSPValue = bodyRow.createCell(1);
				tenSPValue.setCellValue(sanPham.getTenSP());
				tenSPValue.setCellStyle(bodyCellStyle);

				HSSFCell giaValue = bodyRow.createCell(2);
				giaValue.setCellValue(sanPham.getGiaTien());
				giaValue.setCellStyle(bodyCellStyle);

				HSSFCell donViTinhValue = bodyRow.createCell(3);
				donViTinhValue.setCellValue(sanPham.getDonViTinh());
				donViTinhValue.setCellStyle(bodyCellStyle);

				HSSFCell ngayDangValue = bodyRow.createCell(4);
				ngayDangValue.setCellValue(sanPham.getNgayDang());
				ngayDangValue.setCellStyle(bodyCellStyle);

				HSSFCell soLuongValue = bodyRow.createCell(5);
				soLuongValue.setCellValue(sanPham.getSoLuongConLai());
				soLuongValue.setCellStyle(bodyCellStyle);

				i++;
			}
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean createPdfSanPhamDaXoa(List<SanPham> sanPhamDaXoa, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A3, 15, 15, 45, 30);
		try {
			String filePath = context.getRealPath("/resources/reports");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}

			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(file + "/" + "sanPhamDaXoa" + ".pdf"));
			document.open();

			Font maintFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);

			Paragraph paragraph = new Paragraph("SAN PHAM DA XOA", maintFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);

			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

			float[] columnWidths = { 2f, 2f, 2f, 2f, 2f, 2f };
			table.setWidths(columnWidths);

			PdfPCell id = new PdfPCell(new Paragraph("ID", tableHeader));
			id.setBorderColor(BaseColor.BLACK);
			id.setPaddingLeft(10);
			id.setHorizontalAlignment(Element.ALIGN_CENTER);
			id.setVerticalAlignment(Element.ALIGN_CENTER);
			id.setBackgroundColor(BaseColor.GRAY);
			id.setExtraParagraphSpace(5f);
			table.addCell(id);

			PdfPCell maSP = new PdfPCell(new Paragraph("MA SAN PHAM", tableHeader));
			maSP.setBorderColor(BaseColor.BLACK);
			maSP.setPaddingLeft(10);
			maSP.setHorizontalAlignment(Element.ALIGN_CENTER);
			maSP.setVerticalAlignment(Element.ALIGN_CENTER);
			maSP.setBackgroundColor(BaseColor.GRAY);
			maSP.setExtraParagraphSpace(5f);
			table.addCell(maSP);

			PdfPCell tenSP = new PdfPCell(new Paragraph("TEN SAN PHAM", tableHeader));
			tenSP.setBorderColor(BaseColor.BLACK);
			tenSP.setPaddingLeft(10);
			tenSP.setHorizontalAlignment(Element.ALIGN_CENTER);
			tenSP.setVerticalAlignment(Element.ALIGN_CENTER);
			tenSP.setBackgroundColor(BaseColor.GRAY);
			tenSP.setExtraParagraphSpace(5f);
			table.addCell(tenSP);

			PdfPCell donViTinh = new PdfPCell(new Paragraph("DON VI TINH", tableHeader));
			donViTinh.setBorderColor(BaseColor.BLACK);
			donViTinh.setPaddingLeft(10);
			donViTinh.setHorizontalAlignment(Element.ALIGN_CENTER);
			donViTinh.setVerticalAlignment(Element.ALIGN_CENTER);
			donViTinh.setBackgroundColor(BaseColor.GRAY);
			donViTinh.setExtraParagraphSpace(5f);
			table.addCell(donViTinh);

			PdfPCell gia = new PdfPCell(new Paragraph("GIA", tableHeader));
			gia.setBorderColor(BaseColor.BLACK);
			gia.setPaddingLeft(10);
			gia.setHorizontalAlignment(Element.ALIGN_CENTER);
			gia.setVerticalAlignment(Element.ALIGN_CENTER);
			gia.setBackgroundColor(BaseColor.GRAY);
			gia.setExtraParagraphSpace(5f);
			table.addCell(gia);

			PdfPCell ngayXoa = new PdfPCell(new Paragraph("NGAY XOA", tableHeader));
			ngayXoa.setBorderColor(BaseColor.BLACK);
			ngayXoa.setPaddingLeft(10);
			ngayXoa.setHorizontalAlignment(Element.ALIGN_CENTER);
			ngayXoa.setVerticalAlignment(Element.ALIGN_CENTER);
			ngayXoa.setBackgroundColor(BaseColor.GRAY);
			ngayXoa.setExtraParagraphSpace(5f);
			table.addCell(ngayXoa);

			for (SanPham sanPham : sanPhamDaXoa) {
				PdfPCell idValue = new PdfPCell(new Paragraph(sanPham.getIdSanPham(), tableBody));
				idValue.setBorderColor(BaseColor.BLACK);
				idValue.setPaddingLeft(10);
				idValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				idValue.setVerticalAlignment(Element.ALIGN_CENTER);
				idValue.setBackgroundColor(BaseColor.WHITE);
				idValue.setExtraParagraphSpace(5f);
				table.addCell(idValue);

				PdfPCell maSPValue = new PdfPCell(new Paragraph(sanPham.getMaSP(), tableBody));
				maSPValue.setBorderColor(BaseColor.BLACK);
				maSPValue.setPaddingLeft(10);
				maSPValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				maSPValue.setVerticalAlignment(Element.ALIGN_CENTER);
				maSPValue.setBackgroundColor(BaseColor.WHITE);
				maSPValue.setExtraParagraphSpace(5f);
				table.addCell(maSPValue);

				PdfPCell tenSPValue = new PdfPCell(new Paragraph(String.valueOf(sanPham.getTenSP()), tableBody));
				tenSPValue.setBorderColor(BaseColor.BLACK);
				tenSPValue.setPaddingLeft(10);
				tenSPValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				tenSPValue.setVerticalAlignment(Element.ALIGN_CENTER);
				tenSPValue.setBackgroundColor(BaseColor.WHITE);
				tenSPValue.setExtraParagraphSpace(5f);
				table.addCell(tenSPValue);

				PdfPCell donViTinhValue = new PdfPCell(new Paragraph(sanPham.getDonViTinh(), tableBody));
				donViTinhValue.setBorderColor(BaseColor.BLACK);
				donViTinhValue.setPaddingLeft(10);
				donViTinhValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				donViTinhValue.setVerticalAlignment(Element.ALIGN_CENTER);
				donViTinhValue.setBackgroundColor(BaseColor.WHITE);
				donViTinhValue.setExtraParagraphSpace(5f);
				table.addCell(donViTinhValue);

				PdfPCell giaValue = new PdfPCell(new Paragraph(String.valueOf(sanPham.getGiaTien()), tableBody));
				giaValue.setBorderColor(BaseColor.BLACK);
				giaValue.setPaddingLeft(10);
				giaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				giaValue.setVerticalAlignment(Element.ALIGN_CENTER);
				giaValue.setBackgroundColor(BaseColor.WHITE);
				giaValue.setExtraParagraphSpace(5f);
				table.addCell(giaValue);

				PdfPCell ngayXoaValue = new PdfPCell(new Paragraph(String.valueOf(sanPham.getNgayXoa()), tableBody));
				ngayXoaValue.setBorderColor(BaseColor.BLACK);
				ngayXoaValue.setPaddingLeft(10);
				ngayXoaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				ngayXoaValue.setVerticalAlignment(Element.ALIGN_CENTER);
				ngayXoaValue.setBackgroundColor(BaseColor.WHITE);
				ngayXoaValue.setExtraParagraphSpace(5f);
				table.addCell(ngayXoaValue);

			}

			document.add(table);
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean createExcelSanPhamDaXoa(List<SanPham> sanPhamDaXoa, ServletContext context,
			HttpServletRequest request, HttpServletResponse response) {
		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if (!exists) {
			new File(filePath).mkdirs();

		}
		try {
			FileOutputStream outputStream = new FileOutputStream(file + "/" + "sanPhamDaXoa" + ".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet workSheet = workbook.createSheet("SanPhamDaXoa");
			workSheet.setDefaultColumnWidth(30);

			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			HSSFRow headerRow = workSheet.createRow(0);

			HSSFCell maSP = headerRow.createCell(0);
			maSP.setCellValue("ID");
			maSP.setCellStyle(headerCellStyle);

			HSSFCell tenSP = headerRow.createCell(1);
			tenSP.setCellValue("MA SP");
			tenSP.setCellStyle(headerCellStyle);

			HSSFCell gia = headerRow.createCell(2);
			gia.setCellValue("TEN SP");
			gia.setCellStyle(headerCellStyle);

			HSSFCell donViTinh = headerRow.createCell(3);
			donViTinh.setCellValue("DON VI TINH");
			donViTinh.setCellStyle(headerCellStyle);

			HSSFCell ngayDang = headerRow.createCell(4);
			ngayDang.setCellValue("GIA");
			ngayDang.setCellStyle(headerCellStyle);

			HSSFCell soLuong = headerRow.createCell(5);
			soLuong.setCellValue("NGAY XOA");
			soLuong.setCellStyle(headerCellStyle);

			int i = 1;
			for (SanPham sanPham : sanPhamDaXoa) {
				HSSFRow bodyRow = workSheet.createRow(i);

				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
				bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);

				HSSFCell maSPVaLue = bodyRow.createCell(0);
				maSPVaLue.setCellValue(sanPham.getIdSanPham());
				maSPVaLue.setCellStyle(bodyCellStyle);

				HSSFCell tenSPValue = bodyRow.createCell(1);
				tenSPValue.setCellValue(sanPham.getMaSP());
				tenSPValue.setCellStyle(bodyCellStyle);

				HSSFCell giaValue = bodyRow.createCell(2);
				giaValue.setCellValue(sanPham.getTenSP());
				giaValue.setCellStyle(bodyCellStyle);

				HSSFCell donViTinhValue = bodyRow.createCell(3);
				donViTinhValue.setCellValue(sanPham.getDonViTinh());
				donViTinhValue.setCellStyle(bodyCellStyle);

				HSSFCell ngayDangValue = bodyRow.createCell(4);
				ngayDangValue.setCellValue(sanPham.getGiaTien());
				ngayDangValue.setCellStyle(bodyCellStyle);

				HSSFCell soLuongValue = bodyRow.createCell(5);
				soLuongValue.setCellValue(sanPham.getNgayXoa());
				soLuongValue.setCellStyle(bodyCellStyle);

				i++;
			}
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}
}
