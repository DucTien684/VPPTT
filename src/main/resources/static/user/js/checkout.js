//Defining a function to display error message

function printError(elemId, hintMsg) {
	document.getElementById(elemId).innerHTML = hintMsg;
}

// Defining a function to validate form
function validateForm1() {

	// Retrieving the values of form elements
	var name = document.checkoutForm1.ten.value;
	var email = document.checkoutForm1.email.value;
	var mobile = document.checkoutForm1.sdt.value;
	var address = document.checkoutForm1.diaChi.value;
	var province = document.checkoutForm1.tinh.value;
	var district = document.checkoutForm1.huyen.value;
	var ward = document.checkoutForm1.xa.value;
	
	/*var cbox = document.checkoutForm.addPass;
	var pass = document.checkoutForm.matKhau.value;
	var checkPass = document.checkoutForm.checkMatKhau.value;*/

	// Defining error variables with a default value
	var nameErr = emailErr = mobileErr = addressErr = provinceErr = districtErr = wardErr = true;

	// Validate name
	if (name == "") {
		printError("nameErr", "*Vui lòng nhập họ tên!");
	} else {
		printError("nameErr", " ");
		nameErr = false;
	}

	// Validate email address
	if (email == "") {
		printError("emailErr", "*Vui lòng nhập email");
	} else {
		// Regular expression for basic email validation
		var regex = /^\S+@\S+\.\S+$/;
		if (regex.test(email) === false) {
			printError("emailErr", "*Vui lòng nhập đúng định dạng email");
		} else {
			printError("emailErr", " ");
			emailErr = false;
		}
	}

	// Validate mobile number
	if (mobile == "") {
		printError("mobileErr", "*Vui lòng nhập số điện thoại");
	} else {
		var regex = /^[0-9]\d{9}$/;
		if (regex.test(mobile) === false) {
			printError("mobileErr", "*Vui lòng nhập đúng số điện thoại");
		}
		else {
			printError("mobileErr", " ");
			mobileErr = false;
		}
	}

	// Validate address
	if (address == "") {
		printError("addressErr", "*Vui lòng nhập địa chỉ");
	} else {
		printError("addressErr", " ");
		addressErr = false;
	}

	// Validate province
	if (province == "") {
		printError("provinceErr", "*Vui lòng chọn tỉnh / thành phố");
	} else {
		printError("provinceErr", " ");
		provinceErr = false;
	}

	// Validate district
	if (district == "") {
		printError("districtErr", "*Vui lòng chọn quận / huyện");
	} else {
		districtErr("districtErr", " ");
		districtErr = false;
	}

	// Validate ward
	if (ward == "") {
		printError("wardErr", "*Vui lòng chọn xã / phường");
	} else {
		printError("wardErr", " ");
		wardErr = false;
	}
	
	/*if(cbox.checked) {
		if (pass == ""){
			printError("passErr", "*Vui lòng nhập mật khẩu");
		} else if (pass.length != "" && pass.length < 6){
			printError("passErr", "*Mật khẩu có ít nhất 6 ký tự");
			
		} else {
			printError("passErr", " ");
			newPassErr = false;
		}
		if (checkPass == ""){
			printError("checkPassErr", "*Vui lòng xác nhận mật khẩu");
		} else if (pass != checkPass){
			printError("checkPassErr", "*Mật khẩu không trùng khớp");
		} else {
			printError("passErr", " ");
			printError("checkPassErr", " ");
			passErr = false;
			checkPassErr = false;
		}
	    
	}*/

	// Prevent the form from being submitted if there are any errors
	if ((nameErr || emailErr || mobileErr || addressErr || provinceErr
			|| districtErr || wardErr ) == true) {
		return false;
	}

}

/*function ValidateFormLoginCheckout() {
	var myForm = document.forms["loginCheckout"];

	var email = myForm["emailCK"].value;
	var pass = myForm["pass"].value;

	var reg_mail = /^[A-Za-z0-9]+([_\.\-]?[A-Za-z0-9])*@[A-Za-z0-9]+([\.\-]?[A-Za-z0-9]+)*(\.[A-Za-z]+)+$/;

	if (email == "") {
		document.getElementById("errorEmail").innerHTML = '*Vui lòng nhập Email!';
		myForm["emailCK"].focus();
		return false;
	} else if (reg_mail.test(email) == false) {
		document.getElementById("errorEmail").innerHTML = '*Vui lòng nhập đúng định dạng Email!';
		myForm["emailCK"].focus();
		return false;
	} else {
		document.getElementById("errorEmail").innerHTML = '';
	}

	if (pass == "") {
		document.getElementById("errorPass").innerHTML = '*Vui lòng nhập mật khẩu!';
		myForm["pass"].focus();
		return false;
	} else {
		document.getElementById("errorPass").innerHTML = '';
	}

	return true;
}*/