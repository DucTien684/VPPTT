function printError(elemId, hintMsg) {
	document.getElementById(elemId).innerHTML = hintMsg;
}

// Defining a function to validate form
function validateForm1() {

	// Retrieving the values of form elements
	var name = document.checkoutForm1.ten.value;
	var mobile = document.checkoutForm1.sdt.value;
	var address = document.checkoutForm1.diaChi.value;
	var province = document.checkoutForm1.province.value;
	var district = document.checkoutForm1.district.value;
	var ward = document.checkoutForm1.ward.value;

	// Defining error variables with a default value
	var nameErr = mobileErr = addressErr = provinceErr = districtErr = wardErr = true;

	// Validate name
	if (name == "") {
		printError("nameErr", "*Vui lòng nhập họ tên!");
	} else {
		printError("nameErr", " ");
		nameErr = false;
	}

	// Validate mobile number
	if (mobile == "") {
		printError("mobileErr", "*Vui lòng nhập số điện thoại!");
	} else {
		var regex = /^[0-9]\d{9}$/;
		if (regex.test(mobile) === false) {
			printError("mobileErr", "*Vui lòng nhập đúng định dạng số điện thoại!");
		} else {
			printError("mobileErr", " ");
			mobileErr = false;
		}
	}

	// Validate address
	if (address == "") {
		printError("addressErr", "*Vui lòng nhập địa chỉ!");
	} else {
		printError("addressErr", " ");
		addressErr = false;
	}

	// Validate province
	if (province == "") {
		printError("provinceErr", "*Vui lòng chọn Tỉnh / Thành phố!");
	} else {
		printError("provinceErr", " ");
		provinceErr = false;
	}

	// Validate district
	if (district == "") {
		printError("districtErr", "*Vui lòng chọn Quận / Huyện!");
	} else {
		printError("districtErr", " ");
		districtErr = false;
	}

	// Validate ward
	if (ward == "") {
		printError("wardErr", "*Vui lòng chọn Xã / Phường!");
	} else {
		printError("wardErr", " ");
		wardErr = false;
	}

	// Prevent the form from being submitted if there are any errors
	if ((nameErr || mobileErr || addressErr || provinceErr || districtErr || wardErr) == true) {
		return false;
	}

}