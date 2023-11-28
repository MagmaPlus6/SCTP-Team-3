
// ----------------------- Get all products from MySQL database -----------------------------

const productsController = new Controller();

// Call the fetch function passing the url of the API AS a parameter

fetch('http://localhost:8080/products')
.then(response => response.json())
.then(data => {
    // Codes for handling the data you get from the API
    productsController.products = data;
    productsController.displayCart(data);

    console.log('Success:', data);
})
.catch((error) => {
    // Run code if the server returns any errors 
    console.error('Error:', error);
});

// Cannot work cuz need additional Library to run asyn operations 
// const getAllProducts = async () => {
//     try {
//         const response = await fetch('http://localhost:8080/products');

//         if(response.ok) {
//             const jsonResponse = await response.json();
//             const productsController = new Controller(jsonResponse.length, jsonResponse);
//             productsController.loadDataFromLocalStorage();

//             console.log('Success:', jsonResponse);
//         }
//         throw new Error('Request failed!');
//     } catch (error) {
//         console.log(error);
//     }
// }

// getAllProducts();
