/**
 * 
 */
package com.DB.dao;

import java.util.List;

import com.DB.model.Role;
import com.DB.model.User;
import com.DB.model.View;

/**
 * @author Bruce GONG
 *
 */
public interface RoleDao {
	public Role getRole(String username);
	public List<Role> getAllRoles();
	public boolean addMember(Role role, String username);
	public boolean removeMember(Role role, String username);//should be used with addMember()
	public List<User> getUsersInRole(Role role);
	public List<View> getViewsWithRole(Role role);
	public boolean createRole(String rolename);
	public boolean updateViews4Role(Role role);
	public boolean removeViewsOfRole(Role role);
	public boolean deleteRole(String rolename);
}
