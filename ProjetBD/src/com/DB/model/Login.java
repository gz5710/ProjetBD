package com.DB.model;

public class Login {
	private String LoginName;
	private String LoginPwd;
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return LoginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	/**
	 * @return the loginPwd
	 */
	public String getLoginPwd() {
		return LoginPwd;
	}
	/**
	 * @param loginPwd the loginPwd to set
	 */
	public void setLoginPwd(String loginPwd) {
		LoginPwd = loginPwd;
	}
	public Login(String loginName, String loginPwd) {
		super();
		LoginName = loginName;
		LoginPwd = loginPwd;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Login [LoginName=" + LoginName + ", LoginPwd=" + LoginPwd + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((LoginName == null) ? 0 : LoginName.hashCode());
		result = prime * result + ((LoginPwd == null) ? 0 : LoginPwd.hashCode());
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
		Login other = (Login) obj;
		if (LoginName == null) {
			if (other.LoginName != null)
				return false;
		} else if (!LoginName.equals(other.LoginName))
			return false;
		if (LoginPwd == null) {
			if (other.LoginPwd != null)
				return false;
		} else if (!LoginPwd.equals(other.LoginPwd))
			return false;
		return true;
	}
	
}
