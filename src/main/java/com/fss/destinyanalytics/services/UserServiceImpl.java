package com.fss.destinyanalytics.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fss.destinyanalytics.dao.UserDAO;
import com.fss.destinyanalytics.models.BookMarks;
import com.fss.destinyanalytics.models.UserDetails;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails getUserRole(UserDetails userDetails) {
		UserDetails user = new UserDetails();
		try {
			user = userDAO.retrieveUserRole(userDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;

	}

	@Override
	public String getreportId(String reportName) {
		String reportId = null;
		try {
			reportId = userDAO.getreportId(reportName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportId;
	}

	@Override
	public void insertBookMarksDetails(BookMarks bookMarks) {
		userDAO.insertBookMarksDetails(bookMarks);
	}

	@Override
	public List<BookMarks> getBookMarkList(UserDetails userDetails) {
		 List<BookMarks> bookMarkList = userDAO.getBookMarkList(userDetails);
		return bookMarkList;
	}

	@Override
	public void deleteBookMarksDetails(BookMarks bookMarks) {
		userDAO.deleteBookMarksDetails(bookMarks);
	}

}
