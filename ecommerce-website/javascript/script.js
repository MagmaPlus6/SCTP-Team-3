// ----------------------- Add new product page --------------------------

// --- "sizes available" section ---

// There is no validation for multiple checkboxes
// So need to create

// Challenge: To use the same default HTML validation error message 

const productSizeM = document.querySelector('#size-m');

// Added required attribute to size M 
productSizeM.setAttribute('required', '')
productSizeM.setCustomValidity("Please select at least one option.");

const checkBoxes = document.querySelectorAll('input[type="checkbox"]')

//Loop through the array elements and attach the event
for (var i = 0; i < checkBoxes.length; i++) {
  checkBoxes[i].addEventListener("change", checkedOrNot);
}

//Define separate function
function checkedOrNot() {

  // Must define checkedBoxes here, not at the beginning
  // So that the number of checked boxes changes every time a box is checked
  let checkedBoxes = document.querySelectorAll('input[type="checkbox"]:checked')

  if (checkedBoxes.length >= 1) {

    // If don't reset the message, cannot remove the required attribute
    productSizeM.setCustomValidity("")
    productSizeM.removeAttribute('required')
  } else {
    productSizeM.setAttribute('required', '')
    productSizeM.setCustomValidity("Please select at least one option.");
  }
}

// ------------------------ Display uploaded image on the --------------------------------------------

productImage.onchange = evt => {
  const [file] = productImage.files
  if (file) {
    upLoadImg.src = URL.createObjectURL(file)
  }
}
