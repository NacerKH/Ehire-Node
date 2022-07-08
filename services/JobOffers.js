const express = require('express')
const router = express.Router();
const pool =require('../config/DbConnection')
//GET
//get all JobOffer
router.get("/showAll", (req, res) => {
    pool.query("SELECT * FROM joboffer ", (err, user_rows, fields) => {
         res.status(200)
         res.json(user_rows)
     })
 })
 //get One JobOffer
 router.get("/showOne", (req, res) => {
     pool.query("SELECT * FROM joboffer  where id = ?", [
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
 //Create JobOffer
 router.post("/add", (req, res) => {
  
     pool.query("INSERT INTO `joboffer`(`jobDescription`,`AverageSallary`,`totalPlaces`,`Status`,`category`,`idTest`) VALUES (?,?,?,?,?,?)", [
         
         req.query.jobDescription,
         req.query.AverageSallary,
         req.query.totalPlaces,
         req.query.Status,
         req.query.category,
         req.query.idTest,
 ], (err, rows, fields) => {
             console.log(err);
             res.status(200);
             res.json(rows);
         })
 })
 
 
 
 
 
     
 module.exports = router;