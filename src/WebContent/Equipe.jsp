<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Equipe</title>
<link rel="stylesheet" type="text/css" href="css/style.css">

<script type="text/javascript">
	function confirmer(url)
	{
		var rep=confirm("Etes vous sûr  de vouloir supprimer?");
		
		if(rep==true)
			{
				document.location=url;
			}
	}
</script>

</head>
<body>
<%@ include file="menu.jsp" %>

	<div id="colEquip">
				
		<h3 style="margin-top: 0;">Ajouter une équipe</h3>
				
		<form action="equipe.david" method="post">
            
        	<label  class="col1_label"> Nom</label>
                   
        	<input type="text" name="nom" value="${modele.inputNom}"/> <br/>
          			
            <input type="submit" value="Enregistrer" name="action" class="col1_input_equip" >
    	</form>
    			
		<div> ${modele.msgError}</div>
	
	</div>
				
	<table>
		<tr>
			
			<td>
				<div>
					<h3>Rechercher une équipe</h3>
					<form action="equipe.david" method="post">
						<table>
							<tr>
								<td>Mot clé</td>
								<td><input type="text" name="motcle" value="${modele.motCle }"></td>
								<td><input type="submit" value="chercher" name="action" ></td>
							</tr>
						</table>
					</form>
	
					<table class="table1" cellspacing="0" cellpadding="0">
						<tr>
							<th>Nom</th> <th>Membre</th> <th>Modifier</th> <th>Supprimer</th> 
						</tr>
						
						<c:forEach items="${modele.equipe}" var="p">
							<tr>
								<td>${p.nom}</td>
								<td>${p.nom}</td>
								<td><a href="personne.david?action=modifier&nom=${p.nom}"><img src="img/edit.jpg" alt="edit_Image" /></a></td>
								<td><a href="javascript:confirmer('equipe.david?action=delete&nom=${p.nom}')"><img src="img/supp.jpg" alt="supprimer_Image" /></a></td>

							</tr>
			
						</c:forEach>
					</table>
				</div>
			</td>
		 </tr>
		 <tr>
			<td>
<!-- 				<div id="col2"> -->
<!-- 					<h3>Ajouter une personne dans une  equipe</h3> -->
<!-- 						<form action="equipe.david" method="post"> -->
            
<!--    							<p> -->
<!--       						<label for="personne">personne</label> -->
<!--       						<select name="perselect" id="personne"> -->
<%--          					<c:forEach items="${modele.personne}" var="p"> --%>
<%-- 							<option value="<c:out value='${p.nom}'/>">${p.nom}</option> --%>
				
<%-- 							</c:forEach>   --%>
<!--       						</select> -->
<!--    							</p> -->
   		
<!--    							<p> -->
<!--       						<label for="Equipe">Equipe</label> -->
<!--       						<select name="equipSelect" id="Equipe"> -->
<%--          					<c:forEach items="${modele.equipe}" var="p"> --%>
<%-- 							<option value="<c:out value='${p.nom}'/>">${p.nom}</option> --%>
				
<%-- 							</c:forEach>   --%>
<!--       						</select> -->
<!--    							</p> -->
<!--    							<input type="submit" value="ajouter_pers" name="action" class="col1_input" > -->
   	
<!-- 						</form> -->
          
<%-- 					<div> ${modele.msgError}</div> --%>
	
<!-- 				</div> -->
<!-- 			</td> -->
			
<!-- 			<td> -->
<!-- 				<div> -->
<!-- 					<h3>Rechercher les personnes d'une équipe</h3> -->
<!-- 					<form action="equipe.david" method="post"> -->
<!-- 						<table> -->
<!-- 							<tr> -->
<!-- 								<td>Mot clé</td> -->
<%-- 								<td><input type="text" name="motclePers" value="${modele.motCle }"></td> --%>
<!-- 								<td><input type="submit" value="chercherPers" name="action" ></td> -->
<!-- 							</tr> -->
<!-- 						</table> -->
<!-- 					</form> -->
	
<!-- 					<table class="table1" cellspacing="0" cellpadding="0"> -->
<!-- 						<tr> -->
<!-- 							<th>Personne</th> <th>Equipe</th>  -->
<!-- 						</tr> -->
<%-- <%-- 						<c:forEach items="${modele.AllPersInEquipe}" var="p"> --%> --%>
<!-- <!-- 							<tr> --> -->
			
<%-- <%-- 								<td>${p.key}</td> --%> --%>
<%-- <%-- 								<td>${p.value}</td> --%> --%>
<%-- <%-- 								<td><a href="javascript:confirmer('equipe.david?action=delete&nom=${p.key}')">Supprimer</a></td> --%> --%>

<!-- <!-- 							</tr> --> -->
			
<%-- <%-- 						</c:forEach> --%> --%>
<!-- 					</table> -->
<!-- 				</div> -->
<!-- 			</td> -->
		 </tr>
	</table>
	
	
	
	
	
	
	


</body>
</html>