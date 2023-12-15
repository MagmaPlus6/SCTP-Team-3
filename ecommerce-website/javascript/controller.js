// creates a class that controls the listing of products
class Controller{

    // constructor of a class is typically used for initialisation
    // 'this' is a keyword, properties that belong to this class are declared here

    constructor(data = []){
        this.products = data !== null && data;  // class Controller's property: products
        // this.displayCart(data); // cannot work, will display empty product message even though it's not empty
    };

    // displayCart() method belongs to class Controller
    // it receives an array of Objects
    // an instance unorderedlist is instantiated to reference classname "product-list"

    // (A) in the event there are NO products
    // displays feedback that there are no products at the moment
    // and exit the function displayCart() prematurely - via return;

    // (B) in the event there are products
    // a for loop is used to iteratively populate class name "product-list"
    // with a list of items representing each product received from param 'data'

    displayCart(data){
        const showProductList = document.querySelector(".product-list");

        // (A)
        if(!data.length){
            let noProductList = document.createElement("div");
            noProductList.className = "no-products";
            let preProductList = document.querySelector(".pre-product-list")
            noProductList.style.textAlign = "center";
            noProductList.innerHTML = `<span>No product(s) found.</span>`
            preProductList.appendChild(noProductList);
            document.querySelector("nav .pagination").style.display = "none";
            return;
        } else {
            let noProductList = document.querySelector(".no-products");
            noProductList.parentNode.removeChild(noProductList);
            document.querySelector("nav .pagination").style.display = "";
        }

        // (B) 
        for (let index = 0; index < data.length; index++) {
            let subProductList = document.createElement("div");
            subProductList.className = "col pb-4";
            subProductList.innerHTML = `
                <a href="#/" class="text-decoration-none text-dark">
                    <div class="card h-100">
                        <img class="card-img-top card-img-fit" src="image/${data[index].image}">
                        <div class="card-body d-flex flex-column">
                            <p class="card-title text-muted">${data[index].brand}</p>
                            <h5 class="card-text">${data[index].name}</h5>
                            <p class="card-text mt-auto">$${data[index].price.toFixed(2)}</p>
                        </div>
                    </div>
                </a>
            `
            showProductList.appendChild(subProductList);
        }

        // Alternative: parseFloat(data[index].price).toFixed(2)
        // if don't put, 43.10 will be displayed as 43.1
    };

    // use the Fetch API to consume the save item endpoint
    // implement a new function called save that will POST the new item's data using the fetch function

    createNewProduct(name, brand, price, image){
        const data = { 
            name: name,
            brand: brand, 
            price: price, 
            image: image, 
        };

        // saveProduct(data);

        fetch('http://localhost:8080/products', {
        // Call the fetch function passing the url of the API AS a parameter
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
        })
        .then(response => response.json())
        .then(data => {
            // Codes for handling the data you get from the API
            console.log('Success:', data);
        })
        .catch((error) => {
            // Run code if the server returns any errors 
            console.error('Error:', error);
        });
    }

    // to edit
    update({name, brand, price, image}){
        const data = { name, brand, price, image };

        fetch('http://localhost:8080/products/{id}', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
        })
        .then(response => response.json())
        .then(data => {
        console.log('Success:', data);
        })
        .catch((error) => {
        console.error('Error:', error);
        });    
    }

    // to edit
    delete(id){
        fetch('http://localhost:8080/products/{id}', {
            method: 'DELETE'
        })
    }

    // to edit
    findById(id){
        fetch('http://localhost:8080/products/{id}', {
            method: 'GET'
        })    
    }

}



