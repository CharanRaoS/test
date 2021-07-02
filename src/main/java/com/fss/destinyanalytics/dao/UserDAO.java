package com.fss.destinyanalytics.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fss.destinyanalytics.models.BookMarks;
import com.fss.destinyanalytics.models.UserDetails;

@Component
public interface UserDAO {

	public UserDetails retrieveUserRole(UserDetails userDetails);
	
	public String getreportId(String reportName);

	public void insertBookMarksDetails(BookMarks bookMarks);

	public List<BookMarks> getBookMarkList(UserDetails userDetails);

	public void deleteBookMarksDetails(BookMarks bookMarks);
}
