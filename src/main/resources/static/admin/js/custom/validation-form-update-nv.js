//Defining a function to display error message

function printError(elemId, hintMsg) {
	document.getElementById(elemId).innerHTML = hintMsg;
}

// Defining a function to validate form
function validateFormNV() {

	// Retrieving the values of form elements
	var name = document.updateFormNV.hoTen.value;
	var email = document.updateFormNV.email.value;
	var pass = document.updateFormNV.pass.value;
	var diaChi = document.updateFormNV.diaChi.value;
	// var vaiTro = document.updateFormAddNV.vaiTro.value;
	var gioiTinh = document.updateFormNV.gioiTinh.value;
	var ngaySinh = document.updateFormNV.ngaySinh.value;
	var sdt = document.updateFormNV.sdt.value;

	// Defining error variables with a default value
	var nameErr = emailErr = passErr = diaChiErr = gioiTinhErr = ngaySinhErr = sdtErr = true;

	// Validate name
	if (name == "") {
		printError("nameErr", "*Vui lòng nhập tên nhân viên");
	} else {
		printError("nameErr", " ");
		nameErr = false;
	}

	// Validate email address
	if (email == "") {
		printError("emailErr", "*Vui lòng nhập email!");
	} else {
		// Regular expression for basic email validation
		var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if (regex.test(email) == false) {
			printError("emailErr", "*Vui lòng nhập đúng định dạng email!");
		} else {
			printError("emailErr", " ");
			emailErr = false;
		}
	}

	// validate pass
	if (pass.length == "") {
		printError("passErr", "*Vui lòng nhập mật khẩu!");

	} else if (pass.length != "" && pass.length < 6) {
		printError("passErr", "*Mật khẩu có ít nhất 6 ký tự!");

	} else {
		printError("passErr", " ");
		passErr = false;
	}

	// Validate địa chỉ
	if (diaChi == "") {
		printError("diaChiErr", "*Vui lòng nhập địa chỉ!");
	} else {
		printError("diaChiErr", " ");
		diaChiErr = false;
	}

	// Validate giới tính
	if (gioiTinh == "") {
		printError("gioiTinhErr", "*Vui lòng nhập giới tính!");
	} else {
		printError("gioiTinhErr", " ");
		gioiTinhErr = false;
	}

	// Validate ngày sinh
	if (ngaySinh == "") {
		printError("ngaySinhErr", "*Vui lòng nhập ngày sinh!");
	} else {
		printError("ngaySinhErr", " ");
		ngaySinhErr = false;
	}

	// Validate số điện thoại

	if (sdt == "") {
		printError("sdtErr", "*Vui lòng nhập số điện thoại!");
	} else {
		// Regular expression for basic phoneNumber validation.
		var regex = /((09|03|07|08|05)+([0-9]{8})\b)/;
		if (regex.test(sdt) == false) {
			printError("sdtErr", "*Vui lòng nhập đúng định dạng số điện thoại!");
		} else {
			printError("sdtErr", " ");
			sdtErr = false;
		}
	}
	// Prevent the form from being submitted if there are any errors
	if ((nameErr || emailErr || passErr || diaChiErr || gioiTinhErr
			|| ngaySinhErr || sdtErr) == true) {
		return false;
	}

}