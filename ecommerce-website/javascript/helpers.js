
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

// Add product to shopping cart 
// NOT WORKING

const addProductToShoppingCart = (data) => {

    const orderList = document.querySelector('.order-list')

	console.log('orderList: ' + orderList)

	if(orderList) {

	const subOrderList = document.createElement("div");
	subOrderList.className = "row border-bottom m-1 py-2 align-items-center";

	subOrderList.innerHTML = `

		<div class="col-lg-4 col-md mb-4 mb-lg-0 text-center">
			<img
				src="image/${data.image}"
				class="img-fluid shopping-cart-img"
				alt=""
			/>
		</div>

		<div class="col-lg-8 col-md mb-4 mb-lg-0">
			<p><strong>${data.name}</strong></p>

			<div class="form-group row">
				<label for="size" class="col-lg-3 col-md-5 col-sm-3 col-form-label">Size</label>
				<div class="col-lg col-md-8 col-sm">
					<div class="input-group">
						<div id="radioBtn${data.id}" class="btn-group">
							<a class="btn btn-primary btn-sm active" data-toggle="size${data.id}" data-title="XS">XS</a>
							<a class="btn btn-primary btn-sm notActive" data-toggle="size${data.id}" data-title="S">S</a>
							<a class="btn btn-primary btn-sm notActive" data-toggle="size${data.id}" data-title="M">M</a>
							<a class="btn btn-primary btn-sm notActive" data-toggle="size${data.id}" data-title="L">L</a>
							<a class="btn btn-primary btn-sm notActive" data-toggle="size${data.id}" data-title="XL">XL</a> 
						</div>
						<input type="hidden" name="size${data.id}" id="size${data.id}">
					</div>
				</div>
			</div>

			<div class="form-group row">
				<label for="quantity" class="col-lg-3 col-md-6 col-sm-3 col-form-label">Quantity</label>

				<div class="col-lg-5 col-md-8 col-sm-4">
					<div class="input-group number-spinner">
						<span class="input-group-btn btn-light">
							<button class="btn btn-default" data-dir="dwn">
								<span class="fas fa-minus"></span>
							</button>
						</span>
						<input type="text" class="form-control text-center quantity-input" value="1">
						<span class="input-group-btn btn-light">
							<button class="btn btn-default" data-dir="up">
								<span class="fas fa-plus"></span>
							</button>
						</span>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-3 col-md-5 col-sm-3">
					<p>Price</p>
				</div>
				<div class="col-md-8 col-sm">
					<p class="">
						<strong>$${data.price}</strong>
					</p>
				</div>   
			</div>

			<button
				type="button"
				class="btn btn-danger btn-sm mb-2"
				data-mdb-toggle="tooltip"
				title="Move to the wish list"
			>
				<i class="fas fa-heart"></i>
			</button>
			<button
				type="button"
				class="btn btn-secondary btn-sm mb-2 float-right"
				data-mdb-toggle="tooltip"
				title="Remove item"
			>
				<i class="fas fa-trash"></i>
			</button>
		</div>
	`

	orderList.appendChild(subOrderList);

	}

}
