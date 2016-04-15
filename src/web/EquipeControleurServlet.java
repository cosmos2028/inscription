package web;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.Equipe;
import metier.IMetier;
import metier.MetierImpl;
import metier.Personne;

public class EquipeControleurServlet extends HttpServlet 
{

IMetier metier;
	
	@Override
	public void init() throws ServletException 
	{
		metier = new MetierImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse responses) throws ServletException, IOException {
		doPost(request,responses);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EquipeModel model = new EquipeModel();
		Map<String, String> allPersInEquip;
		request.setAttribute("modele", model);
		String action = request.getParameter("action");
		model.setPersonne(metier.GetAllPersonne());
		model.setEquipe(metier.GetAllEquipe());
		model.setAllPersInEquipe(metier.GetAllPersonneInEquipe());
//		allPersInEquip = metier.GetAllPersonneInEquipe();
//		request.setAttribute("AllPersInEquipe", allPersInEquip);
		if (action !=null) 
		{
			if (action.equals("chercherPers")) 
			{
				
				model.setMotCle(request.getParameter("motclePers"));
				allPersInEquip = metier.SerchPersonneInEquipeParMC(model.getMotCle());
//				model.setEquipe(equipe);
			}
			else if(action.equals("ajouterEquip"))
			{
				
				String equip = request.getParameter("nom");
				model.getEquip().setNom(equip);
				metier.addEquipe(model.getEquip());
				model.setInputNom(model.getEquip().getNom());
				model.setEquipe(metier.GetAllEquipe());
			}
			
			else if(action.equals("ajouter_pers"))
			{
					
				String perselect = request.getParameter("perselect");
				String equipSelect = request.getParameter("equipSelect");
				metier.addPersonneInEquipe(equipSelect, perselect);
				model.setAllPersInEquipe(metier.GetAllPersonneInEquipe());
				
			}else if (action.equals("ajouter_pers")) 
			{
				String equip = request.getParameter("eqip");
				String pers = request.getParameter("pers");
				metier.addPersonneInEquipe(equip, pers);
				
				model.setAllPersInEquipe(metier.GetAllPersonneInEquipe());
	
			}
		}
		
		request.getRequestDispatcher("Equipe.jsp").forward(request, response);

	}
}

