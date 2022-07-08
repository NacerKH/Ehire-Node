
var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
session = require('express-session');
var indexRouter = require('./routes/CondidatEmploye');


var app = express();
//-------------------------------------------------------
//-------------------------------------------------------
//WS
var condiadatEmployee=require('./services/CondidatEmpolyee');



//-------------------------------------------------------
//-------------------------------------------------------

// // view engine setup
// app.set('views', path.join(__dirname, 'views'));
// app.set('view engine', 'pug');
app.use(session({
	secret: 'secret',
	resave: true,
	saveUninitialized: true
}));
 app.use(logger('dev'));
app.use(express.json());
 app.use(express.urlencoded({ extended: false }));
 app.use(cookieParser());
 app.use(express.static(path.join(__dirname, 'public')));

//-------------------------------------------------------
//-------------------------------------------------------
//ROUTER
app.use('/', indexRouter);

app.use('/condidatEmployee', condiadatEmployee);



//-------------------------------------------------------
//-------------------------------------------------------
// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});
var mysql = require("mysql");
//Database connection
app.use(function(req, res, next){
	res.locals.connection = mysql.createConnection({
		host : 'localhost',
		user     : 'root',
		password : '',
		database : 'ehire'
	});
	res.locals.connect();
	next();
});
module.exports = app;