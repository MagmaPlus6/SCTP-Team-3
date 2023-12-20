// import express from '/express';
// import multer from '/multer';
// var app = express()
// var port = 5502;

// var storage = diskStorage({
//   destination: function (req, file, cb) {
//     cb(null, './image')
//   },
//   filename: function (req, file, cb) {
//     cb(null, file.originalname)
//   }
// })
// var upload = multer({ storage: storage })

// app.use((__dirname));
// app.use('/image', ('image'));

// app.post('/create-new-product', upload.single('productImage'), function (req, res, next) {
//   // req.file is the `productImage` file
//   // req.body will hold the text fields, if there were any
//   console.log(JSON.stringify(req.file))
//   var response = '<a href="/">Home</a><br>'
//   response += "Files uploaded successfully.<br>"
//   response += `<img src="${req.file.path}" /><br>`
//   return res.send(response)
// })

// app.listen(port,() => console.log(`Server running on port ${port}!`))


// let file = document.getElementById("productImage").files[0];

// let fd = new FormData();
// fd.append("file", file);

// import fs from '../fs';
// import Multer from '../multer';
// import path from '../path';
// import config from './../../config';  //configuration file to get project root path
// //name of the input type (avatar in our case).
// const FILENAME = 'avatar';

// const uploadDir = config.get('path_project') + '/' +'uploads/';
// if (!fs.existsSync(uploadDir)) {
//     fs.mkdirSync(uploadDir);
// }

// /**
// *	multer setting for photo upload storage and imagename setting, also
// *	set the file details in request object
// */
// let photoStorage = Multer.diskStorage({
//     destination: function (req, file, cb) {
//        cb(null, uploadDir)
//    },
//    filename: function (req, file, cb) {
//         cb(null, "Photo" + '_' + Date.now() + path.extname(file.originalname));
//    }
// })

// /**
// *	Function to set storage for single upload, named as FILENAME
// */
// let singleFileUpload = () => {
//     return Multer({
//         storage : photoStorage
//     }).single(FILENAME);
// }

// function controller(req, res) {
//     /**
//     *   You will get image details in req.file (path where image is saved and name of the image), and if you send any other data you will get it in req.body.
//     */
//         localPath = req.file.destination;
//         filename = req.file.filename;
//     }


import express from 'express';
const app = express();
const port = 3000;
app.get('/', (req, res) => {
    res.send('hello people');
});
app.listen(port, () => {
    console.log('listening to the port: ' + port);
});