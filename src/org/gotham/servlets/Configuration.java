package org.gotham.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gotham.bll.BLLFactory;
import org.gotham.bll.PageManager;
import org.gotham.businessException.BusinessException;

/**
 * Servlet implementation class Configuration
 */
@WebServlet(description = "Servlet regirigeant vers la page de configuration", urlPatterns = { "/Configuration" })
public class Configuration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Configuration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/configuration.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		if(request.getParameter("enregistrer")!=null)
		{
			List<Integer>listeCodesErreur = new ArrayList<>();
			
			String titre = request.getParameter("titre");
			int nbHabitants = Integer.parseInt(request.getParameter("nbHabitants"));
			String contenue = request.getParameter("contenu");
			LocalDate dateConseil = LocalDate.parse(request.getParameter("dateConseil"));
			System.out.println("input de la jsp : " + titre + nbHabitants+ contenue + dateConseil);
			
			PageManager pageManager = BLLFactory.getPageBLL();
			
			try
			{
				pageManager.EnregistrementNouvellePage(titre, nbHabitants, contenue, dateConseil);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				listeCodesErreur.add((CodesResultatServlets.ENVOI_NOUVELLE_PAGE_A_PAGE_MANAGER_ERREUR));
			}
			RequestDispatcher rd = request.getRequestDispatcher("Accueil");
			rd.forward(request, response);
		}
	}	
		

}
