package com.fss.destinyanalytics.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.fss.destinyanalytics.models.BookMarks;
import com.fss.destinyanalytics.models.UserDetails;

@Component
public class UserDAOImpl implements UserDAO {

	@Autowired
	private JdbcTemplate jdbc;

	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public JdbcTemplate getJdbc(){
		return this.jdbc;
	}

	@SuppressWarnings("deprecation")
	@Override
	public UserDetails retrieveUserRole(UserDetails userDetails) {
		UserDetails users = new UserDetails();
		List memuItem;
		try {
			String str = "select userid,userpassword,userrole,isprimeuser from Users where UserID = ? and UserPassword=?";
			users = getJdbc().queryForObject(
			       str,
			        new Object[]{userDetails.getUserName(), userDetails.getPassword()},
			        new RowMapper<UserDetails>() {
			            public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			            	UserDetails userObj = new UserDetails();
			            	userObj.setUserName(rs.getString("userid"));
			            	userObj.setPassword(rs.getString("userpassword"));
			            	userObj.setRole(rs.getString("userrole"));
			            	userObj.setIsPrimeUser(rs.getString("isprimeuser"));
			                return userObj;
			            }
			        });

			if (users != null) {
				String query = "select c.menuname from users a, userrolemenuitems b, menuitems c where a.userid = b.userid and b.menuid = c.menuid and a.userid= ? and a.userpassword = ?";
				memuItem = getJdbc().queryForList(query,
						new Object[] { users.getUserName(), users.getPassword() }, String.class);
				if (memuItem != null && memuItem.size() != 0)
					users.setUsermenu(memuItem);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return users;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getreportId(String reportName) {
		String reportId = "";
		try {
			String str = "select ReportID from powerbireports where ReportName = ?";
			reportId = getJdbc().queryForObject(str, new Object[] {reportName}, String.class);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return reportId;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void insertBookMarksDetails(BookMarks bookMarks) {
		String selectQuery = "select count(*) from BookMarks where BookMarkName = ? and UserName = ?";
		int count = getJdbc().queryForObject(selectQuery, new Object[] { bookMarks.getBookMarkName(), bookMarks.getUserName()}, Integer.class);

		if (count > 0) {
			String query = "UPDATE BookMarks set BookMarkState = ?, CreatedDate = ? where BookMarkName = ? and UserName = ?";
			getJdbc().update(query,  bookMarks.getBookMarkState(), bookMarks.getBookMarkDate(), bookMarks.getBookMarkName(), bookMarks.getUserName());
		} else {
			String query = "INSERT INTO BookMarks (BookMarkName, BookMarkState, UserName, CreatedDate) VALUES (?, ?, ?, ?)";
			getJdbc().update(query, bookMarks.getBookMarkName(), bookMarks.getBookMarkState(), bookMarks.getUserName(),
					bookMarks.getBookMarkDate());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<BookMarks> getBookMarkList(UserDetails userDetails) {
		
		String query = "SELECT * FROM  BookMarks where UserName = ?";
		
		List<BookMarks> list = getJdbc().query(query,  new Object[] {userDetails.getUserName()} , new RowMapper<BookMarks>() {

			@Override
			public BookMarks mapRow(ResultSet rs, int rowNum) throws SQLException {
				BookMarks bookMarks = new BookMarks();

				bookMarks.setBookMarkName(rs.getString("BookMarkName"));
				bookMarks.setBookMarkState(rs.getString("BookMarkState"));
				bookMarks.setUserName(rs.getString("UserName"));
				bookMarks.setBookMarkDate(rs.getString("CreatedDate"));
				
				return bookMarks;
			}

		});
		return list;
	}

	@Override
	public void deleteBookMarksDetails(BookMarks bookMarks) {
		String query = "delete FROM  BookMarks where UserName = ? and BookMarkName = ? and CreatedDate = ?";
		getJdbc().update(query, new Object[] {bookMarks.getUserName(), bookMarks.getBookMarkName(), bookMarks.getBookMarkDate()});
	}
}
