//	Validate form - thêm mới sản phẩm.

//	Defining a function to display error message.
function printError(elemId, hintMsg) {
	document.getElementById(elemId).innerHTML = hintMsg;
}

// 	Defining a function to validate form.
function validateFormAddSP() {

// 	Retrieving the values of form elements.
	var tenSP = document.updateFormAddSP.tenSP.value;
	var thuongHieu = document.updateFormAddSP.thuongHieu.value;
	var sanXuat = document.updateFormAddSP.sanXuat.value;


//	 Defining error variables with a default value.
	var tenSPErr = thuongHieuErr = sanXuatErr = true;

//	Validate tên sản phẩm
	if (tenSP == "") {
		printError("tenSPErr", "*Vui lòng nhập tên sản phẩm!");
	} else {
		printError("tenSPErr", " ");
		tenSPErr = false;
	}
	
//	Validate thương hiệu
	if (thuongHieu == "") {
		printError("thuongHieuErr", "*Vui lòng nhập thương hiệu của sản phẩm!");
	} else {
		printError("thuongHieuErr", " ");
		thuongHieuErr = false;
	}
	
//	Validate sản xuất
	if (sanXuat == "") {
		printError("sanXuatErr", "*Vui lòng nhập nơi sản xuất!");
	} else {
		printError("sanXuatErr", " ");
		sanXuatErr = false;
	}
		 

	//	Prevent the form from being submitted if there are any errors
	if ((tenSPErr || thuongHieuErr || sanXuatErr) == true) {
		return false;
	}

}