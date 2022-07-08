const express = require('express')
const router = express.Router();
const pool =require('../config/DbConnection')
//GET
//get all intereview
router.get("/showAll", (req, res) => {
    pool.query("SELECT * FROM interview ", (err, user_rows, fields) => {
         res.status(200)
         res.json(user_rows)
     })
 })
 //get One intereview
 router.get("/showOne", (req, res) => {
     pool.query("SELECT * FROM interview  where id = ?", [
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
 //Create intereview
 router.post("/add", (req, res) => {
         console.log(req.query.offer_id);
     pool.query("INSERT INTO `interview`(`id_interview`,`date_interview`,`update_interview`,`type_interview`,`statut`) VALUES (?,?,?,?,?)", [
         
         req.query.id_interview,
         req.query.date_interview,
         req.query.update_interview,
         req.query.type_interview,
         req.query.statut,
 ], (err, rows, fields) => {
             console.log(err);
             res.status(200);
             res.json(rows);
         })
 })
 
 
 
 
 
     
 module.exports = router;