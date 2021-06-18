package org.gotham.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.gotham.bo.Utilisateur;
import org.gotham.businessException.BusinessException;

public class UtilisateurDaoJdbcImpl implements UtilisateurDAO {
	
	private static final String TEST_CONNEXION_UTILISATEUR="SELECT id,login,password FROM UTILISATEUR WHERE login=? AND password=?;";
	private static final String SELECT_UTILISATEUR_BY_LOGIN="SELECT ut.login, ut.password FROM UTILISATEUR ut WHERE ut.login=?;";

	@Override
	public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Utilisateur testConnexion(String login, String password) throws BusinessException {
		
		Utilisateur utilisateur=null;
		BusinessException businessException = new BusinessException();
		System.out.println(login);
		System.out.println(password);
		System.out.println("Je suis dans le DAO test de connexion");

		if(login==null || password==null) {
			System.out.println("je suis dans test connexion et le login et ou le password est null");
			System.out.println(login);
			System.out.println(password);
			
		businessException.ajouterErreur(CodesResultatDAL.TEST_CONNEXION_NULL);
		throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(TEST_CONNEXION_UTILISATEUR);
				System.out.println("j'ai fais le pstmt");
				
				pstmt.setString(1, login);
				System.out.println("j'ai fais un set");
				pstmt.setString(2,password);
				
				System.out.println("j'ai fais les set");
				pstmt.executeQuery();
				System.out.println("J'ai passé l'execute");
				rs = pstmt.executeQuery();
				System.out.println("j'ai passé le rs");
				if(rs.next()) {
					System.out.println("succès de connexion utilisateur");
					
					utilisateur=new Utilisateur();
					
					utilisateur.setLogin(rs.getString(1));
					utilisateur.setPassword(rs.getString(2));
				}
				else {
					System.out.println("Erreur de connexion utilisateur");
				}
				rs.close();
				pstmt.close();
				cnx.commit();
			}
			catch(Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			throw businessException;
		}
			
		return utilisateur;
	}

	@Override
	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur selectUtilisateurByLogin(String login) throws BusinessException {
		Utilisateur utilisateur=null;
		BusinessException businessException = new BusinessException();
		System.out.println("Je suis dans le DAO test de connexion");
		if(login==null)
		{
			System.out.println("Je suis dansle selectUtilisateurByLogin ");
			businessException.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_PSEUDO_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_LOGIN);
				
				pstmt.setString(1, login);
				
				pstmt.executeQuery();
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					utilisateur= new Utilisateur();
					
					utilisateur.setLogin(rs.getString(1));
					utilisateur.setPassword(rs.getString(2));
					
					
				}
				else
				{
					System.out.println("Erreur de connexion utilisateur");
				}
				rs.close();
				pstmt.close();
				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_PSEUDO_NULL);
			throw businessException;
		}
		return utilisateur;
	}

	@Override
	public void modifierUtilisateur(Utilisateur utilisateurString, String motDePasseActuel) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub

	}

}
