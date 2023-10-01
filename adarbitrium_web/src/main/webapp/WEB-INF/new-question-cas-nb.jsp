<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<div class="w3-center">
		<p>${question.getEnonce()[0]}</p>
		<h3>${question.getEnonce()[1]}</h3>
		<p>${question.getEnonce()[2]} ${question.getEnonce()[3]}</p>
	</div>
	<div>
		<table class="answer-table w3-border">
			<thead>
			<tr>
				<th class="th-cas">Cas</th>
				<th class="th-sg">Singulier</th>
				<th class="th-pl">Pluriel</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td><label>Nominatif</label></td>
				<td><button id="nom-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="nom-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
			</tr>
			<tr>
				<td><label>Vocatif</label></td>
				<td><button id="voc-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="voc-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
			</tr>
			<tr>
				<td><label>Accusatif</label></td>
				<td><button id="acc-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="acc-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
			</tr>
			<tr>
				<td><label>Génitif</label></td>
				<td><button id="gen-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="gen-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
			</tr>
			<tr>
				<td><label>Datif</label></td>
				<td><button id="dat-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="dat-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
			</tr>
			<tr>
				<td><label>Ablatif</label></td>
				<td><button id="abl-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="abl-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
			</tr>
			</tbody>
		</table>
		<input id="ca" type="hidden" value='[${question.getAnswer().get(0)}, ${ question.getAnswer().get(1)}, ${ 
		question.getAnswer().get(2)}, ${ question.getAnswer().get(3)}, ${ question.getAnswer().get(4)}, ${ 
		question.getAnswer().get(5)}, ${ question.getAnswer().get(6)}, ${ question.getAnswer().get(7)}, ${ 
		question.getAnswer().get(8)}, ${ question.getAnswer().get(9)}, ${ question.getAnswer().get(10)}, ${ 
		question.getAnswer().get(11)}]'>
		<input id="motType" type="hidden" value="${question.getMotType().name()}">
		<input id="motCat" type="hidden" value="${question.getMotCat()}">
		<input id="terminaison" type="hidden" value="${question.getTerminaison()}">
	</div>
</html>