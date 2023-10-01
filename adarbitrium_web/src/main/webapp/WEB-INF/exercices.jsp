<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<%@ include file="docHead.jsp" %>
<body>
	<%@ include file="navbar.jsp" %>
	<section>
		<p>Bonjour ${sessionScope.user.getFirstName()}</p>
		<div class="w3-row-padding">
			<h2 class="w3-card w3-theme-l2 w3-center">Exercices de morphologie</h2>
		</div>
		<div class="w3-row-padding">
    		<div class="w3-col l3 m6 w3-margin-bottom">
    			<a onclick="document.getElementById('choix-decl-noms').style.display='block'">
					<div class="w3-card w3-display-container">
						<header class="w3-theme-l2 w3-container">
							<h2>Noms</h2>
						</header>
						<article class="w3-container">
							<img alt="oups" src="images/exercice-img.png" class="w3-image">
						</article>
					</div>
				</a>
				<div id="choix-decl-noms" class="w3-modal">
					<div class="w3-modal-content">
						<div class="w3-container">
							<span onclick="document.getElementById('choix-decl-noms').style.display='none'"
							class="w3-button w3-display-topright">&times;</span>
							<p>Paramètres de l'exercice de décliaisons?</p>
							<form action="./exercices/decl-noms" method="post">
								<p>Choix des déclinaisons à exercer</p>
								<input type="checkbox" id="1" name="1" value="1">
								<label for="1">Première déclinaison</label><br>
								<input type="checkbox" id="2" name="2" value="2">
								<label for="2">Deuxième déclinaison</label><br>
								<input type="checkbox" id="3" name="3" value="3">
								<label for="3">Troisième déclinaison</label><br>
								<input type="checkbox" id="4" name="4" value="4">
								<label for="4">Quatrième déclinaison</label><br>
								<input type="checkbox" id="5" name="5" value="5">
								<label for="5">Cinquième déclinaison</label><br>
								<p>Durée de l'exercice (2 min par défaut)</p>
								<input type="number", id="tpsEx" name="tpsEx" min="1" max="5" value="2">
								<label for="tpsEx">minutes.</label><br>
								<input type="hidden" name="hasCatChoice" value="true">
								<input type="submit" class="submit-button" value="Commencer l'exercice">
							</form>
						</div>
					</div>
				</div>
			</div>
    		<div class="w3-col l3 m6 w3-margin-bottom">
				<a onclick="document.getElementById('choix-decl-adj').style.display='block'">
					<div class="w3-card">
						<header class="w3-theme-l2 w3-container">
							<h2>Adjectifs</h2>
						</header>
						<article class="w3-container">
							<img alt="oups" src="images/exercice-img.png" class="w3-image">
						</article>
					</div>
				</a>
				<div id="choix-decl-adj" class="w3-modal">
					<div class="w3-modal-content">
						<div class="w3-container">
							<span onclick="document.getElementById('choix-decl-adj').style.display='none'"
							class="w3-button w3-display-topright">&times;</span>
							<p>Quelle(s) déclinaison(s) voulez-vous exercer?</p>
							<form action="./exercices/decl-adj" method="post">
								<input type="checkbox" id="1" name="1" value="1">
								<label for="1">Première classe d'adjectifs</label><br>
								<input type="checkbox" id="2" name="2" value="2">
								<label for="2">Deuxième classe d'adjectifs</label><br>
								<p>Durée de l'exercice (2 min par défaut)</p>
								<input type="number", id="tpsEx" name="tpsEx" min="1" max="5" value="2">
								<label for="tpsEx">minutes.</label><br>
								<input type="hidden" name="hasCatChoice" value="true">
								<input type="submit" class="submit-button" value="Commencer l'exercice">
							</form>
						</div>
					</div>
				</div>
			</div>
    		<div class="w3-col l3 m6 w3-margin-bottom">
				<a href="./../stats">
					<div class="w3-card">
						<header class="w3-theme-l2 w3-container">
							<h2>Pronoms</h2>
						</header>
						<article class="w3-container">
							<img alt="oups" src="images/testChart.png" class="w3-image">
						</article>
					</div>
				</a>
			</div>
    		<div class="w3-col l3 m6 w3-margin-bottom">
				<a href="./../devoirs">
					<div class="w3-card">
						<header class="w3-theme-l2 w3-container">
							<h2>Verbes</h2>
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