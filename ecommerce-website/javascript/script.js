
productImage.onchange = evt => {
  const [file] = productImage.files
  if (file) {
    upLoadImg.src = URL.createObjectURL(file)
  }
}




/*
$(document).on('change', '.file-input', function() {
    var filesCount = $(this)[0].files.length;
    
    var textbox = $(this).prev();
  
    if (filesCount === 1) {
      var fileName = $(this).val().split('\\').pop();
      textbox.text(fileName);
    } else {
      textbox.text(filesCount + ' files selected');
    }
});
*/