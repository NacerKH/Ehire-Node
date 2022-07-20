const express = require('express')
const router = express.Router();
const pool =require('../config/DbConnection')

//GET
//get all intereviewdate
router.get("/showAll", (req, res) => {
    pool.query("SELECT * FROM intereviewdate ", (err, user_rows, fields) => {
         res.status(200)
         res.json(user_rows)
     })
 })
 //get One intereviewdate
 router.get("/showOne", (req, res) => {
     pool.query("SELECT * FROM intereviewdate  where id = ?", [
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
 //Create intereviewdate
 router.post("/add", (req, res) => {
       
     pool.query("INSERT INTO `intereviewdate`(`intrvDate`,`condidat_id`,`CreatedDate`,`UpdatedDate`) VALUES (?,?,?,?)", [
         
         req.query.intrvDate,
         req.query.condidat_id,
            new Date(),
            new Date(),
 
     
         
     
 
      ], (err, rows, fields) => {
             console.log(err);
             res.status(200);
             res.json(rows);
         })
 })
 
 
 
 
 
     
 module.exports = router;