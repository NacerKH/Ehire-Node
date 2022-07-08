const express = require('express')
const router = express.Router();
const pool =require('../config/DbConnection')

//GET
//get all Conge
router.get("/showAll", (req, res) => {
    pool.query("SELECT * FROM conge ", (err, user_rows, fields) => {
         res.status(200)
         res.json(user_rows)
     })
 })
 //get One conge
 router.get("/showOne", (req, res) => {
     pool.query("SELECT * FROM conge  where id = ?", [
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
 //Create conge
 router.post("/add", (req, res) => {
         console.log(req.query.offer_id);
     pool.query("INSERT INTO `conge`(`Type`,`StartDate`, `EndDate`,`Description`,`Status`,`createdAt`,`updateAt`) VALUES (?,?,?,?,?,?,?)", [
         
         req.query.Type,
         req.query.StartDate,
         req.query.EndDate,
         req.query.Description,
         req.query.Status,
         new Date(),
         new Date(),
 
     
         
     
 
      ], (err, rows, fields) => {
             console.log(err);
             res.status(200);
             res.json(rows);
         })
 })
 
 
 
 
 
     
 module.exports = router;