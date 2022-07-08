const express = require('express')
const router = express.Router();
const pool =require('../config/DbConnection')

//GET
//get all condidatEmployees
router.get("/showAll", (req, res) => {
    pool.query("SELECT * FROM condidat ", (err, user_rows, fields) => {
         res.status(200)
         res.json(user_rows)
     })
 })
 //get One condidatEmployees
 router.get("/showOne", (req, res) => {
     pool.query("SELECT * FROM condidat  where id = ?", [
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
     pool.query("INSERT INTO `condidat`(`fullName`,`phoneNumber`, `email`,`cv_url`,`createdDate`,`updatedDate`,`idIntereviewDate`) VALUES (?,?,?,?,?,?,?)", [
         
         req.query.fullname,
         req.query.phoneNumber,
         req.query.email,
         req.query.cv_url,
         new Date(),
         new Date(),
         req.query.idIntereviewDate,
     
         
     
 
      ], (err, rows, fields) => {
             console.log(err);
             res.status(200);
             res.json(rows);
         })
 })
 
 
 
 
 
     
 module.exports = router;