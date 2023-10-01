<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<%@ include file="docHead.jsp" %>
<body class="page-body">
	<%@ include file="navbar.jsp" %>
	<section class="main-section">
		<h2>Déclinaison des adjectifs</h2>
		
		<!-- timer -->
		<aside class="timer-group">
			<div class="timer-progress">
				<canvas height="200px" width="200px" id="timer-progress"></canvas>
			</div>
			<div class="timer w3-theme-l3">
				<div class="timer-content">
					<div class="timer-writing">
						<p id="clock-time" class="time">2:00</p>
						<p id="current-score">0</p>
					</div>
				</div>
			</div>
		</aside>
		
		<!-- Corps de la question -->
		<div id="question-container" class="w3-container">
			<div class="w3-center">
			</div>
		</div>
		
		<div class="w3-center">
			<button id="submit-answer" class="w3-button w3-theme-d1 w3-circle submit-button" onclick="checkAnswer()">Soumettre la réponse</button>
		</div>
		
	</section>
	<%@ include file="pageFooter.jsp" %>	
</body>

<script type="text/javascript">
var canvas = document.getElementById("timer-progress");
var ctx = canvas.getContext("2d");
ctx.strokeStyle = "#b68900";
ctx.lineWidth = 20;
ctx.translate(75, 75);
ctx.rotate(-Math.PI/2);
var initialTime = ${initialTime};
var remainingTime = ${remainingTime};
var clockTime = document.getElementById("clock-time");
var timerIntervalId = setInterval(timeProgress, 1000);

var currentScore = ${currentScore};
var answerButtons;
var correctAnswer;
var wrongAnswerAlert;
var questionContainer = document.getElementById("question-container");
var csText = document.getElementById("current-score");
var submitAnswerButton = document.getElementById("submit-answer");
var vocParams = '${vocParams}';
var failedAttempts = 0;
getNextQuestion();


function onoff(element)
{
  if(element.value == 'off')
  {
    element.className = element.className.replace("w3-grey", "w3-green");
    element.value = 'on';
    element.innerHTML = 'oui';
  }
  else
  {
	element.className = element.className.replace("w3-green", "w3-grey");
	element.value = 'off';
	element.innerHTML = 'non';
  }
}

function reinitialize(){
	answerButtons = [document.getElementById("nom-m-sg"), document.getElementById("nom-f-sg"), document.getElementById("nom-n-sg"),
		document.getElementById("voc-m-sg"), document.getElementById("voc-f-sg"), document.getElementById("voc-n-sg"),
		document.getElementById("acc-m-sg"), document.getElementById("acc-f-sg"), document.getElementById("acc-n-sg"),
		document.getElementById("gen-m-sg"), document.getElementById("gen-f-sg"), document.getElementById("gen-n-sg"),
		document.getElementById("dat-m-sg"), document.getElementById("dat-f-sg"), document.getElementById("dat-n-sg"),
		document.getElementById("abl-m-sg"), document.getElementById("abl-f-sg"), document.getElementById("abl-n-sg"),
		document.getElementById("nom-m-pl"), document.getElementById("nom-f-pl"), document.getElementById("nom-n-pl"),
		document.getElementById("voc-m-pl"), document.getElementById("voc-f-pl"), document.getElementById("voc-n-pl"),
		document.getElementById("acc-m-pl"), document.getElementById("acc-f-pl"), document.getElementById("acc-n-pl"),
		document.getElementById("gen-m-pl"), document.getElementById("gen-f-pl"), document.getElementById("gen-n-pl"),
		document.getElementById("dat-m-pl"), document.getElementById("dat-f-pl"), document.getElementById("dat-n-pl"),
		document.getElementById("abl-m-pl"), document.getElementById("abl-f-pl"), document.getElementById("abl-n-pl")];
	var caJson = document.getElementById("ca").value;
	correctAnswer = JSON.parse(caJson);
	wrongAnswerAlert = document.getElementById("wrong-answer");
	failedAttempts = 0;
}

function timeProgress(){
	remainingTime = remainingTime - 1;
	var min = Math.trunc(remainingTime/60);
	var sec = remainingTime - 60 * min;
	clockTime.innerHTML = (sec < 10)? min + ":0" + sec : min + ":" + sec;

	ctx.clearRect(-75, -75, 150, 150);
	ctx.beginPath();
	ctx.arc(0, 0, 64, - 2 * remainingTime * Math.PI / initialTime, 0);
	ctx.stroke();
	
	if (remainingTime <= 0) endExam();
}

function checkAnswer(){
	var isOK = true;
	for (var i = 0; i < 12; i++){
		if (answerButtons[i].value == "on" && correctAnswer[i] != 1){
			isOK = false;
			break;
		}
		if (answerButtons[i].value == "off" && correctAnswer[i] != 0){
			isOK = false;
			break;
		}
	}
	if(isOK) {
		currentScore += 1;
		writeStats();
		csText.innerHTML = currentScore;
		getNextQuestion();

	}
	else{
		wrongAnswerAlert.style.display = "block";
		setTimeout(function() { 
			wrongAnswerAlert.style.display = "none"; 
		}, 500);
		failedAttempts +=1;
	}
}

var stats = [];
var startQuestionTime = initialTime;
function writeStats(){
	var timeUsed = startQuestionTime-remainingTime;
	startQuestionTime = remainingTime;
	
	stats.push([document.getElementById("motType").value,document.getElementById("motCat").value, document.getElementById("terminaison").value, timeUsed, failedAttempts]);
}

function getNextQuestion(){
	var examState = {'initialTime' : initialTime,
			'remainingTime' : remainingTime,
			'currentScore' : currentScore}
	var formBody = [];
	for (var property in examState) {
	  var encodedKey = encodeURIComponent(property);
	  var encodedValue = encodeURIComponent(examState[property]);
	  formBody.push(encodedKey + "=" + encodedValue);
	}
	formBody = formBody.join("&");
	
	fetch(window.location.href, {
		method: 'POST',
		headers:{'Content-Type': 'application/x-www-form-urlencoded'}, 
		body: formBody
	})
		.then((response) => {
			return response.text();
		})
		.then((html) => {
			questionContainer.innerHTML = html;
			reinitialize();
		});
}

function endExam(){
	clearInterval(timerIntervalId);
	submitAnswerButton.style.display = 'none';
	var endData = {'score' : currentScore,
			'initialTime' : initialTime,
			'vocParams' : vocParams,
			'stats' : JSON.stringify(stats)};
	var formBody = [];
	for (var property in endData) {
	  var encodedKey = encodeURIComponent(property);
	  var encodedValue = encodeURIComponent(endData[property]);
	  formBody.push(encodedKey + "=" + encodedValue);
	}
	formBody = formBody.join("&");
	
	fetch("${pageContext.request.contextPath}/exercices/result", {
		method: 'POST',
		headers:{'Content-Type': 'application/x-www-form-urlencoded'}, 
		body: formBody
	})
		.then((response) => {
			return response.text();
		})
		.then((html) => {
			questionContainer.innerHTML = html;
		});
	
}
</script>
</html>