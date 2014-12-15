/**
 * 
 */
package com.DB.dao;

import java.util.List;
import java.util.Map;

import com.DB.model.File;
import com.DB.model.MultiCritere;
import com.DB.model.View;

/**
 * @author Bruce GONG
 *
 */
public interface FileDao {
	public boolean addFileRecord(File file);
	public boolean removeFileRecord(int id);
	public boolean updateFile(File file);
	public boolean deleteFile(String chemin);
	public File getFileById(int id);
	public File getFileInViews(List<View> views, int id);
	public List<File> getAllFiles();
	public List<File> getAllFilesInView(View view);
	public List<File> searchFilesByMultiCritere(MultiCritere mc);
	public List<String> getAllFileTypes();
	public List<String> getAllAuthors();
	public Map<String, Integer> getChartFileType();
	public Map<String, Integer> getChartFileGroup();
	public Map<String, Integer> getChartFileView();
}
