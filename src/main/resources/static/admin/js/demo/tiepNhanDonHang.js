// Call the dataTables jQuery plugin
toastr.options ={
  "closeButton": true,
  "debug": false,
  "newestOnTop": false,
  "progressBar": true,
  "positionClass": "toast-top-right",
  "preventDuplicates": false,
  "onclick": null,
  "showDuration": "300",
  "hideDuration": "1000",
  "timeOut": "5000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}

$(document).ready(function() {

  $('#dataTable').DataTable();

  let myTable = $('#dataTable-remove-sp').DataTable();

  $('#dataTable-remove-sp').fadeIn(1000);

  $('#dataTable-remove-sp tbody').on( 'click', 'tr td button.remove-sp', function (e) {
    myTable.$('tr.selected').removeClass('selected');
    $(this).parent().parent().addClass('selected');
    let id = e.target.dataset.id;
    if (e.target != 'fas fa-check')
    {
    	id = e.currentTarget.dataset.id;
    }
    Swal.fire({
      title: 'Tình trạng đơn hàng?',
      text: `Xác nhận đơn hàng với ID: ${id} đã giao thành công!`,
      type: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Xác nhận',
      cancelButtonText: 'Hủy'
    }).then((result) => {
      if (result.value) {
      $.ajax({
        url : '/admin//tiepNhanDonHang/' + id,
        method: 'post'
      })
          .done(function(res){
            if(res){
              myTable.row('.selected').remove().draw();
              toastr.success('Đơn hàng đã giao thành công', 'Thông báo');
            }else{
              toastr.error("Đơn hàng đã giao thất bại", "Thông Báo");
            }
          });
    }
  })
  });
});
