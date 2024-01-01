// creates a class that controls the listing of products
class Controller{

    // constructor of a class is typically used for initialisation
    // 'this' is a keyword, properties that belong to this class are declared here

    constructor(data = []){
        this.products = data !== null && data;  // class Controller's property: products
        // this.displayCart(data); // cannot work, will display empty product message even though it's not empty
    };

    storeDataToLocalStorage(data){

        if(localStorage.getItem("productList")){
            localStorage.removeItem("productList")
        }

        const sampleItems = [];
        if(data.length > 0){
            for (let index = 0; index < data.length; index++) {
                sampleItems.push({
                    id: data[index].id,
                    name: data[index].name,
                    brand: data[index].brand,
                    price: data[index].price,
                    image: data[index].image,
                })
            }
        
        }
        localStorage.setItem("productList", JSON.stringify(sampleItems));  
    }

    loadDataFromLocalStorage() {
        const storageItems = localStorage.getItem("productList");
        if (storageItems) {
            const products = JSON.parse(storageItems);
            this.displayCart(products);
        }
    }

    displayCart(data){
        const showProductList = document.querySelector(".product-list");

        // Clear off existing products displayed on the page
        while (showProductList.hasChildNodes()) {
            showProductList.removeChild(showProductList.firstChild);
        }

        // (A)
        if(!data.length){
            let noProductList = document.createElement("div");
            noProductList.className = "no-products pb-2";
            noProductList.style.textAlign = "center";
            noProductList.innerHTML = `<span>No product(s) found.</span>`;
            let preProductList = document.querySelector(".pre-product-list")

            if(!document.querySelector(".no-products")) {
                preProductList.appendChild(noProductList);
                // document.querySelector("nav .pagination").style.display = "none";   
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
                // document.querySelector("nav .pagination").style.display = "";
            }
        }

        // (B) 
        for (let index = 0; index < data.length; index++) {
            let subProductList = document.createElement("div");
            subProductList.className = "bg-white";
            subProductList.innerHTML = `
                <a href="product-id-${data[index].id}.html" class="text-decoration-none text-dark" target="product${data[index].id}page">
                        <img class="card-img-top card-img-fit" src="image/${data[index].image}">
                        <div class="px-3 pt-3">
                            <p class="text-muted">${data[index].brand}</p>
                            <h5 class="">${data[index].name}</h5>
                            <p class="">$${data[index].price.toFixed(2)}</p>
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
    }

}

const productsController = new Controller();
productsController.displayCart([]);
