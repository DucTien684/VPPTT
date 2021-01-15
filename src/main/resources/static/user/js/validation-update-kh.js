//Defining a function to display error message

function printError(elemId, hintMsg) {
	document.getElementById(elemId).innerHTML = hintMsg;
}

// Defining a function to validate form
function validateForm() {

	// Retrieving the values of form elements
	var name = document.updateFormKH.hoTen.value;
	var sdt = document.updateFormKH.sdt.value;
	// var email = document.updateFormKH.email.value;

	var cbox = document.updateFormKH.addPass;
	var oldPass = document.updateFormKH.mkcu.value;
	var newPass = document.updateFormKH.mkmoi.value;
	var checkPass = document.updateFormKH.nhaplaimk.value;

	// Defining error variables with a default value
	var nameErr = sdtErr = oldPassErr = newPassErr = checkPassErr = true;

	// Validate name
	if (name == "") {
		printError("nameErr", "*Vui lòng nhập họ tên!");
	} else {
		printError("nameErr", " ");
		nameErr = false;
	}

	/*
	 * // Validate email address if (email == "") { printError("emailErr", "*Vui
	 * lòng nhập email"); } else { // Regular expression for basic email
	 * validation var regex = /^\S+@\S+\.\S+$/; if (regex.test(email) == false) {
	 * printError("emailErr", "*Vui lòng nhập đúng định dạng email"); } else {
	 * printError("emailErr", " "); emailErr = false; } }
	 */

	// Validate phoneNumber
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

	if (cbox.checked) {
		if (oldPass == "") {
			printError("oldPassErr", "*Vui lòng nhập mật khẩu cũ!");
		} else {
			printError("oldPassErr", " ");
			oldPassErr = false;
		}
		//
		if (newPass.length == "") {
			printError("newPassErr", "*Vui lòng nhập mật khẩu mới!");

		} else if (newPass.length != "" && newPass.length < 6) {
			printError("newPassErr", "*Mật khẩu có ít nhất 6 ký tự!");

		} else {
			printError("newPassErr", " ");
			newPassErr = false;
		}

		if (checkPass == "") {
			printError("checkPassErr", "*Vui lòng xác nhận mật khẩu!");
		} else if (newPass != checkPass) {
			printError("checkPassErr", "*Mật khẩu không trùng khớp!");
		} else {
			printError("newPassErr", " ");
			printError("checkPassErr", " ");
			newPassErr = false;
			checkPassErr = false;
		}
	} else {
		oldPassErr = false;
		newPassErr = false;
		checkPassErr = false;
	}

	// Prevent the form from being submitted if there are any errors
	if ((nameErr || sdtErr || oldPassErr || newPassErr || checkPassErr) == true) {
		return false;
	}

}