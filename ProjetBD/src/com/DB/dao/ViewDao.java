/**
 * 
 */
package com.DB.dao;

import java.util.List;

import com.DB.model.Role;
import com.DB.model.View;

/**
 * @author Bruce GONG
 *
 */
public interface ViewDao {
	public List<Role> getRolesWithView(View view);
	public List<View> getAllViews();
	public boolean deleteView(String viewname);
	public boolean createView(String viewname);
}
