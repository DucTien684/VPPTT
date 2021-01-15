function printError(elemId, hintMsg) {
	document.getElementById(elemId).innerHTML = hintMsg;
}

// Defining a function to validate form
function validateForm() {

	// Retrieving the values of form elements
	var email = document.checkForm.emailForgot.value;

	// Defining error variables with a default value
	var emailErr = true;

	// Validate email address
	if (email == "") {
		printError("emailErr", "*Vui lòng nhập email!");
	} else {
		// Regular expression for basic email validation
		var regex = /^\S+@\S+\.\S+$/;
		if (regex.test(email) === false) {
			printError("emailErr", "*Vui lòng nhập đúng định dạng email!");
		} else {
			printError("emailErr", " ");
			emailErr = false;
		}
	}

	// Prevent the form from being submitted if there are any errors
	if ((emailErr) == true) {
		return false;
	}

}