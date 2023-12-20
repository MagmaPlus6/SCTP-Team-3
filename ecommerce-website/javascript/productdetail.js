
async function productDetailPageData(id) {

	const productName = document.querySelector(`.productName-${id}`);
	const productImage = document.querySelector(`.productImage-${id}`);
	const productPrice = document.querySelector(`.productPrice-${id}`);
	const productBrand = document.querySelector(`.productBrand-${id}`);

	const data = await getSingleProduct(id);

	productName.innerHTML = data.name;
	productImage.src = `image/${data.image}`;
	productPrice.innerHTML = data.price;
	productBrand.innerHTML = data.brand
}

productDetailPageData(1)
// productDetailPageData(2)



const orderForm = document.querySelector('form');

orderForm.addEventListener('submit', async (event) => {
	const id = event.target.id;
	const data = await getSingleProduct(id);

	console.log('id: ' + id)

    addProductToShoppingCart(data);

	// const orderList = document.querySelector('.order-list')

	// console.log('orderList: ' + orderList)

	// if(orderList) {

	// const subOrderList = document.createElement("div");
	// subOrderList.className = "row border-bottom m-1 py-2 align-items-center";

	// subOrderList.innerHTML = `

	// 	<div class="col-lg-4 col-md mb-4 mb-lg-0 text-center">
	// 		<img
	// 			src="image/${data.image}"
	// 			class="img-fluid shopping-cart-img"
	// 			alt=""
	// 		/>
	// 	</div>

	// 	<div class="col-lg-8 col-md mb-4 mb-lg-0">
	// 		<p><strong>${data.name}</strong></p>

	// 		<div class="form-group row">
	// 			<label for="size" class="col-lg-3 col-md-5 col-sm-3 col-form-label">Size</label>
	// 			<div class="col-lg col-md-8 col-sm">
	// 				<div class="input-group">
	// 					<div id="radioBtn${data.id}" class="btn-group">
	// 						<a class="btn btn-primary btn-sm active" data-toggle="size${data.id}" data-title="XS">XS</a>
	// 						<a class="btn btn-primary btn-sm notActive" data-toggle="size${data.id}" data-title="S">S</a>
	// 						<a class="btn btn-primary btn-sm notActive" data-toggle="size${data.id}" data-title="M">M</a>
	// 						<a class="btn btn-primary btn-sm notActive" data-toggle="size${data.id}" data-title="L">L</a>
	// 						<a class="btn btn-primary btn-sm notActive" data-toggle="size${data.id}" data-title="XL">XL</a> 
	// 					</div>
	// 					<input type="hidden" name="size${data.id}" id="size${data.id}">
	// 				</div>
	// 			</div>
	// 		</div>

	// 		<div class="form-group row">
	// 			<label for="quantity" class="col-lg-3 col-md-6 col-sm-3 col-form-label">Quantity</label>

	// 			<div class="col-lg-5 col-md-8 col-sm-4">
	// 				<div class="input-group number-spinner">
	// 					<span class="input-group-btn btn-light">
	// 						<button class="btn btn-default" data-dir="dwn">
	// 							<span class="fas fa-minus"></span>
	// 						</button>
	// 					</span>
	// 					<input type="text" class="form-control text-center quantity-input" value="1">
	// 					<span class="input-group-btn btn-light">
	// 						<button class="btn btn-default" data-dir="up">
	// 							<span class="fas fa-plus"></span>
	// 						</button>
	// 					</span>
	// 				</div>
	// 			</div>
	// 		</div>

	// 		<div class="row">
	// 			<div class="col-lg-3 col-md-5 col-sm-3">
	// 				<p>Price</p>
	// 			</div>
	// 			<div class="col-md-8 col-sm">
	// 				<p class="">
	// 					<strong>$${data.price}</strong>
	// 				</p>
	// 			</div>   
	// 		</div>

	// 		<button
	// 			type="button"
	// 			class="btn btn-danger btn-sm mb-2"
	// 			data-mdb-toggle="tooltip"
	// 			title="Move to the wish list"
	// 		>
	// 			<i class="fas fa-heart"></i>
	// 		</button>
	// 		<button
	// 			type="button"
	// 			class="btn btn-secondary btn-sm mb-2 float-right"
	// 			data-mdb-toggle="tooltip"
	// 			title="Remove item"
	// 		>
	// 			<i class="fas fa-trash"></i>
	// 		</button>
	// 	</div>
	// `

	// orderList.appendChild(subOrderList);

	// }

})

