const express = require('express')
const router = express.Router();
const pool =require('../config/DbConnection')
//GET
//get all support
router.get("/showAll", (req, res) => {
    pool.query("SELECT * FROM support ", (err, user_rows, fields) => {
         res.status(200)
         res.json(user_rows)
     })
 })
 //get One support
 router.get("/showOne", (req, res) => {
     pool.query("SELECT * FROM support  where id = ?", [
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
 //Create support
 router.post("/add", (req, res) => {
 
     pool.query("INSERT INTO `support`(`id_support`,`id_interview`,`description_offre`,`cv_candidate`) VALUES (?,?,?,?)", [
         
         req.query.id_support,
         req.query.id_interview,
         req.query.description_offre,
         req.query.cv_candidate,

 ], (err, rows, fields) => {
             console.log(err);
             res.status(200);
             res.json(rows);
         })
 })
 
 
 
 
 
     
 module.exports = router;