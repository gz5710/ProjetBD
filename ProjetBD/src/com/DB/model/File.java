/**
 * 
 */
package com.DB.model;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

/**
 * @author Bruce GONG
 *
 */
public class File implements Comparator<File> {
	private int Id;
	private String Nom;
	private String Chemin;
	private String Autheur;
	private Date Date_Pub;
	private String Description;
	private View Tag_View;
	private int Vu;
	private String Type_File;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "File [Id=" + Id + ", Nom=" + Nom + ", Chemin=" + Chemin + ", Autheur=" + Autheur + ", Date_Pub="
				+ Date_Pub + ", Description=" + Description + ", Tag_View=" + Tag_View.getViewName() + ", Vu=" + Vu + ", Type_File="
				+ Type_File + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Chemin == null) ? 0 : Chemin.hashCode());
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
		File other = (File) obj;
		if (Chemin == null) {
			if (other.Chemin != null)
				return false;
		} else if (!Chemin.equals(other.Chemin))
			return false;
		return true;
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
	 * @return the nom
	 */
	public String getNom() {
		return Nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		Nom = nom;
	}
	/**
	 * @return the chemin
	 */
	public String getChemin() {
		return Chemin;
	}
	/**
	 * @param chemin the chemin to set
	 */
	public void setChemin(String chemin) {
		Chemin = chemin;
	}
	/**
	 * @return the autheur
	 */
	public String getAutheur() {
		return Autheur;
	}
	/**
	 * @param autheur the autheur to set
	 */
	public void setAutheur(String autheur) {
		Autheur = autheur;
	}
	/**
	 * @return the date_Pub
	 */
	public Date getDate_Pub() {
		return Date_Pub;
	}
	/**
	 * @param date_Pub the date_Pub to set
	 */
	public void setDate_Pub(Date date_Pub) {
		Date_Pub = date_Pub;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}
	/**
	 * @return the tag_View
	 */
	public View getTag_View() {
		return Tag_View;
	}
	/**
	 * @param tag_View the tag_View to set
	 */
	public void setTag_View(View tag_View) {
		Tag_View = tag_View;
	}
	/**
	 * @return the vu
	 */
	public int getVu() {
		return Vu;
	}
	/**
	 * @param vu the vu to set
	 */
	public void setVu(int vu) {
		Vu = vu;
	}
	/**
	 * @return the type_File
	 */
	public String getType_File() {
		return Type_File;
	}
	/**
	 * @param type_File the type_File to set
	 */
	public void setType_File(String type_File) {
		Type_File = type_File;
	}
	public File(int id, String nom, String chemin, String autheur, Date date_Pub, String description, View tag_View,
			int vu, String type_File) {
		super();
		Id = id;
		Nom = nom;
		Chemin = chemin;
		Autheur = autheur;
		Date_Pub = date_Pub;
		Description = description;
		Tag_View = tag_View;
		Vu = vu;
		Type_File = type_File;
	}
	public File() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int compare(File o1, File o2) {
		// TODO Auto-generated method stub
		return o1.getVu() - o2.getVu();
	}
	
	public void readOneMoreTime(){
		this.Vu++;
	}
	
}
