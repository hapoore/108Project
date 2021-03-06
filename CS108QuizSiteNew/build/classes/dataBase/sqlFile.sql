USE c_cs108_parkerp1;

mysql -h mysql-user.stanford.edu -u ccs108parkerp1 -p

DROP TABLE IF EXISTS administrator;
CREATE TABLE administrator(
announcement0 TEXT,
announcement1 TEXT,
announcement2 TEXT,
announcement3 TEXT,
announcement4 TEXT,
numQuizzes INT,
numUsers INT);

DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
userName CHAR(64), 
password CHAR(64), 
passwordHint CHAR(64),
securityQuestion CHAR(128), 
securityAnswer CHAR(64),
profilePic TEXT,
salt CHAR(16),
status TEXT,
numRatings INT,
rating DECIMAL(3,2),
administratorStatus BOOLEAN,
number INT);

DROP TABLE IF EXISTS quizzes;

CREATE TABLE quizzes(
userName CHAR(64),
quizName CHAR(64),
description TEXT,
tag CHAR(64),
dateCreated DATE,
rand BOOLEAN,
multiplePages BOOLEAN,
immediateCorrection BOOLEAN,
practiceMode BOOLEAN,
numPlays INT,
rating INT,
numRatings INT);

DROP TABLE IF EXISTS quizResults;

CREATE TABLE quizResults(
userName CHAR(64),
quizName CHAR(64),
creator CHAR(64),
timeTaken DATE,
score INT,
percentage DECIMAL(5,2),
timeNeeded INT);

DROP TABLE IF EXISTS question0;

CREATE TABLE question0(
userName CHAR(64),
quizName CHAR(64),
question TEXT,
answers TEXT,
number INT,
worth INT);

DROP TABLE IF EXISTS question1;

CREATE TABLE question1(
userName CHAR(64),
quizName CHAR(64),
question TEXT,
answers TEXT,
number INT,
worth INT);

DROP TABLE IF EXISTS question2;

CREATE TABLE question2(
userName CHAR(64),
quizName CHAR(64),
question TEXT,
answer TEXT,
wrongAnswer TEXT,
number INT,
worth INT);

DROP TABLE IF EXISTS question3;

CREATE TABLE question3(
userName CHAR(64),
quizName CHAR(64),
question TEXT,
url TEXT,
answers TEXT,
number INT,
worth INT);

DROP TABLE IF EXISTS question4;

CREATE TABLE question4(
userName CHAR(64),
quizName CHAR(64),
question TEXT,
objects TEXT,
matches TEXT,
number INT,
worth INT);

DROP TABLE IF EXISTS question5;

CREATE TABLE question5(
userName CHAR(64),
quizName CHAR(64),
question TEXT,
answer INT,
number INT,
worth INT);

DROP TABLE IF EXISTS question6;

CREATE TABLE question6(
userName CHAR(64),
quizName CHAR(64),
difficulty INT,
number INT,
worth INT);

DROP TABLE IF EXISTS question7;

CREATE TABLE question7(
userName CHAR(64),
quizName CHAR(64),
question TEXT,
number INT,
worth INT);

DROP TABLE IF EXISTS question8;

CREATE TABLE question8(
userName CHAR(64),
quizName CHAR(64),
question TEXT,
correctAnswers TEXt,
wrongAnswers TEXT,
number INT,
worth INT);

DROP TABLE IF EXISTS question9;

CREATE TABLE question9(
userName CHAR(64),
quizName CHAR(64),
question TEXT,
number INT,
worth INT);

	





