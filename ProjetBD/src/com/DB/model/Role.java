/**
 * 
 */
package com.DB.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruce GONG
 *
 */
public class Role {
	private String Role;
	private Date createTime;
	private List<View> views = new ArrayList<View>();
	/**
	 * @return the views
	 */
	public List<View> getViews() {
		return views;
	}

	/**
	 * @param views the views to set
	 */
	public void setViews(List<View> views) {
		this.views = views;
	}
	
	public void addView(View view)
	{
		this.views.add(view);
	}
	
	public void removeView(View view)
	{
		this.views.remove(view);
	}

	/**
	 * @return the creatTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param creatTime the creatTime to set
	 */
	public void setCreateTime(Date creatTime) {
		this.createTime = creatTime;
	}

	public Role(String role) {
		super();
		Role = role;
	}

	public Role() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return Role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		Role = role;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Role == null) ? 0 : Role.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (Role == null) {
			if (other.Role != null)
				return false;
		} else if (!Role.equals(other.Role))
			return false;
		return true;
	}
	
}
