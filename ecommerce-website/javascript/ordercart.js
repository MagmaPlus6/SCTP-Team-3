
const selectProductSizeinCart = () => {
	document.querySelectorAll('.btn-group a').forEach(function (element) {
		element.addEventListener('click', function () {
		var sel = this.getAttribute('data-title');
		var tog = this.getAttribute('data-toggle');
		document.getElementById(tog).value = sel;
	
		document.querySelectorAll('a[data-toggle="'+tog+'"]:not([data-title="'+sel+'"])').forEach(function (el) {
			el.classList.remove('active');
			el.classList.add('notActive');
		});
	
		this.classList.remove('notActive');
		this.classList.add('active');
		});
	})
}


const removeProductInCart = () => {
	document.querySelectorAll("button[title='Remove item']").forEach(function (element) {
		element.addEventListener('click', function () {

			const productToRemove = element.closest('.row.border-bottom.m-1.py-2.align-items-center')

			const orderListStorage = localStorage.getItem("orderListStorage");
			const parsedOrderListStorage = JSON.parse(orderListStorage);

			const keyIDToRemove = this.getAttribute('data')

			const filteredOrderList = parsedOrderListStorage.filter(product => product.keyID !== parseInt(keyIDToRemove))

			if (filteredOrderList.length) {
				localStorage.setItem("orderListStorage", JSON.stringify(filteredOrderList));
			} else {
				localStorage.removeItem("orderListStorage")
			}

			productToRemove.remove();

			shoppingCartSummary();

		});
	})
}


const addProductToShoppingCart = async () => {

	const orderListStorage = localStorage.getItem("orderListStorage");
	const parsedOrderListStorage = JSON.parse(orderListStorage);

    const orderList = document.querySelector('.order-list')

	if(orderList) {
	
	if(parsedOrderListStorage) {
		
	for (let i = 0; i < parsedOrderListStorage.length; i++) {

		const data = await getSingleProduct(parsedOrderListStorage[i].productID);

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
				<label for="size${i}" class="col-lg-3 col-md-5 col-sm-3 col-form-label">Size</label>
				<div class="col-lg col-md-8 col-sm">
					<div class="input-group">
						<div id="radioBtn${i}" class="btn-group">
							<a class="btn btn-primary btn-sm active" data-toggle="size${i}" data-title="XS">XS</a>
							<a class="btn btn-primary btn-sm notActive" data-toggle="size${i}" data-title="S">S</a>
							<a class="btn btn-primary btn-sm notActive" data-toggle="size${i}" data-title="M">M</a>
							<a class="btn btn-primary btn-sm notActive" data-toggle="size${i}" data-title="L">L</a>
							<a class="btn btn-primary btn-sm notActive" data-toggle="size${i}" data-title="XL">XL</a> 
						</div>
						<input type="hidden" name="size${i}" id="size${i}">
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
					<p><strong>$<span class="price" data="${data.price}">${data.price}</span>
					</strong></p>
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
				data="${parsedOrderListStorage[i].keyID}"
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
	selectProductSizeinCart()
	removeProductInCart();
	shoppingCartSummary();
	}
}

addProductToShoppingCart()


// Update in Shopping cart summary section
const shoppingCartSummary = () => {

    let pricesOfProduct = document.querySelectorAll('.price')

    let sum = 0;

    pricesOfProduct.forEach(i => {
        sum += parseFloat(i.innerText);
    });

    const subtotal = document.querySelector('.subtotal')
    subtotal.innerHTML = sum.toFixed(2)

    const shippingFee = document.querySelector('.shipping-fee').innerHTML

    const total = document.querySelector('.total')
    total.innerHTML = (parseFloat(subtotal.innerHTML) + parseFloat(shippingFee)).toFixed(2)
}


// Change product quantity in shopping cart
document.addEventListener('click', function (event) {
	var target = event.target;
  
	if (target.classList.contains('number-spinner') || target.closest('.number-spinner button')) {
	  var btn = target.closest('.number-spinner button');
	  var oldValue = btn.closest('.number-spinner').querySelector('input').value.trim();
	  var newVal = 0;
  
	  var originalPrice = btn.closest('.col-lg-8.col-md.mb-4').querySelector('.price').getAttribute('data');
  
	  if (btn.getAttribute('data-dir') == 'up') {
		newVal = parseInt(oldValue) + 1;
	  } else {
		if (oldValue > 1) {
		  newVal = parseInt(oldValue) - 1;
		} else {
		  newVal = 1;
		}
	  }
  
	  btn.closest('.number-spinner').querySelector('input').value = newVal;
  
	  btn.closest('.col-lg-8.col-md.mb-4').querySelector('.price').textContent = (parseFloat(originalPrice) * newVal).toFixed(2);
  
	  shoppingCartSummary();
	}
});


const getDeliveryDates = (numberofDaysFromToday) => {

	let targetDate = new Date();
	targetDate.setDate(targetDate.getDate() + numberofDaysFromToday);
	
	let dd = targetDate.getDate();
	let mm = targetDate.getMonth() + 1; // 0 is January, so we must add 1
	let yyyy = targetDate.getFullYear();
	
	let dateString = dd + "." + mm + "." + yyyy;
	
	return dateString
}

const deliveryDates = document.querySelector('.delivery-dates');
deliveryDates.innerHTML = getDeliveryDates(2) + ' - ' + getDeliveryDates(5)
