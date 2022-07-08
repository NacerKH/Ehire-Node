const express = require('express')
const router = express.Router();
const pool =require('../config/DbConnection')
const bcrypt = require('bcrypt');

//Post 
// //SignIn
router.post("/Login", (req, res) => {
    pool.query("SELECT * FROM users where email = ? ",
    [   req.body.email,
    ], (err, user_rows, fields) => {
        res.status(200)
        console.log(user_rows)
        res.json(user_rows[0])
    })
})
router.post('/auth', function(request, response) {
	// Capture the input fields
	let email = request.body.email;
	let password = request.body.password;
	// Ensure the input fields exists and are not empty
	if (username && password) {
		// Execute SQL query that'll select the account from the database based on the specified username and password
		pool.query('SELECT * FROM users WHERE email = ?', [email], function(error, results, fields) {
			// If there is an issue with the query, output the error
			if (error) throw error;
            const auth=bycrypt.compare(results.Password,password);
            if (auth){
                response.status(200)
                console.log(user_rows)
                response.json(results[0])
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
    pool.query("SELECT * FROM users ", (err, user_rows, fields) => {
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
 
 //POST
 //Create users
 router.post("/add", (req, res) => {
    const salt = await bcrypt.genSalt();
     pool.query("INSERT INTO `users`(`UserName`,`Password`,`Email`,`UserType`,`ProfilePicture`,`Birthday`,`JobTitle`,`CreatedDate`,`UpdatedDate`) VALUES (?,?,?,?,?,?,?,?,?)", [
         
         req.query.UserName,
         bcrypt.hash(req.query.Password,salt),
         req.query.Email,
         req.query.UserType,
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
 
  //POST
 //Create users
 router.post("/put", (req, res) => {
 
    pool.query("UPDATE `Users` SET UserName = ?, Password = ? , Email = ?, UserType = ?, ProfilePicture = ?, Birthday = ?, JobTitle = ?, UpdatedDate = ? WHERE Id = ?", [
        
        req.query.UserName,
        req.query.Password,
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
 
 
 
 
     
 module.exports = router;