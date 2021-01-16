//function getDonHangById(id) {
//	$
//			.ajax({
//				url : 'http://localhost:8083/admin/donHang/' + id,
//				type : 'GET',
//				success : function(donHangDTOs) {
//					document.getElementById("idDonHang").value = donHangDTOs.donHang.idDonHang;
//					document.getElementById("ngayLapDonHang").value = donHangDTOs.donHang.ngayLapDH;
//					document.getElementById("hoTen").value = donHangDTOs.khachHang.hoTen;
//					document.getElementById("tenSP").value = donHangDTOs.tenSP;
//					document.getElementById("soLuong").value = donHangDTOs.dh_ChiTiet.soLuong;
//					document.getElementById("donGia").value = donHangDTOs.dh_ChiTiet.donGia
//							+ " " + "đ";
//					document.getElementById("thue").value = donHangDTOs.donHang.thue
//							+ "%";
//					document.getElementById("tongTien").value = donHangDTOs.donHang.tongSoTien;
//					document.getElementById("diaChi").value = donHangDTOs.donHang.diaChiGiaoHang;
//					console.log(donHangDTOs);
//				},
//				error : function() {
//					console.log('Lỗi rồi!))');
//				}
//			});
//}