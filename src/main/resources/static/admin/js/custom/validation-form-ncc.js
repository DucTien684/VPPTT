//Defining a function to display error message

	function printError(elemId, hintMsg) {
		document.getElementById(elemId).innerHTML = hintMsg;
	}

	// Defining a function to validate form
	function validateFormNCC() {

		// Retrieving the values of form elements
		var name = document.updateFormNCC.tenNCC.value;
		var diaChi = document.updateFormNCC.diaChi.value;
		diaChi
	

		// Defining error variables with a default value
		var  nameErr =  diaChiErr = true;

		
		// Validate name
		if (name == "") {
			printError("nameErr", "*Vui lòng nhập tên nhà cung cấp!");
		} else {
			printError("nameErr", " ");
			nameErr = false;
		}

		
		// Validate diaChi
		if (diaChi == "") {
			printError("diaChiErr", "*Vui lòng nhập địa chỉ của nhà cung cấp!");
		} else {
			printError("diaChiErr", " ");
			diaChiErr = false;
		}
	
		// Prevent the form from being submitted if there are any errors
		if (( nameErr  || diaChiErr ) == true) {
			return false;
		}

	}