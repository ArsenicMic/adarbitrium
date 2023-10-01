<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="w3-bar w3-theme-l1">
	    <a href="${pageContext.request.contextPath}/" class="w3-bar-item w3-button"><i class="fa fa-home"></i></a>
	    <a href="${pageContext.request.contextPath}/exercices" class="w3-bar-item w3-button w3-hide-small">Exercices</a>
	    <a href="${pageContext.request.contextPath}/signout" class="w3-bar-item w3-right w3-button w3-hide-small"><i class="fa fa-sign-out"></i></a>
	    <a href="${pageContext.request.contextPath}/compte" class="w3-bar-item w3-right w3-button w3-hide-small">Mon compte</a>
	    <a href="${pageContext.request.contextPath}/stats" class="w3-bar-item w3-right w3-button w3-hide-small">Mes statistiques</a>
	    <a href="${pageContext.request.contextPath}/devoirs" class="w3-bar-item w3-right w3-button w3-hide-small">Mes devoirs</a>
	    <button class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="showVerticalMenu()"><i class="fa fa-bars"></i></button>
	    <div id="verticalMenu" class="w3-bar-block w3-hide w3-hide-large w3-hide-medium w3-theme-l1">
			<a href="${pageContext.request.contextPath}/exercices" class="w3-bar-item w3-button">Exercices</a>
			<a href="${pageContext.request.contextPath}/devoirs" class="w3-bar-item w3-right w3-button">Mes devoirs</a>
			<a href="${pageContext.request.contextPath}/stats" class="w3-bar-item w3-right w3-button">Mes statistiques</a>
			<a href="${pageContext.request.contextPath}/compte" class="w3-bar-item w3-right w3-button">Mon compte</a>
			<a href="${pageContext.request.contextPath}/signout" class="w3-bar-item w3-right w3-button">Déconnexion</a>		    		
	    </div>
</nav>

<script>
var x = document.getElementById("verticalMenu");
function showVerticalMenu() {
  if (x.className.indexOf("w3-show") == -1) {
    x.className += " w3-show";
  } else { 
    x.className = x.className.replace(" w3-show", "");
  }
}
</script>