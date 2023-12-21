
async function productDetailPageData(id) {

	const productName = document.querySelector(`.productName-${id}`);
	const productImage = document.querySelector(`.productImage-${id}`);
	const productPrice = document.querySelector(`.productPrice-${id}`);
	const productBrand = document.querySelector(`.productBrand-${id}`);

	const data = await getSingleProduct(id);

	productName.innerHTML = data.name;
	productImage.src = `image/${data.image}`;
	productPrice.innerHTML = `$${data.price}`;
	productBrand.innerHTML = data.brand
}

productDetailPageData(1)
productDetailPageData(2)

const orderForm = document.querySelector('form.order-product');

orderForm.addEventListener('submit', async (event) => {

	const id = event.target.id;

	if(!localStorage.getItem("orderListStorage")){
		const orderListStorage = [];
		orderListStorage.push({
			keyID: Date.now(),
			productID: parseInt(id)
		})
		localStorage.setItem("orderListStorage", JSON.stringify(orderListStorage));
	} else {
		const orderListStorage = localStorage.getItem("orderListStorage");
		const parsedOrderListStorage = JSON.parse(orderListStorage);
		parsedOrderListStorage.push({
			keyID: Date.now(),
			productID: parseInt(id)
		})
		localStorage.setItem("orderListStorage", JSON.stringify(parsedOrderListStorage));
	}

})

