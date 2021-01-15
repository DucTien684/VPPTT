
function getKhachHangById(id)
{
	$.ajax({
        url: 'http://localhost:8083/admin/khachHang/' + id,
        type: 'GET',
        success: function (khachHang) {	
        	document.getElementById("idKhachHang").value= khachHang.idKhachHang;
        	document.getElementById("hoTen").value= khachHang.hoTen;
        	document.getElementById("sdt").value= khachHang.sdt;
        	document.getElementById("email").value= khachHang.email;
        	var listDiaChi = khachHang.soDiaChi;
        	var len = listDiaChi.length;
        	listDiaChi.forEach(function(diaChi, index){
        		document.getElementById("dc" + index).style.display = "block";
        		document.getElementById("idc" + index).value= diaChi.diaChiCuThe + ", " +diaChi.xa + ", " + diaChi.huyen + ", " + diaChi.tinh;
        	})
        	var i = 3-len;
        	var j=1;
        	while(j<=i)
        		{
        			var index= 3 - j;
        			document.getElementById("dc"+index).style.display = "none";
        			++j;
        		}
        	if (len===0)
        		{
	        		document.getElementById("errorDiaChi").style.display = "block";
        		}
        	else
        		{
        			document.getElementById("errorDiaChi").style.display = "none";
        		}
        	
        },
        error: function () {
            console.log('ss');
        }
    });
}