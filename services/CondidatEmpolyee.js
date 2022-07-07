const express = require('express')
const mysql = require('mysql')
const router = express.Router()

const pool = mysql.createPool({
    connectionLimit: 10,
    host: "localhost",
    user: "root",
    database: "ehire"
})

function getConnection(){
    return pool
}



//--------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------


//GET
//get all condidatEmployees
router.get("/showAll", (req, res) => {
    pool.query("SELECT * FROM condidatEmployees ", (err, user_rows, fields) => {
        res.status(200)
        res.json(user_rows)
    })
})
router.get("/showOne", (req, res) => {
    pool.query("SELECT * FROM condidatEmployees  where id = ?", [
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
//Create condidatEmployees
router.post("/add", (req, res) => {
        console.log(req.query.offer_id);
    pool.query("INSERT INTO `condidatEmployees`(`offer_id`,`user_id`, `cv_url`,`status`,`CreatedDate`,`UpdatedDate`) VALUES (?,?,?,?,?,?)", [
        
        req.query.offer_id,
        req.query.user_id,
        req.query.cv_url,
        req.query.status,
        new Date(),
        new Date(),
        
    

     ], (err, rows, fields) => {
            console.log(err);
            res.status(200);
            res.json(rows);
        })
})

// //Post 
// //SignIn
// router.post("/Login", (req, res) => {
//     pool.query("SELECT * FROM personne where mail = ? and password = ? ",
//     [   req.body.email,
//         req.body.password], (err, user_rows, fields) => {
//         res.status(200)
//         console.log(user_rows)
//         res.json(user_rows[0])
//     })
// })



    
module.exports = router;