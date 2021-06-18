package org.gotham.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gotham.bll.BLLFactory;
import org.gotham.bll.UtilisateurManager;
import org.gotham.bo.Utilisateur;
import org.gotham.businessException.BusinessException;

/**
 * Servlet implementation class Connexion
 */
@WebServlet(description = "Servlet redirigeant vers la page de connexion pour identification", urlPatterns = { "/Connexion" })
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
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


		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		String login=lireParametreLogin(request, listeCodesErreur);
		String password=lireParametrePassword(request, listeCodesErreur);
		
		UtilisateurManager utilisateurManager=BLLFactory.getUtilisateurBLL();
		Utilisateur utilisateur=null;
		
		if(listeCodesErreur.size()>0)
		{
			System.out.println("err" + listeCodesErreur.size());
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
			rd.forward(request, response);
		}
		else
		{
			try
			{
				utilisateur = utilisateurManager.testConnexion(login, password);
					if (utilisateur != null)
					{
						response.sendRedirect("/Gotham_City/Configuration");
					}
					else
					{
						listeCodesErreur.add(CodesResultatServlets.MOT_DE_PASSE_IDENTIFIANT_INCORRECT);
						request.setAttribute("listeCodesErreur", listeCodesErreur);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
					
						rd.forward(request, response);
					}
					
				
			
			
		}catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	

	private String lireParametrePassword(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String password;
		password = request.getParameter("password");
		if(password==null || password.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.MOT_DE_PASSE_OBLIGATOIRE);
		}
		return password;
	}

	private String lireParametreLogin(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String login;
		login = request.getParameter("login");
		if(login==null || login.trim().equals(""))
		{
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_OBLIGATOIRE);
		}
		return login;
	}

}
