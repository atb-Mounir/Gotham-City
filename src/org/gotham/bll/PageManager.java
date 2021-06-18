package org.gotham.bll;

import java.time.LocalDate;

import org.gotham.bo.Page;
import org.gotham.businessException.BusinessException;
import org.gotham.dal.DAOFactory;
import org.gotham.dal.PageDAO;

public class PageManager implements PageBLL {
	
	private PageDAO pageDAO;
	
	public PageManager()
	{
		this.pageDAO = DAOFactory.getPageDAO();
	}

	@Override
	public Page selectPageById(int noPage) throws BusinessException {
		
		return this.pageDAO.selectPageById(noPage);
	}
	
	private void validerTitre(String titre, BusinessException businessException)
	{
		if(titre==null)
		{
			businessException.ajouterErreur(CodeResultatBLL.PAGE_TITRE_ERREUR);
		}
	}
	
	private void validerNombreHbts(int nbHabitants, BusinessException businessException)
	{
		if(nbHabitants==0)
		{
			businessException.ajouterErreur(CodeResultatBLL.PAGE_TITRE_ERREUR);
		}
	}
	
	private void validerContenu(String contenu, BusinessException businessException)
	{
		if(contenu==null)
		{
			businessException.ajouterErreur(CodeResultatBLL.PAGE_CONTENU_ERREUR);
		}
	}
	
	private void validerDate(LocalDate dateConseil, BusinessException businessException)
	{
		if(dateConseil==null)
		{
			businessException.ajouterErreur(CodeResultatBLL.PAGE_DATE_CONSEIL_ERREUR);
		}
	}
	
	public void EnregistrementNouvellePage(String titre, int nbHabitants, String contenu, LocalDate dateConseil)
	{
		BusinessException businessException = new BusinessException();
		
		this.validerTitre(titre, businessException);
		this.validerContenu(contenu, businessException);
		this.validerNombreHbts(nbHabitants, businessException);
		this.validerDate(dateConseil, businessException);
		
		System.out.println("Je suis dans page Manager les tests d'erreur sont passés");
		Page newPage = new Page();
		//attribution des paramètres obtenus de la JSP aux nouveaux objets
		newPage.setTitre(titre);
		newPage.setNbHabitants(nbHabitants);
		newPage.setContenu(contenu);
		newPage.setDateConseil(dateConseil);
		
		try {
			System.out.println("je suis dans le try et j'insert l'objet dans la BDD");
			pageDAO.insertNouvellePage(newPage);
			System.out.println("Dans le Test Manager on appelle la méthode de page DAO");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SupprimerCettePage(int noPage) {
		BusinessException businessException = new BusinessException();
		
		try
		{
			pageDAO.supprimerLaPage(noPage);
			
		}
		catch (BusinessException e)
		{
			e.printStackTrace();
		}
	}

//	public void modifierPage(int idPage, String titre, st nbHabitants, String contenue, LocalDate dateConseil) {
//
//		BusinessException businessException = new  BusinessException();
//		
//		this.validerTitre(titre, businessException);
//		this.validerNombreHbts(nbHabitants, businessException);
//		this.validerContenu(contenue, businessException);
//		this.validerDate(dateConseil, businessException);
//		
//		Page page = null;
//		
//		if(!businessException.hasErreurs())
//		{
//			System.out.println("jesuis dans le pageManager avec test erreur");
//			if(titre==null )
//			{
//				page =new Page ( titre, nbHabitants, contenue, dateConseil,idPage);
//				page.setNumeroPage(idPage);
//			}
//			System.out.println("je crée Page DAO");
//			this.pageDAO.modifierPage(page);
//		}
//		else
//		{
//			System.out.println("je suis dans l'exception de modifier pageManager");
//			try {
//				throw businessException;
//			} catch (BusinessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		
//		
//	}

}
