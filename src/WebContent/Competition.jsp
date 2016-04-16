<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compétition</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript">
	function confirmer(url)
	{
		var rep=confirm("Etes vous sûr  de vouloir modifier?");
		
		if(rep==true)
			{
				document.location=url;
			}
	}
</script>
</head>
<body>
	<%@ include file="menu.jsp" %>
	<div id="col1">
	<form action="competition.david" method="post">
            
                <label for="nom" class="col1_label"> Nom</label>
                <input type="text" name="nom" value=""/> <br/>
                <label for="date" class="col1_label"> Date</label>
                <input type="date" name="date" value="" style ="width: 175px;"/> <br/>
                <label for="simple">Simple</label><input type="radio" name="simple" value="simple" id="simple" /> 
                <label for="equipe">Par equipe</label><input type="radio" name="simple" value="equipe" id="equipe" /><br /><br />
          
                <input type="submit" value="Ajouter" name="action" class="col1_input" >
              
    </form>
	<div> ${modele.msgError}</div>
	
	</div>
	
	
	<div>
		<form action="competition.david" method="post">
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
				<th>Nom</th> <th>Date</th> <th>Type</th> <th>Modifier</th> 
			</tr>
			<c:forEach items="${modele.personnes}" var="p">
			<tr>
				<td>${p.nom}</td>
				<td>${p.prenom}</td>
				<td>${p.mail}</td>
				<td><a href="javascript:confirmer('personne.david?action=delete&nom=${p.nom}')"> <img src="img/edit.jpg" alt="edit_Image" /> </a></td>
			</tr>
			
			</c:forEach>
		</table>
	</div>
</body>
</html>