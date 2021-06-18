package org.gotham.bll;

import org.gotham.bo.Page;
import org.gotham.businessException.BusinessException;

public interface PageBLL {
	
	public Page selectPageById(int noPage) throws BusinessException;

}
