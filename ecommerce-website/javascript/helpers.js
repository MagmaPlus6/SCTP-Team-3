// save temporary data to local storage
//  ----------- area to invoke the functions ----------- 

// temporary data
var data = [];

data = [

    { 
        // id: 1, 
        name: "3-stripes Tracksuit",
        brand: "Adidas",
        price: 43.10.toFixed(2), // without toFixed(2) , 43.1 will be displayed
        image: "image/women-tracksuit.jpg", 
    },
    {
        // id: 2, 
        name: "Pocket Skirt",
        brand: "Urban Coco",
        price: 57.88,
        image: "image/women-skirt.jpg", 
    },
    { 
        // id: 3, 
        name: "Long Sleeve T Shirt",
        brand: "Love, Bonito.",
        price: 8.99,
        image: "image/women-long-sleeve.jpg", 
    },
    { 
        // id: 4, 
        name: "Pull Up Pants",
        brand: "Tommy Hilfiger",
        price: 12.17,
        image: "image/women-pants.jpg", 
    },
    { 
        // id: 5, 
        name: "Button Down Blouse",
        brand: "Beyond the Vines",
        price: 10.83,
        image: "image/women-blouse.jpg", 
    },
    { 
        // id: 6, 
        name: "Jogging Gym Pants",
        brand: "Curious Creatures",
        price: 16.01,
        image: "image/women-gym-pants.jpg", 
    },

]; 

/*
    {
        // id: 7, 
        name: "Fitness T Shirt",
        brand: "In Good Company",
        price: 29.63,
        image: "image/women-fitness-shirt.jpg", 
    },
    { 
        // id: 8, 
        name: "Gym Training Top",
        brand: "Just Cool",
        price: 10.48,
        image: "image/women-gym-top.jpg", 
    },
*/


// Initialize a new TaskManager with currentId set based on the length of 'data' (number of elements in data)
const productsController = new Controller(data.length, data);
productsController.loadDataFromLocalStorage();