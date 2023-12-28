
// Select the New Item Form
const newProductForm = document.querySelector('#createNewProductForm');

const defaultImage = document.querySelector('#upLoadImg')

// To fix the "cannot read property 'addEventListener' of null" error, check that the element is not null before calling the addEventListener() method on it.
if(newProductForm) {

    // Add an 'onsubmit' event listener
    newProductForm.addEventListener('submit', async (event) => {

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

        // Upload the image to local system first, then use the unique image name to store in products database
        // In case there are other images with same name
        const imageToUpload = newProductImage.files[0];
        const formData = new FormData();
        formData.append("imageFile", imageToUpload);
        const uploadedImageName = await uploadImage(formData);
     
        // Add the product to the productsController (add to database)
        productsController.createNewProduct(name, brand, price, uploadedImageName);
    
        // Run BootStrap4's toast to show the activity is complete.
        $('.toast').toast('show');
    
        // think of how to reset entire field after submit
        newProductForm.reset();
        defaultImage.src = "image/blank-image.png"
    
    });
}


// Eventhandler in jQuery for Bootstrap4's toast. 
// Opens the products listed in another page after the [x] is clicked
$('.toast').on('hidden.bs.toast', function () {
    
    // basic way of opening a new page in javascript
    // document.location.href='cards.html';
    
    // open a page in a new tab
    // window.open('cards.html', '_blank');
    window.open("product_list.html", "productlistwindow");
})