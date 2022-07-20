
const mysql = require('mysql');
const pool = mysql.createPool({
    connectionLimit: 10,
    host: "localhost",
    user: "root",
    database: "ehire"
})



module.exports =pool ;
