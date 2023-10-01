<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<%@ include file="docHead.jsp" %>
<body>
	<%@ include file="navbar.jsp" %>
	<section>
		<p class="w3-center">Bonjour ${sessionScope.user.getFirstName()}</p>
		<div class="w3-row-padding">
    		<div class="w3-col l3 m6 w3-margin-bottom">
				<a href="./exercices">
					<div class="w3-card w3-display-container w3-hover-opacity">
						<header class="w3-theme-l2 w3-container">
							<h2>Exercices</h2>
						</header>
						<article class="w3-container">
							<img alt="oups" src="images/exercice-img.png" class="w3-image">
							<p class="w3-display-middle w3-display-hover w3-button w3-theme-d4">Allez voir toutes vos statistiques</p>
						</article>
					</div>
				</a>
			</div>
    		<div class="w3-col l3 m6 w3-margin-bottom">
				<a href="./devoirs">
					<div class="w3-card">
						<header class="w3-theme-l2 w3-container">
							<h2>Mes devoirs</h2>
						</header>
						<article class="w3-container">
							<img alt="oups" src="images/testChart.png" class="w3-image">
						</article>
					</div>
				</a>
			</div>
    		<div class="w3-col l3 m6 w3-margin-bottom">
				<a href="./stats">
					<div class="w3-card">
						<header class="w3-theme-l2 w3-container">
							<h2>Mes Statistiques</h2>
						</header>
						<article class="w3-container">
							<img alt="oups" src="images/testChart.png" class="w3-image">
						</article>
					</div>
				</a>
			</div>
    		<div class="w3-col l3 m6 w3-margin-bottom">
				<a href="./compte">
					<div class="w3-card">
						<header class="w3-theme-l2 w3-container">
							<h2>Mon compte</h2>
						</header>
						<article class="w3-container">
							<img alt="oups" src="images/testChart.png" class="w3-image">
						</article>
					</div>
				</a>
			</div>
		</div>
	</section>
	<%@ include file="pageFooter.jsp" %>	
</body>
</html>