<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<div class="w3-center w3-card">
		<h2>Exercice terminé</h2>
		<p>Votre score : ${score}<p>
		<p>Meilleur résultat précédent: ${previousBest}</p>
		<form action="${pageContext.request.contextPath}/exercices/decl-noms" method="post">
			<input type="hidden" name="initial-time" value="${initialTime}">
			<input type="hidden" name="vocParams" value="${vocParams}">
			<input type="submit" value="Réessayer">
		</form>
	</div>
</html>