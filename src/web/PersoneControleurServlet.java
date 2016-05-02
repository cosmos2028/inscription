package web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.IMetier;
import metier.MetierImpl;
import metier.Personne;

public class PersoneControleurServlet extends HttpServlet{

IMetier metier;
	
	@Override
	public void init() throws ServletException 
	{
		metier = new MetierImpl();
	}
     
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException 
	{
		doPost(request, response);		
	}	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PersonneModel model = new PersonneModel();
		EquipeModel modelEquip = new EquipeModel();
		String action = request.getParameter("action");
//		modelEquip.setEquipe(metier.GetAllEquipe());

		request.setAttribute("modele", model);
		request.setAttribute("modelEquip", modelEquip);
		
		if (action !=null) 
		{
			
			if (action.equals("chercher")) 
			{
				
				model.setMotCle(request.getParameter("motcle"));
				Set<Personne> personnes = metier.SerchPersonneParMC(model.getMotCle());
				model.setPersonnes(personnes);
			}
			else if(action.equals("delete"))
			{
				String pers = request.getParameter("nom");
				metier.DeletePersonne(pers);
				model.setPersonnes(metier.GetAllPersonne());
			}
			else if(action.equals("Enregistrer"))
			{
				try 
				{
					
				 model.getPers().setNom(request.getParameter("nom"));
				 model.getPers().setPrenom(request.getParameter("prenom"));
				 model.getPers().setMail(request.getParameter("mail"));
				 model.setMode(request.getParameter("mode"));
				 if (model.getMode().equals("ajouter")) 
				 {
					 metier.addPersonne(model.getPers());
				 }else if(model.getMode().equals("modifier"))
					 metier.UpdatePersonne(model.getPers());
				
				model.setPersonnes(metier.GetAllPersonne());
				} catch (Exception e) {
					model.setMsgError(e.getMessage());
				}
			}else if(action.equals("modifier"))
			{
				String nom = request.getParameter("nom");
				Personne pers = metier.GetPersonne(nom);
				model.setPers(pers);
				model.setMode("modifier");
				model.setPersonnes(metier.GetAllPersonne());
			}
		}
		
		request.getRequestDispatcher("Personne.jsp").forward(request, response);
		
	}
	
}

