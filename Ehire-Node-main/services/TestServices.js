const express = require('express')
const router = express.Router();
const pool =require('../config/DbConnection')

//GET
//get all test
router.get("/showAll", (req, res) => {
    pool.query("SELECT * FROM test ", (err, user_rows, fields) => {
         res.status(200)
         res.json(user_rows)
     })
 })
 //get One test
 router.get("/showOne", (req, res) => {
     pool.query("SELECT * FROM test  where id = ?", [
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
 //Create test
 router.post("/add", (req, res) => {
 
     pool.query("INSERT INTO `test`(`topic`,`totalQuestions`,`duration`,`createdDate`,`updatedDate`) VALUES (?,?,?,?,?)", [
         
         req.query.topic,
         req.query.totalQuestions,
         req.query.duration,
         new Date(),
         new Date(),
 ], (err, rows, fields) => {
             console.log(err);
             res.status(200);
             res.json(rows);
         })
 })
 
 
 
 
 
     
 module.exports = router;