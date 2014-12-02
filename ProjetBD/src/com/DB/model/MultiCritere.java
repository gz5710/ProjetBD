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
public class MultiCritere {
	private String Type_File;
	private String keyWords;
	private Date From_Date_Pub;
	private Date To_Date_Pub;
	private String Author;
	public MultiCritere() {
		super();
		// TODO Auto-generated constructor stub
		this.Type_File = null;
		this.keyWords = null;
		this.From_Date_Pub = null;
		this.To_Date_Pub = null;
		this.Author = null;
	}
	public MultiCritere(String type_File, String keyWords, Date from_Date_Pub, Date to_Date_Pub, String author) {
		super();
		Type_File = type_File;
		this.keyWords = keyWords;
		From_Date_Pub = from_Date_Pub;
		To_Date_Pub = to_Date_Pub;
		Author = author;
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
		if(!type_File.trim().equals("")){
			Type_File = type_File.trim();
		}
	}

	/**
	 * @return the keyWords
	 */
	public String getKeyWords() {
		return keyWords;
	}
	/**
	 * @param keyWords the keyWords to set
	 */
	public void setKeyWords(String keyWords) {
		if(!keyWords.trim().equals("")){
			this.keyWords = keyWords.trim();
		}
	}
	/**
	 * @return the from_Date_Pub
	 */
	public Date getFrom_Date_Pub() {
		return From_Date_Pub;
	}
	/**
	 * @param from_Date_Pub the from_Date_Pub to set
	 */
	public void setFrom_Date_Pub(Date from_Date_Pub) {
		From_Date_Pub = from_Date_Pub;
	}
	/**
	 * @return the to_Date_Pub
	 */
	public Date getTo_Date_Pub() {
		return To_Date_Pub;
	}
	/**
	 * @param to_Date_Pub the to_Date_Pub to set
	 */
	public void setTo_Date_Pub(Date to_Date_Pub) {
		To_Date_Pub = to_Date_Pub;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return Author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		if(!author.trim().equals("")){
			Author = author.trim();
		}
	}
	
	public String buildSQLWhere()
	{
		String where = "";
		List<String> conditions = new ArrayList<String>();
		//File Type
		if(!this.Type_File.equals("ALL"))
		{
			String fileType = " Type_File = '" + this.Type_File + "' ";
			conditions.add(fileType);
		}
		//Keywords
		if(this.keyWords != null && !this.keyWords.trim().equals(""))
		{
			String keywords = " ( Descriptions like '%";
			keywords += this.keyWords.replaceAll(",", "%' or Descriptions like '%");
			keywords += "%' ) ";
			conditions.add(keywords);
		}
		//Date of publish
		if(this.From_Date_Pub != null && this.To_Date_Pub != null)
		{
			String dateOfPub = " ( Date_Pub between '" + 
					this.From_Date_Pub.toString()+ "' and '" +
					this.To_Date_Pub.toString()+ "' ) ";
			conditions.add(dateOfPub);			
		}
		//Author
		if(!this.Author.equals("ALL"))
		{
			String author = " Autheur = '" + this.Author + "' ";
			conditions.add(author);
		}
		//form the where code
		for(int i=0; i<conditions.size(); i++)
		{
			String c = conditions.get(i);
			if(i==0){
				where += " WHERE " + c;
			}else{
				where += " AND " + c;
			}
		}
		System.out.println(where);
		return where;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MultiCritere [Type_File=" + Type_File + ", keyWords=" + keyWords + ", From_Date_Pub=" + From_Date_Pub
				+ ", To_Date_Pub=" + To_Date_Pub + ", Author=" + Author + "]";
	}
	
}
