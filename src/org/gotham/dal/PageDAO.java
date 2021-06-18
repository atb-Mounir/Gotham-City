package org.gotham.dal;

import org.gotham.bo.Page;
import org.gotham.businessException.BusinessException;

public interface PageDAO {
	
	/**
	 * @throws BusinessException 
	 * 
	 */
	
	public Page selectPageById (int idPage) throws BusinessException ;
	public void insertNouvellePage (Page newPage) throws BusinessException;
	public void supprimerLaPage(int noPage) throws BusinessException;
	public void modifierPage(Page page);
	

}
