const express = require('express')
const router = express.Router();
const pool =require('../config/DbConnection')
//GET
//get all category
router.get("/showAll", (req, res) => {
    pool.query("SELECT * FROM category ", (err, user_rows, fields) => {
         res.status(200)
         res.json(user_rows)
     })
 })
 //get One category
 router.get("/showOne", (req, res) => {
     pool.query("SELECT * FROM category  where id = ?", [
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
 //Create category
 router.post("/add", (req, res) => {
    
     pool.query("INSERT INTO `category`(`id_cat`,`name_cat`) VALUES (?,?)", [
         
         req.query.id_cat,
         req.query.name_cat,
      
         
     
 
      ], (err, rows, fields) => {
             console.log(err);
             res.status(200);
             res.json(rows);
         })
 })
 
 
 
 
 
     
 module.exports = router;