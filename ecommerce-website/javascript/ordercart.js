
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


// Select product size
// $('.btn-group a').on('click', function(){
//     var sel = $(this).data('title');
//     var tog = $(this).data('toggle');
//     $('#'+tog).prop('value', sel);
    
//     $('a[data-toggle="'+tog+'"]').not('[data-title="'+sel+'"]').removeClass('active').addClass('notActive');
//     $('a[data-toggle="'+tog+'"][data-title="'+sel+'"]').removeClass('notActive').addClass('active');
// })

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
});
  

// Change product quantity
// $(document).on('click', '.number-spinner button', function () {    
// 	var btn = $(this),
// 		oldValue = btn.closest('.number-spinner').find('input').val().trim(),
// 		newVal = 0;

// 	const originalPrice = btn.parents('.col-lg-8.col-md.mb-4').find('.price').attr('data')

// 	if (btn.attr('data-dir') == 'up') {
// 		newVal = parseInt(oldValue) + 1;
// 	} else {
// 		if (oldValue > 1) {
// 			newVal = parseInt(oldValue) - 1;
// 		} else {
// 			newVal = 1;
// 		}
// 	}
// 	btn.closest('.number-spinner').find('input').val(newVal);

// 	btn.parents('.col-lg-8.col-md.mb-4').find('.price').text(parseFloat(originalPrice*newVal).toFixed(2))

// 	shoppingCartSummary()
// });

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

// Delete button
document.querySelectorAll("button[title='Remove item']").forEach(function (element) {
	element.addEventListener('click', function () {

		const productToRemove = element.closest('.row.border-bottom.m-1.py-2.align-items-center')

		productToRemove.remove();

		shoppingCartSummary();
	});
});
  