package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.Competition;
import metier.IMetier;
import metier.Inscriptions;
import metier.MetierImpl;
import metier.Personne;

public class InscriptionControleurServlet extends HttpServlet{
 
IMetier metier;
	
	@Override
	public void init() throws ServletException 
	{
		metier = new MetierImpl();
	}
     
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);		
	}	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		InscriptionModel model = new InscriptionModel();
		String action = request.getParameter("action");
		
	        String candSelect;
	        
	        request.setAttribute("model", model);
	        
	        if (action !=null) 
			{
				
				if (action.equals("Envoyer")) 
				{
					  candSelect = request.getParameter("candidSelect");
					  request.setAttribute("modelCompet",  metier.GetAllCompetInscrit(candSelect));
					  model.setMode("Inscrire");
					  model.setValSelect(candSelect);
				}
				else if(action.equals("Inscrire")) 
				{
					String cand = request.getParameter("candidSelect");
					String compet = request.getParameter("compselect");
					metier.addInscrit(cand, compet);
//					request.setAttribute("modelAllInscrit", );
				}
			}
	     
	    request.setAttribute("model", model);
		request.setAttribute("modelCandid", metier.GetAllCandidat());
		
		request.getRequestDispatcher("Inscription.jsp").forward(request, response);
		
	}
	
}


