package org.gotham.dal;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.gotham.bo.Page;
import org.gotham.businessException.BusinessException;

public class PageDAOJdbcImpl implements PageDAO {
	
	private static final String DETAIL_PAGE = "SELECT titre, nb_habitant, contenu, date_conseil FROM Page \r\n" + 
			"WHERE id =(select Max(id) from page );";
	
	private static final String INSERT_PAGE = "INSERT INTO PAGE (titre, nb_habitant, contenu, date_conseil) VALUES (?,?,?,?)";

	private static final String DELETE_PAGE = "DELETE FROM PAGE WHERE id=?";
	
	private static final String UPDATE_PAGE = "UPDATE PAGE SET titre=?, nombre_hbts=?, contenu=?, date_conseil=? WHERE id=?";

	
	@Override
	public Page selectPageById(int idPage) throws BusinessException {
		
	Page page = null;
	BusinessException businessException = new BusinessException();
	System.out.println("je suis entrer dans la methode selectPageById");
	
	try (Connection cnx = ConnectionProvider.getConnection())
	{
		System.out.println("j'ai effectuer la connexion avec connectionProvider");
		try
		{
		cnx.setAutoCommit(false);
		PreparedStatement pstmt;
		ResultSet rs;
		pstmt = cnx.prepareStatement(DETAIL_PAGE);
		pstmt.executeQuery();
		rs = pstmt.executeQuery();
		if(rs.next())
		{
			page=new Page();
			
			page.setTitre(rs.getString(1));
			page.setNbHabitants(rs.getInt(2));
			page.setContenu(rs.getString(3));
			page.setDateConseil((rs.getDate(4)).toLocalDate());
		}
		else
		{
			System.out.println("Erreur de connexion utilisateur");
		}
		rs.close();
		pstmt.close();
		cnx.commit();
		
	} 
	catch (SQLException e)
	{
		e.printStackTrace();
		cnx.rollback();
		throw businessException;
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		businessException.ajouterErreur(CodesResultatDAL.SELECT_PAGE_BY_ID_NULL);
		throw businessException;
	}

	return page;
	}


	@Override
	public void insertNouvellePage(Page newPage) throws BusinessException {
		System.out.println("Je suis dans insertNouvellePage dans la jdbc");
		if (newPage==null)
		{
			System.out.println("newPage==null, dans insertNouvellePage dans la jdbc");
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			cnx.setAutoCommit(false);
			try
			{
				PreparedStatement pstmt= cnx.prepareStatement(INSERT_PAGE, PreparedStatement.RETURN_GENERATED_KEYS);
				System.out.println("J'ai fais la connexion dans la jdbc");
				System.out.println("je fais un pstmt INSERT PAGE");
				pstmt.setString(1, newPage.getTitre());
				System.out.println(newPage.getTitre());
				pstmt.setInt(2, newPage.getNbHabitants());
				System.out.println(newPage.getNbHabitants());
				pstmt.setString(3, newPage.getContenu());
				System.out.println(newPage.getContenu());
				pstmt.setDate(4, java.sql.Date.valueOf(newPage.getDateConseil()));
				System.out.println(newPage.getDateConseil());
				pstmt.executeUpdate();
				System.out.println("Dans le pstmt INSERT PAGE récupération de la PK");
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next())
				{
					newPage.setNumeroPage(rs.getInt(1));
				}
				System.out.println("récupération de la PK");
				pstmt.close();
				cnx.commit();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
		}

	}

	@Override
	public void supprimerLaPage(int noPage) throws BusinessException {

		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_PAGE);)
		{
			cnx.setAutoCommit(false);
			try
			{
				pstmt.setInt(1, noPage);
			}
			
		catch (SQLException e) {
			e.printStackTrace();
			cnx.rollback();
			throw e;
		}
		cnx.commit();
		try(PreparedStatement psmt = cnx.prepareStatement(DELETE_PAGE);)
		{
			cnx.setAutoCommit(false);
			try 
					{
				pstmt.setInt(1, noPage);
					}
		
		catch (Exception e)
		{
			e.printStackTrace();
			cnx.rollback();
			throw e;
		}
		cnx.commit();
		}
	
}catch (Exception e)
{
	e.printStackTrace();
	BusinessException businessexception = new BusinessException();
	businessexception.ajouterErreur(CodesResultatDAL.DELETE_NOUVELLE_PAGE);
}
	}


	@Override
	public void modifierPage(Page page) {
		
		BusinessException businessException = new BusinessException();
		Page pageAmodifier=null;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
			cnx.setAutoCommit(false);
			PreparedStatement pstmt;
			pstmt = cnx.prepareStatement(UPDATE_PAGE);
			pstmt.executeQuery();
			
			pstmt.setString(1, pageAmodifier.getTitre());
			pstmt.setInt(2, pageAmodifier.getNbHabitants());
			pstmt.setString(3, pageAmodifier.getContenu());
			pstmt.setDate(4, java.sql.Date.valueOf(pageAmodifier.getDateConseil()));
			pstmt.setInt(5, pageAmodifier.getNumeroPage());
			
			System.out.println("j'ai passé le rs de update");
			
			pstmt.executeUpdate();
			
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
			businessException.ajouterErreur(CodesResultatDAL.MODIFIER_NOUVELLE_PAGE_NULL);
			try {
				throw businessException;
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

