
// Function to get all products
const getAllProducts = async () => {
    try {
        const response = await fetch('http://localhost:8080/products');

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
        const response = await fetch(`http://localhost:8080/products/${id}`);

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
        const response = await fetch('http://localhost:8080/products', {
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

//Function to get products based on search input
const searchProducts = async (string) => {
    try {
        const response = await fetch(`http://localhost:8080/products/?name=${string}`);

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

// Function to sort products in ascending price
const sortAllProductsByPriceAscend = async () => {
    try {
        const response = await fetch('http://localhost:8080/products/sortby_price_asc');

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

// Function to sort products in descending price
const sortAllProductsByPriceDescend = async () => {
    try {
        const response = await fetch('http://localhost:8080/products/sortby_price_desc');

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
        const response = await fetch('http://localhost:8080/orders', {
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
        const response = await fetch(`http://localhost:8080/orders/${orderId}/products/${productId}/order-details`, {
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