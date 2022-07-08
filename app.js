
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
var condidat=require('./services/CondidatServices');
var conge=require('./services/CongeServices');
var InterviewDate=require('./services/InterviewDateServices');
var Interview=require('./services/InterviewServices');
var JobOffers=require('./services/JobOffers');
var Test=require('./services/TestServices');
var QuestionsServices=require('./services/QuestionsServices');
var Users=require('./services/UserServices');
var Category=require('./services/CategoryServices');
var Support=require('./services/SupportServices');



//-------------------------------------------------------
//-------------------------------------------------------

// // view engine setup
// app.set('views', path.join(__dirname, 'views'));
// app.set('view engine', 'pug');
const oneDay = 1000 * 60 * 60 * 24;
app.use(session({
	secret: "kalitobeyondtoinifnityNotStoop##§",
	saveUninitialized: true,
	cookie: { maxAge: oneDay },
	resave: false ,
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
app.use('/condidat', condidat);
app.use('/conge', conge);
app.use('/InterviewDate', InterviewDate);
app.use('/Interview', Interview);
app.use('/JobOffers', JobOffers);
app.use('/Test', Test);
app.use('/Users', Users);
app.use('/QuestionsServices', QuestionsServices);
app.use('/Support', Support);
app.use('/Category', Category);




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