
// Function to get all products
const getAllProducts = async () => {
    try {

// https://gensg-final-project.up.railway.app/api/products
// http://localhost:8080/api/products

        const response = await fetch('https://gensg-final-project.up.railway.app/api/products');

        if(response.ok) {
            const jsonResponse = await response.json();
            productsController.products = jsonResponse;
            productsController.displayCart(jsonResponse);
            productsController.storeDataToLocalStorage(jsonResponse);
            // productsController.loadDataFromLocalStorage();

            console.log('Success:', productsController.products);
        }
        // throw new Error('Request failed!');
    } catch (error) {
        console.log('Error:', error);
    }
}

// Function to get single product
const getSingleProduct = async (id) => {
    try {

// https://gensg-final-project.up.railway.app/api/products/${id}
// http://localhost:8080/api/products/${id}

        const response = await fetch(`https://gensg-final-project.up.railway.app/api/products/${id}`);

        if(response.ok) {
            const jsonResponse = await response.json();
            // console.log(`Product with id ${id}`, jsonResponse);
            return jsonResponse;
        }
        // throw new Error('Request failed!');
    } catch (error) {
        console.log('Error:', error);
    }
}

// Function to save product
const saveProduct = async (data) => {
    try {

// http://localhost:8080/api/products
// https://gensg-final-project.up.railway.app/api/products

        const response = await fetch('https://gensg-final-project.up.railway.app/api/products', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),            
        });

        if(response.ok) {
            const jsonResponse = await response.json();
            console.log('Success:', jsonResponse);
        }
        // throw new Error('Request failed!');
    } catch (error) {
        console.log('Error:', error);
    }
}


// Function to upload image
const uploadImage = async (formData) => {
    try {

// http://localhost:8080/api/image
// https://gensg-final-project.up.railway.app/api/image

        const response = await fetch('https://gensg-final-project.up.railway.app/api/image', {
            method: 'POST',
            body: formData           
        });

        if(response.ok) {
            return response.text();
        }
        // throw new Error('Request failed!');
    } catch (error) {
        console.log('Error:', error);
    }
}


//Function to get products based on search input
const searchProducts = async (string) => {
    try {

// https://gensg-final-project.up.railway.app/api/products/?name=${string}
// http://localhost:8080/api/products/?name=${string}

        const response = await fetch(`https://gensg-final-project.up.railway.app/api/products/?name=${string}`);

        if(response.ok) {
            const jsonResponse = await response.json();
            productsController.products = jsonResponse;
            productsController.displayCart(jsonResponse);
            productsController.storeDataToLocalStorage(jsonResponse);
            // productsController.loadDataFromLocalStorage();

            console.log('Search results:', productsController.products);
        }
        // throw new Error('Request failed!');


        if(response.status === 404){
            const jsonResponse = await response.json();
            console.log(jsonResponse)
            productsController.products = [];
            productsController.displayCart([]);
            productsController.storeDataToLocalStorage([]);
            // productsController.loadDataFromLocalStorage();
        }

    } catch (error) {
        console.log('Error:', error);
    }
}

// Function to sort products
const sortAllProductsByPrice = async (order) => {
    try {

// https://gensg-final-project.up.railway.app/api/products/sortby_price_${order}
// http://localhost:8080/api/products/sortby_price_${order}

        const response = await fetch(`https://gensg-final-project.up.railway.app/api/products/sortby_price_${order}`);

        if(response.ok) {
            const jsonResponse = await response.json();
            productsController.products = jsonResponse;
            productsController.displayCart(jsonResponse);
            productsController.storeDataToLocalStorage(jsonResponse);
            // productsController.loadDataFromLocalStorage();

            console.log('Success:', productsController.products);
        }
        // throw new Error('Request failed!');
    } catch (error) {
        console.log('Error:', error);
    }
}


// Function to create a new order
const createNewOrder = async (data) => {
    try {

//  https://gensg-final-project.up.railway.app/api/orders
//  http://localhost:8080/api/orders

        const response = await fetch('https://gensg-final-project.up.railway.app/api/orders', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),            
        });

        if(response.ok) {
            const jsonResponse = await response.json();
            // console.log('New order is created:', jsonResponse);
            return(jsonResponse.id)
        }
        // throw new Error('Request failed!');
    } catch (error) {
        console.log('Error:', error);
    }
}


// Function to create ordered products in the new order
const placeProductsInNewOrder = async (orderId, productId, productData) => {

    try {

// http://localhost:8080/api/orders/${orderId}/products/${productId}/order-details
// https://gensg-final-project.up.railway.app/api/orders/${orderId}/products/${productId}/order-details

        const response = await fetch(`https://gensg-final-project.up.railway.app/api/orders/${orderId}/products/${productId}/order-details`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(productData),            
        });

        if(response.ok) {
            const jsonResponse = await response.json();
            console.log('New order detail is created:', jsonResponse);
        }
        // throw new Error('Request failed!');
    } catch (error) {
        console.log('Error:', error);
    }
}