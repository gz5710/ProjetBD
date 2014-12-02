/**
 * 
 */
package com.DB.model;

import java.sql.Date;



/**
 * @author Bruce GONG
 *
 */
public class User {

	private int Id;
	private String Username;
	private String Password;
	private String Titre;
	private String Email;
	private Date DOB;
	private Date Date_Inscription;
	private Date Date_Dernier_Acces;
	private boolean SiPermis;
	/**
	 * @return the titre
	 */
	public String getTitre() {
		return Titre;
	}
	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		Titre = titre;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * @return the dOB
	 */
	public Date getDOB() {
		return DOB;
	}
	/**
	 * @param dOB the dOB to set
	 */
	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	private Login Login;
	
	/**
	 * @return the login
	 */
	public Login getLogin() {
		return Login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		Login = login;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return Username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		Username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}
	/**
	 * @return the date_Inscription
	 */
	public Date getDate_Inscription() {
		return Date_Inscription;
	}
	/**
	 * @param date_Inscription the date_Inscription to set
	 */
	public void setDate_Inscription(Date date_Inscription) {
		Date_Inscription = date_Inscription;
	}
	/**
	 * @return the date_Dernier_Acces
	 */
	public Date getDate_Dernier_Acces() {
		return Date_Dernier_Acces;
	}
	/**
	 * @param date_Dernier_Acces the date_Dernier_Acces to set
	 */
	public void setDate_Dernier_Acces(Date date_Dernier_Acces) {
		Date_Dernier_Acces = date_Dernier_Acces;
	}
	
	public boolean isSiPermis() {
		return SiPermis;
	}
	public void setSiPermis(boolean siPermis) {
		SiPermis = siPermis;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [Id=" + Id + ", Username=" + Username + ", Password=" + Password + ", Titre=" + Titre + ", Email="
				+ Email + ", DOB=" + DOB + ", Date_Inscription=" + Date_Inscription + ", Date_Dernier_Acces="
				+ Date_Dernier_Acces + ", SiPermis=" + SiPermis + ", Login=" + Login + "]";
	}
	
}
