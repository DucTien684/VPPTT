$(document).ready(function() {
	var spNhomDefault = document.getElementById("spNhomDefault").value;
	var spNhomConDefault = document.getElementById("spNhomConDefault").value;
	document.getElementById(spNhomDefault).selected = true;
	const idNhomSP = spNhomDefault;
	$.ajax({
		url : 'http://localhost:8083/admin/getNhomCon?idNhomSP=' + idNhomSP,
		type : 'GET',
		success : function(listSPCon) {
			$("#childrenProductGroup").empty();
			$.each(listSPCon, function(index, spCon) {
				$('#childrenProductGroup').append($('<option>', {
					value : spCon.idNhomSPCon,
					text : spCon.name
				}));
			});
		},
		error : function(e) {
			console.log(e.message);
		}
	});
	document.getElementById(spNhomConDefault).selected = true;
});

$('#productGroup').change(function() {
	const idNhomSP = $('#productGroup option:selected').val();
	$.ajax({
		url : 'http://localhost:8083/admin/getNhomCon?idNhomSP=' + idNhomSP,
		type : 'GET',
		success : function(listSPCon) {
			$("#childrenProductGroup").empty();
			$.each(listSPCon, function(index, spCon) {
				$('#childrenProductGroup').append($('<option>', {
					value : spCon.idNhomSPCon,
					text : spCon.name
				}));
			});
		},
		error : function(e) {
			console.log(e.message);
		}
	});
});