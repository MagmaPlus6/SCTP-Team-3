
// Initialize a new ItemsController with currentId set to 0
const productsController = new Controller();

// Select the New Item Form
const newProductForm = document.querySelector('#form1');

// Add an 'onsubmit' event listener
newProductForm.addEventListener('submit', (event) => {
    // Prevent default action
    event.preventDefault();

    // Select the inputs
    const newProductName = document.querySelector('#productName');
    const newProductBrand = document.querySelector('#productBrand');
    const newProductPrice = document.querySelector('#productPrice');
    const newProductImage = document.querySelector('#productImage');    

    // Get the values of the inputs
    const name = newProductName.value;
    const brand = newProductBrand.value;
    const price = newProductPrice.value;
    const image = newProductImage.value;

    /*
        Validation code here
    */

    // Add the item to the ItemsController
    productsController.addProduct(name, brand, price, image);


    // Run BootStrap4's toast to show the activity is complete.
    $('.toast').toast('show');

    // Clear the form
    // newProductName.value = '';
    // newProductBrand.value = '';   
    // newProductPrice.value = '';  
    // newProductImage.value = '';  

    // think of how to reset entire field after submit

});

// Eventhandler in jQuery for Bootstrap4's toast. 
// Opens the products listed in another page after the [x] is clicked
$('.toast').on('hidden.bs.toast', function () {
    
    // basic way of opening a new page in javascript
    // document.location.href='cards.html';
    
    // open a page in a new tab
    // window.open('cards.html', '_blank');
    window.open("product_list.html", "productlistwindow");
})
