var inputs = document.forms['register'].getElementsByTagName('input');
	var run_onchange = false;
	function valid() {
		var errors = false;
		var reg_mail = /^[A-Za-z0-9]+([_\.\-]?[A-Za-z0-9])*@[A-Za-z0-9]+([\.\-]?[A-Za-z0-9]+)*(\.[A-Za-z]+)+$/;
		for (var i = 0; i < inputs.length; i++) {
			var value = inputs[i].value;
			var id = inputs[i].getAttribute('id');

			// Tạo phần tử span lưu thông tin lỗi
			var span = document.createElement('span');
			// Nếu span đã tồn tại thì remove
			var p = inputs[i].parentNode;
			if (p.lastChild.nodeName == 'SPAN') {
				p.removeChild(p.lastChild);
			}

			// Kiểm tra rỗng
			if (value == '') {
				span.innerHTML = 'Thông tin được yêu cầu*';
			} else {
				// Kiểm tra các trường hợp khác
				if (id == 'email') {
					if (reg_mail.test(value) == false) {
						span.innerHTML = 'Email không hợp lệ! (Ví dụ: abc@gmail.com)';
					}
					
					var email = value;
				}

				// Kiểm tra password
				if (id == 'pass') {
					if (value.length < 6) {
						span.innerHTML = 'Password phải từ 6 ký tự trở lên!';
					}
					var pass = value;
				}
				// Kiểm tra password nhập lại
				if (id == 'confirm_pass' && value != pass) {
					span.innerHTML = 'Password nhập lại chưa đúng!';
				}
				// Kiểm tra số điện thoại
				if (id == 'sdt' && isNaN(value) == true) {
					span.innerHTML = 'Số điện thoại phải là kiểu số!';
				} if (id == 'sdt') {
					if (value.length != 10) {
						span.innerHTML = 'Vui lòng nhập đúng độ dài Sđt!';
					}
					var pass = value;
				}
			}

			// Nếu có lỗi thì chèn span vào hồ sơ, chạy onchange, submit return false, highlight border
			if (span.innerHTML != '') {
				inputs[i].parentNode.appendChild(span);
				errors = true;
				run_onchange = true;
//				inputs[i].style.border = '1px solid red';
				span.style.color = 'red';
//				inputs[i].style.background = '#F2F2F2';
			}
		}// end for

		/*if (errors == false) {
			alert('Đăng ký thành công');
		}*/
		return !errors;
	}// end valid()

	// Chạy hàm kiểm tra valid()
	var register = document.getElementById('submit');
	register.onclick = function() {
		return valid();
	}

	// Kiểm tra lỗi với sự kiện onchange -> gọi lại hàm valid()
	for (var i = 0; i < inputs.length; i++) {
		var id = inputs[i].getAttribute('id');
		inputs[i].onchange = function() {
			if (run_onchange == true) {
//				this.style.border = '1px solid #999';
				this.style.background = '#fff';
				valid();
			}
		}
	}// end for