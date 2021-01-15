function printError(elemId, hintMsg) {
	document.getElementById(elemId).innerHTML = hintMsg;
}

// Defining a function to validate form
function validateFormLogin() {

	// Retrieving the values of form elements
	var email = document.loginCheckout.emailCK.value;
	var pass = document.loginCheckout.pass.value;

	// Defining error variables with a default value
	var errorEmail = errorPass = true;

	if (pass.length == "") {
		printError("errorPass", "*Vui lòng nhập mật khẩu!");

	} else if (pass.length != "" && pass.length < 6) {
		printError("errorPass", "*Mật khẩu có ít nhất 6 ký tự!");

	} else {
		printError("errorPass", " ");
		errorPass = false;
	}

	// Validate email address
	if (email == "") {
		printError("errorEmail", "*Vui lòng nhập email!");
	} else {
		// Regular expression for basic email validation
		var regex = /^\S+@\S+\.\S+$/;
		if (regex.test(email) === false) {
			printError("errorEmail", "*Vui lòng nhập đúng định dạng email!");
		} else {
			printError("errorEmail", " ");
			errorEmail = false;
		}
	}

	// Prevent the form from being submitted if there are any errors
	if ((errorEmail || errorPass) == true) {
		return false;
	}

}