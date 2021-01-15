// Call the dataTables jQuery plugin
toastr.options = {
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
    let code = e.target.dataset.code;
    let id = e.target.dataset.id;
    if (e.target != 'i.fas.fa-trash-alt')
    {
    	id = e.currentTarget.dataset.id;
    }
    Swal.fire({
      title: 'Xóa nhân viên?',
      text: `Bạn có chắc xóa nhân viên với ID: ${id}!`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Xóa',
      cancelButtonText: 'Hủy'
    }).then((result) => {
      if (result.value) {
      $.ajax({
        url : '/admin/deleteNV/' + id,
        method: 'post'
      })
          .done(function(res){
            if(res){
              myTable.row('.selected').remove().draw();
              toastr.success('Xóa thành công nhân viên!', 'Thông báo');
            }else{
              toastr.error("Xóa thất bại!", "Thông Báo");
            }
          });
    }
  })
  });
});
