
// Function to get all products
const getAllProducts = async () => {
    try {
        const response = await fetch('http://localhost:8080/products');

        if(response.ok) {
            const jsonResponse = await response.json();
            productsController.products = jsonResponse;
            productsController.displayCart(jsonResponse);

            console.log('Success:', productsController.products);
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
const searchforProducts = async (string) => {
    try {
        const response = await fetch(`http://localhost:8080/products/?name=${string}&brand=${string}`);

        if(response.ok) {
            const jsonResponse = await response.json();
            productsController.products = jsonResponse;
            productsController.displayCart(jsonResponse);

            console.log('Search results:', productsController.products);
        }
        // throw new Error('Request failed!');


        if(response.status === 404){
            const jsonResponse = await response.json();
            console.log(jsonResponse)
            productsController.products = [];
            productsController.displayCart([]);
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

            console.log('Success:', productsController.products);
        }
        // throw new Error('Request failed!');
    } catch (error) {
        console.log('Error:', error);
    }
}