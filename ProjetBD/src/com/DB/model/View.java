/**
 * 
 */
package com.DB.model;

/**
 * @author Bruce GONG
 *
 */
public class View {
	private String ViewName;

	public View(String viewName) {
		super();
		ViewName = viewName;
	}

	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return ViewName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ViewName == null) ? 0 : ViewName.hashCode());
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
		View other = (View) obj;
		if (ViewName == null) {
			if (other.ViewName != null)
				return false;
		} else if (!ViewName.equals(other.ViewName))
			return false;
		return true;
	}
	
}
