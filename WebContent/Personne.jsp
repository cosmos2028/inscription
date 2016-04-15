<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personne</title>
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
	<div id="col1">
	<form action="personne.david" method="post">
        
        <c:if test="${modele.mode=='ajouter' }">
            
                <label for="rue" class="col1_label"> Nom</label>
                    <input type="text" name="nom" value="${modele.pers.nom }"/> <br/>
          

        </c:if>
        <c:if test="${modele.mode=='modifier' }">
        
               <label for="rue"> Nom </label>
                    <span style="margin-left: 22px;">${modele.pers.nom}</span> <br/>
                    <input type="hidden" name="nom" value="${modele.pers.nom }"/> <br/>
                
           
            
        </c:if>
            
                <label for="rue"> Prenom</label>
                    <input type="text" name="prenom" value="${modele.pers.prenom }"/> <br/>
            
                <label for="rue" class="col1_label"> Mail</label>
                    <input type="text" name="mail" value="${modele.pers.mail }"/> <br/>
           
           
                <input type="submit" value="ajouter" name="action" class="col1_input" >
                <input type="hidden" value ="${modele.mode }" name="mode"/>
    </form>
	<div> ${modele.msgError}</div>
	
	</div>
	
	
	<div>
		<form action="personne.david" method="post">
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
				<th>Nom</th> <th>Prenom</th><th>Mail</th>
			</tr>
			<c:forEach items="${modele.personnes}" var="p">
			<tr>
				<td>${p.nom}</td>
				<td>${p.prenom}</td>
				<td>${p.mail}</td>
				<td><a href="javascript:confirmer('personne.david?action=delete&nom=${p.nom}')">Supprimer</a></td>
				<td><a href="personne.david?action=modifier&nom=${p.nom}">Modifier</a></td>
				
			</tr>
			
			</c:forEach>
		</table>
	</div>
</body>
</html>