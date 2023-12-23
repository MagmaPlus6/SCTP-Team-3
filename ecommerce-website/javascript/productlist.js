const productsController = new Controller();
productsController.displayCart([]);

getAllProducts();

// ------------------------------------------------------------------------------------
// Invoke search product function
const newSearchProduct = document.querySelector('#searchProductsForm');

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

        searchProducts(input) 
    });
}

// ------------------------------------------------------------------------------------
// Invoke sort product function
const sortProducts = document.querySelector('#sortProducts');

sortProducts.addEventListener('change', function() {

    if(this.value === "Ascending Price") {
        sortAllProductsByPriceAscend();
        return;
    } else if (this.value === "Descending Price") {
        sortAllProductsByPriceDescend();
        return;       
    }

}, false);


// ------------------------------------------------------------------------------------
// Show/hide filter side bar

document.querySelector('.filter-sort-bar a').addEventListener('click', function (event) {

    const filterSideBar = document.querySelector('.filter-products')

    console.log(filterSideBar.style.display)

    if(window.innerWidth > 575 && filterSideBar) {

          filterSideBar.style.display === "" ?
            filterSideBar.style.display = "none" :
            filterSideBar.style.display = "";

    }



  

});