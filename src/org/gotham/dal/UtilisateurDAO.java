package org.gotham.dal;

import org.gotham.bo.Utilisateur;
import org.gotham.businessException.BusinessException;

public interface UtilisateurDAO {
	
	public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	public Utilisateur testConnexion (String login, String password) throws BusinessException;
	
	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException;
	
	public Utilisateur selectUtilisateurByLogin(String login) throws BusinessException;
	
	public void modifierUtilisateur(Utilisateur utilisateurString, String motDePasseActuel) throws BusinessException;
	
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException;

}
