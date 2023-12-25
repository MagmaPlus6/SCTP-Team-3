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

// document.querySelector('.filter-sort-bar a').addEventListener('click', function (event) {

//     const filterSideBar = document.querySelector('.filter-products')

//     console.log(filterSideBar.style.display)

//     if(window.innerWidth > 575 && filterSideBar) {
//           filterSideBar.style.display === "" ?
//             filterSideBar.style.display = "none" :
//             filterSideBar.style.display = "";
//     } 

// });

var bsOverlay = $('.bs-canvas-overlay');
$('[data-toggle="canvas"]').on('click', function(){

    if(window.innerWidth < 576){
    
    var ctrl = $(this), 
        elm = ctrl.is('a') ? ctrl.data('target') : ctrl.attr('href');
    $(elm).addClass('ml-0 px-3');
    $(elm).removeClass('col-3');
    $(elm + ' .bs-canvas-close').attr('aria-expanded', "true");
    $('[data-target="' + elm + '"], a[href="' + elm + '"]').attr('aria-expanded', "true");
    if(bsOverlay.length)
        bsOverlay.addClass('show');
    return false;
    }
});

$('.bs-canvas-close, .bs-canvas-overlay').on('click', function(){
    var elm;
    if($(this).hasClass('bs-canvas-close')) {
        elm = $(this).closest('.bs-canvas');
        $('[data-target="' + elm + '"], a[href="' + elm + '"]').attr('aria-expanded', "false");
    } else {
        elm = $('.bs-canvas')
        $('[data-toggle="canvas"]').attr('aria-expanded', "false");	
    }
    elm.removeClass('ml-0 px-3');
    elm.addClass('col-3');
    $('.bs-canvas-close', elm).attr('aria-expanded', "false");
    if(bsOverlay.length)
        bsOverlay.removeClass('show');
    return false;
});

  
document.querySelector('button.uncheckAll').addEventListener('click', function (){

    document.querySelectorAll('input[type="checkbox"]')
      .forEach(el => el.checked = false);

})

