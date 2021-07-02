package com.fss.destinyanalytics.services;

import java.util.List;

import com.fss.destinyanalytics.models.BookMarks;
import com.fss.destinyanalytics.models.UserDetails;

public interface UserService {

	public UserDetails getUserRole(UserDetails userDetails);
	
	public String getreportId(String reportName);
	
	public void insertBookMarksDetails(BookMarks bookMarks);
	
	public List<BookMarks> getBookMarkList(UserDetails userDetails);
	
	public void deleteBookMarksDetails(BookMarks bookMarks);
}
