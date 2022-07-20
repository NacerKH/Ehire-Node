const express = require('express')
const router = express.Router();
const pool = require('../config/DbConnection')
const bcrypt = require('bcrypt');
var path = require('path');
//Post 
// //SignIn
router.post("/Login", (req, res) => {
    pool.query("SELECT * FROM users where email = ? ",
        [req.body.email,
        ], (err, user_rows, fields) => {
            res.status(200)
            console.log(user_rows)
            res.json(user_rows[0])
        })
})
router.post('/auth',  (request, response) =>{
    // Capture the input fields
    let email = request.query.Email;
    let password = request.query.Password;


    // Ensure the input fields exists and are not empty
    if (email  &&  password ) {
        // Execute SQL query that'll select the account from the database based on the specified username and password
        pool.query('SELECT * FROM user WHERE email = ?', [email], function (error, results, fields) {
            // If there is an issue with the query, output the error
   
           let auth =bcrypt.compareSync( password,results[0].Password);
            if (error) throw error;
    
            if (!auth) return response.status(400).send('invalid password not matched !')
            if (auth) {
                response.status(200)
                console.log(results)

                response.json(results[0].Password)
                // response.json(auth)
                // If the account exists
                if (results.length > 0) {
                    // Authenticate the user
                    request.session.loggedin = true;
                    request.session.email = email;
                    // Redirect to home page
                    // response.redirect('/home');
                } else {
                    response.send('Incorrect Username and/or Password!');
                }
            }
            
            response.end();
        });
    } else {
        response.send('Please enter Username and Password!');
        response.end();
    }
});

//GET
//get all users
router.get("/showAll", (req, res) => {
    pool.query("SELECT * FROM user ", (err, user_rows, fields) => {
        res.status(200)
        res.json(user_rows)
    })
})
//get One users
router.get("/showOne", (req, res) => {
    pool.query("SELECT * FROM users  where id = ?", [
        req.query.id,
    ], (err, user_rows, fields) => {
        req.query.id
        res.status(200)
        res.json(user_rows)
    })
})
//--------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------



router.post("/add", (req, res) => {
 
    bcrypt.genSalt(10, function(err, salt) {
        bcrypt.hash( req.query.Password, salt, function(err, hash) {
        pool.query("INSERT INTO `user`(`UserName`,`Password`,`Email`,`UserType`,`ProfilePicture`,`Birthday`,`JobTitle`,`CreatedDate`,`UpdatedDate`) VALUES (?,?,?,?,?,?,?,?,?)", [
            req.query.UserName,
            hash,
            req.query.Email,
            req.query.UserType,
            req.query.ProfilePicture,
            req.query.Birthday,
            req.query.JobTitle,
            new Date(),
            new Date(),
        ], (err, rows, fields) => {
            console.log(err);
       
            res.status(200);
            res.json(rows);
        })
})
});
});

//POST
//Create users
router.post("/put", (req, res) => {

    pool.query("UPDATE `User` SET UserName = ?, Password = ? , Email = ?, UserType = ?, ProfilePicture = ?, Birthday = ?, JobTitle = ?, UpdatedDate = ? WHERE Id = ?", [

        req.query.UserName,
        cryptpassword(req.query.Password),
        req.query.Email,
        req.query.UserType,
        req.query.Birthday,
        req.query.JobTitle,
        new Date(),

    ], (err, rows, fields) => {
        console.log(err);
        res.status(200);
        res.json(rows);
    })
})
router.post('/upload-avatar', async (req, res) => {
    try {     
        console.log(req)
        if(!req.files) {
            res.send({
                status: false,
                message: 'No file uploaded'
            });
        } else {
            //Use the name of the input field (i.e. "avatar") to retrieve the uploaded file
            let avatar = req.files.ProfilePicture;
            
            //Use the mv() method to place the file in upload directory (i.e. "uploads")
            avatar.mv(path.join(__dirname, '../public/uploads/') + avatar.name);

            //send response
            res.send({
                status: true,
                message: 'File is uploaded',
                data: {
                    name: avatar.name,
                    mimetype: avatar.mimetype,
                    size: avatar.size
                }
            });
        }
    } catch (err) {
        res.status(500).send(err);
    }
});



module.exports = router;