const express = require('express')
const router = express.Router();
const pool =require('../config/DbConnection')
//GET
//get all question
router.get("/showAll", (req, res) => {
    pool.query("SELECT * FROM question ", (err, user_rows, fields) => {
         res.status(200)
         res.json(user_rows)
     })
 })
 //get One question
 router.get("/showOne", (req, res) => {
     pool.query("SELECT * FROM question  where id = ?", [
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
 //Create question
 router.post("/add", (req, res) => {
  
     pool.query("INSERT INTO `question`(`description`,`choiceA`,`choiceB`,`choiceC`,`choiceD`,`rightAnswer`,`createdDate`,`updatedDate`,`idTest`,`score`) VALUES (?,?,?,?,?,?,?,?,?,?)", [
         
         req.query.description,
         req.query.choiceA,
         req.query.choiceB,
         req.query.choiceC,
         req.query.choiceD,
         req.query.rightAnswer,
          new Date(),
          new Date(),
         req.query.idTest,
         req.query.score,
 ], (err, rows, fields) => {
             console.log(err);
             res.status(200);
             res.json(rows);
         })
 })
 
 
 
 
 
     
 module.exports = router;