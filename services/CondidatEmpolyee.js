
const express = require('express')
const router = express.Router();
const pool =require('../config/DbConnection')

//GET
//get all condidatEmployees
router.get("/showAll", (req, res) => {
   pool.query("SELECT * FROM condidatEmployees ", (err, user_rows, fields) => {
        res.status(200)
        res.json(user_rows)
    })
})
//get One condidatEmployees
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





    
module.exports = router;