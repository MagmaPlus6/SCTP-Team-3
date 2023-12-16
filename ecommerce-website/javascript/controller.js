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

        // Clear off existing products displayed on the page
        
        while (showProductList.hasChildNodes()) {
            showProductList.removeChild(showProductList.firstChild);
        }

        // (A)
        if(!data.length){
            let noProductList = document.createElement("div");
            noProductList.className = "no-products";
            noProductList.style.textAlign = "center";
            noProductList.innerHTML = `<span>No product(s) found.</span>`;
            let preProductList = document.querySelector(".pre-product-list")

            if(!document.querySelector(".no-products")) {
                preProductList.appendChild(noProductList);
                document.querySelector("nav .pagination").style.display = "none";   
            } 
            
            document.querySelector(".filter-sort-bar").style.display = "none"; 
            document.querySelector(".filter-products").style.display = "none"; 

            return;
        } else {

            document.querySelector(".filter-sort-bar").style.display = ""; 
            document.querySelector(".filter-products").style.display = ""; 

            let noProductList = document.querySelector(".no-products");

            if(noProductList) {
                noProductList.parentNode.removeChild(noProductList);
                document.querySelector("nav .pagination").style.display = "";
            }
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

        const numberOfProducts = document.querySelector(".number-of-products");
        numberOfProducts.innerHTML = data.length + ' product(s) found';

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

        saveProduct(data);

        // fetch('http://localhost:8080/products', {
        // // Call the fetch function passing the url of the API AS a parameter
        // method: 'POST',
        // headers: {
        //     'Content-Type': 'application/json',
        // },
        // body: JSON.stringify(data),
        // })
        // .then(response => response.json())
        // .then(data => {
        //     // Codes for handling the data you get from the API
        //     console.log('Success:', data);
        // })
        // .catch((error) => {
        //     // Run code if the server returns any errors 
        //     console.error('Error:', error);
        // });
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

// ------------------------------------------------------------------------------------

const productsController = new Controller();
productsController.displayCart([]);

getAllProducts();

// ------------------------------------------------------------------------------------
// Invoke searh product function

// Select the New Item Form
const newSearchProduct = document.querySelector('#searchProductsForm');

// To fix the "cannot read property 'addEventListener' of null" error, check that the element is not null before calling the addEventListener() method on it.
if(newSearchProduct) {

    // Add an 'onsubmit' event listener
    newSearchProduct.addEventListener('submit', (event) => {

        // Prevent default action
        event.preventDefault();
    
        // Select the inputs
        const newSearchInput = document.querySelector('#searchInput');
    
        // Get the values of the inputs
        const input = newSearchInput.value.trim();

        console.log('search input:' + input)

        // console.log(productsController.products)
        // productsController.displayCart(productsController.products);

        searchforProducts(input)

        // think of how to reset entire field after submit
        newSearchProduct.reset();   
    });
}

// ------------------------------------------------------------------------------------
// Invoke sort product function

const sortProduct = document.querySelector('#sortProducts');

sortProduct.addEventListener('change', function() {

    if(this.value === "Ascending Price") {
        sortAllProductsByPriceAscend();
        return;
    } else if (this.value === "Descending Price") {
        sortAllProductsByPriceDescend();
        return;       
    }


}, false);





