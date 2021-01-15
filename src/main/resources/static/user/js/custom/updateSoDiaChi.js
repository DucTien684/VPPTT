$(document).ready(function(){
	var provinceDefault = document.getElementById("provinceDefault").value;
	var districtDefault = document.getElementById("districtDefault").value;
	var wardDefault = document.getElementById("wardDefault").value;
	document.getElementById(provinceDefault).selected = true;
	const provinceId = provinceDefault;
	
	$.ajax({
        url: 'http://localhost:8083/getDistrict?provinceId=' + provinceId,
        type: 'GET',
        success: function (listDistrict) {
        	$("#districtProvinceGroup").empty();
        	$("#districtProvinceGroup").prop('disabled', false);
        	listWard.unshift({
        		wardId: '',
        		name: 'Chọn Xã / Phường'
        	});
        	$.each(listWard, function (index, district) {
    	        $('#districtProvinceGroup').append($('<option>', { 
    	            value: district.districtId,
    	            text : district.name 
    	        }));
        	});
        },
        error: function (e) {
            console.log(e.message);
        }
    });
});


$('#provinceGroup').change(function() { 
	const provinceId = $('#provinceGroup option:selected').val();
	if (provinceId==="")
		{
			$("#districtProvinceGroup").empty();
			$('#districtProvinceGroup').append($('<option>', { 
 	            value: "",
 	            text : "Chọn Tỉnh / Thành phố" 
 	        }));
			$('#districtProvinceGroup').prop('disabled', true);
			$("#wardDistrictProvinceGroup").empty();
			 $('#wardDistrictProvinceGroup').append($('<option>', { 
	 	            value: "",
	 	            text : "Chọn Xã / Phường" 
	 	        }));
        	$("#wardDistrictProvinceGroup").prop('disabled', true);
		}
	else
		{
			$.ajax({
		        url: 'http://localhost:8083/getDistrict?provinceId=' + provinceId,
		        type: 'GET',
		        success: function (listDistrict) {
		        	$("#districtProvinceGroup").empty();
		        	$('#districtProvinceGroup').prop('disabled', false);
		        	listDistrict.unshift({
		        		districtId: '',
		        		name: 'Chọn Quận / Huyện'
		        	});
		        	$.each(listDistrict, function (index, district) {
		    	        $('#districtProvinceGroup').append($('<option>', {
		    	            value: district.districtId,
		    	            text : district.name 
		    	        }
		    	        ));
		        	});
		        },
		        error: function (e) {
		            console.log(e.message);
		        }
		    });
		}
	
});

$('#districtProvinceGroup').change(function() { 
	const districtId = $('#districtProvinceGroup option:selected').val();
	$.ajax({
        url: 'http://localhost:8083/getWard?districtId=' + districtId,
        type: 'GET',
        success: function (listWard) {
        	$("#wardDistrictProvinceGroup").empty();
        	$("#wardDistrictProvinceGroup").prop('disabled', false);
        	listWard.unshift({
        		wardId: '',
        		name: 'Chọn Xã / Phường'
        	});
        	$.each(listWard, function (index, ward) {
    	        $('#wardDistrictProvinceGroup').append($('<option>', { 
    	            value: ward.wardId,
    	            text : ward.name 
    	        }));
        	});
        },
        error: function (e) {
            console.log(e.message);
        }
    });
});