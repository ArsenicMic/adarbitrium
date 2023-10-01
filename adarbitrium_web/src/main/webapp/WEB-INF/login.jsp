<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<%@ include file="docHead.jsp" %>

<body>
	</div>
		<section>
		<h1 class="w3-center">Ad Arbitrium Grammatica</h1>
		<div class="w3-row-padding">
    		<div class="w3-col l6 m6 w3-margin-bottom">
    			<div class="w3-card w3-display-container w3-hover-opacity">
    				<header class="w3-theme-l2 w3-container">
					</header>
    				<article class="w3-container">
						<img alt="Login" src="images/priscianus.jpeg" class="w3-image"/>
					</article>
				</div>	

			</div>
    		<div class="w3-col l6 m6 w3-margin-bottom">
    			<div class="w3-card w3-display-container w3-hover-opacity">
    				<header class="w3-theme-l2 w3-container">
						<h2>Login</h2>
					</header>
	    			<article class="w3-container">
		    			<form method="post" action="./login">
							<p>
								<label for="username">Nom d'utilisateur: </label>
								<input type="text" name="username" id="username"/>
							</p>
							<p>
								<label for="password">Mot de passe: </label>
								<input type="password" name="password" id="password"/>
							</p>
							<p>
								<input type="checkbox" name="rememberMe" id="rememberMe"/>
								<label for="rememberMe">Se souvenir de moi</label>
							</p>
							<input type="hidden" name="from" value="${from}">
							<p>
								<input type="submit"/>
							</p>
						</form>
					</article>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="pageFooter.jsp" %>	
</body>
</html>