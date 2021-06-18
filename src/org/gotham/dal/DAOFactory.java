package org.gotham.dal;

public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDaoJdbcImpl();
	}
	
	public static PageDAO getPageDAO()
	{
		return new PageDAOJdbcImpl();
	}

}
