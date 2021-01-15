function getDonHangById(id) {
	$
			.ajax({
				url : 'http://localhost:8083/admin/donHang/' + id,
				type : 'GET',
				success : function(donHangDTOss) {
					document.getElementById("idDonHang").value = donHangDTOss.donHang.idDonHang;
					document.getElementById("ngayLapDonHang").value = donHangDTOss.donHang.ngayLapDH;
					document.getElementById("hoTen").value = donHangDTOss.khachHang.hoTen;
					document.getElementById("tenSP").value = donHangDTOss.tenSP;
					document.getElementById("soLuong").value = donHangDTOss.dh_ChiTiet.soLuong;
					document.getElementById("donGia").value = donHangDTOss.dh_ChiTiet.donGia
							+ " " + "đ";
					document.getElementById("thue").value = donHangDTOss.donHang.thue
							+ "%";
					document.getElementById("tongTien").value = donHangDTOss.donHang.tongSoTien;
					document.getElementById("diaChi").value = donHangDTOss.donHang.diaChiGiaoHang;
				},
				error : function() {
					console.log('Lỗi rồi!))');
				}
			});
}