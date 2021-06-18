package org.gotham.bll;

import org.gotham.bo.Utilisateur;
import org.gotham.businessException.BusinessException;
import org.gotham.dal.DAOFactory;
import org.gotham.dal.UtilisateurDAO;

public class UtilisateurManager implements UtilisateurBLL {
	
	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager()
	{
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	@Override
	public Utilisateur testConnexion(String login, String password) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerLogin(login, businessException);
		return this.utilisateurDAO.testConnexion(login, password);
	}

	private void validerLogin(String login, BusinessException businessException) {
		if(login==null || login.trim().length()>30)
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_LOGIN_ERREUR);
			System.out.println("J'ai une exception login");
		}
		
	}
	
	private void validerPassword(String password, String passwordConfirm, BusinessException businessException)
	{
		/*Test si le password et le passwordConfirm sont identiques*/
		if(!password.equals(passwordConfirm))
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_PASSWORD_IDENTIQUE_ERREUR);
			System.out.println("j'ai une exception de password identique");
		}
		if(password==null || password.trim().length()>30)
		{
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_PASSWORD_ERREUR);
			System.out.println("J'ai une exception password");
		}
	}

	@Override
	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		return this.utilisateurDAO.selectUtilisateurById(noUtilisateur);
	}

	@Override
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		 this.utilisateurDAO.supprimerUtilisateur(noUtilisateur);

	}

	@Override
	public void modifierUtilisateur(String login, String password) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Utilisateur selectUtilisateurByLogin(String login) throws BusinessException {
		// TODO Auto-generated method stub
		return this.utilisateurDAO.selectUtilisateurByLogin(login);
	}

}
