const express = require('express')
const router = express.Router();
const pool =require('../config/DbConnection')

//Post 
// //SignIn
router.post("/Login", (req, res) => {
    pool.query("SELECT * FROM personne where mail = ? and password = ? ",
    [   req.body.email,
        req.body.password], (err, user_rows, fields) => {
        res.status(200)
        console.log(user_rows)
        res.json(user_rows[0])
    })
})