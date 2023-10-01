<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<div class="w3-center">
		<p>${question.getEnonce()[0]}</p>
		<p>${question.getEnonce()[1]}<p>
		<p>${question.getEnonce()[2]} ${question.getEnonce()[3]}</p>
	</div>
	<div>
		<table class="answer-table w3-border">
			<thead>
			<tr>
				<th class="th-cas">Cas</th>
				<th class="th-sg">m.sg.</th>
				<th class="th-sg">f.sg.</th>
				<th class="th-sg">n.sg.</th>
				<th class="th-sg">m.pl.</th>
				<th class="th-sg">f.pl.</th>
				<th class="th-sg">n.pl.</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td><label>Nominatif</label></td>
				<td><button id="nom-m-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="nom-f-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="nom-n-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="nom-m-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="nom-f-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="nom-n-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
			</tr>
			<tr>
				<td><label>Vocatif</label></td>
				<td><button id="voc-m-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="voc-f-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="voc-n-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="voc-m-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="voc-f-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="voc-n-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
			</tr>
			<tr>
				<td><label>Accusatif</label></td>
				<td><button id="acc-m-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="acc-f-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="acc-n-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="acc-m-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="acc-f-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="acc-n-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
			</tr>
			<tr>
				<td><label>Génitif</label></td>
				<td><button id="gen-m-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="gen-f-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="gen-n-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="gen-m-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="gen-f-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="gen-n-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
			</tr>
			<tr>
				<td><label>Datif</label></td>
				<td><button id="dat-m-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="dat-f-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="dat-n-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="dat-m-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="dat-f-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="dat-n-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
			</tr>
			<tr>
				<td><label>Ablatif</label></td>
				<td><button id="abl-m-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="abl-f-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="abl-n-sg" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="abl-m-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="abl-f-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
				<td><button id="abl-n-pl" class="w3-block w3-button w3-grey" value="off" onclick="onoff(this)">non</button></td>
			</tr>
			</tbody>
		</table>
		<input id="ca" type="hidden" value='[${ question.getAnswer().get(0)}, ${ question.getAnswer().get(1)}, ${ 
		question.getAnswer().get(2)}, ${ question.getAnswer().get(3)}, ${ question.getAnswer().get(4)}, ${ 
		question.getAnswer().get(5)}, ${ question.getAnswer().get(6)}, ${ question.getAnswer().get(7)}, ${ 
		question.getAnswer().get(8)}, ${ question.getAnswer().get(9)}, ${ question.getAnswer().get(10)}, ${ 
		question.getAnswer().get(11)}, ${ question.getAnswer().get(12)}, ${ question.getAnswer().get(13)}, ${ 
		question.getAnswer().get(14)}, ${ question.getAnswer().get(15)}, ${ question.getAnswer().get(16)}, ${ 
		question.getAnswer().get(17)}, ${ question.getAnswer().get(18)}, ${ question.getAnswer().get(19)}, ${ 
		question.getAnswer().get(20)}, ${ question.getAnswer().get(21)}, ${ question.getAnswer().get(22)}, ${ 
		question.getAnswer().get(23)}, ${ question.getAnswer().get(24)}, ${ question.getAnswer().get(25)}, ${ 
		question.getAnswer().get(26)}, ${ question.getAnswer().get(27)}, ${ question.getAnswer().get(28)}, ${ 
		question.getAnswer().get(29)}, ${ question.getAnswer().get(30)}, ${ question.getAnswer().get(31)}, ${ 
		question.getAnswer().get(32)}, ${ question.getAnswer().get(33)}, ${ question.getAnswer().get(34)}, ${ 
		question.getAnswer().get(35)}]'>
		<input id="motType" type="hidden" value="${question.getMotType().name()}">
		<input id="motCat" type="hidden" value="${question.getMotCat()}">
		<input id="terminaison" type="hidden" value="${question.getTerminaison()}">
	</div>
</html>