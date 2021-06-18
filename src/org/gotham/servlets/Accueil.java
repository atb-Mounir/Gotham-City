package org.gotham.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gotham.bll.BLLFactory;
import org.gotham.bll.PageManager;
import org.gotham.bo.Page;
import org.gotham.businessException.BusinessException;

/**
 * Servlet implementation class Accueil
 */
@WebServlet(description = "Servlet dirigeant vers la jsp accueil de l'application", urlPatterns = { "/Accueil" })
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	//private String titre = "BIENVENU A GOTHAM-CITY";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageManager pageManager = BLLFactory.getPageBLL();
		
		/**
		 * Plus tard je récupère le no_article envoyé par la jspAccueil qui veut afficher le detail de la page par défaut;
		 * int no_article = Integer.parseInt(request.getParameter("no_article"));
		 * 
		 */
		//pour le moment la page sera la numero 1 de la bdd
		
		
		try {
			Page page;
			int no_page = 1;
			page = pageManager.selectPageById(no_page);
			request.setAttribute("page", page);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");

		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
