package com.fss.destinyanalytics.controller;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fss.destinyanalytics.config.ConfigProperties;
import com.fss.destinyanalytics.models.BookMarks;
import com.fss.destinyanalytics.models.EmbedConfig;
import com.fss.destinyanalytics.models.UserDetails;
import com.fss.destinyanalytics.services.PowerBIService;
import com.fss.destinyanalytics.services.UserService;
import com.fss.destinyanalytics.services.AzureADService;

@Controller
public class UserController {
	
	static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigProperties properties;
	
	/**
	 * login method to call login screen
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * logout method to redirect to login page
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(ModelAndView modelAndView, HttpServletRequest request) {
		modelAndView.setViewName("login");
		request.getSession().invalidate();
		return modelAndView;
	}
	
	/**
	 * submit method to validate it a valid user or not, and bring user details
	 * from data base
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/home")
	public ModelAndView submit(ModelAndView modelAndView, @RequestParam("userName") String userName, @RequestParam("password") String password,  HttpServletRequest request) {
		UserDetails users = new UserDetails();
		users.setUserName(userName);
		users.setPassword(password);
		List bookmarksList;
		try {
			users = userService.getUserRole(users);
			if (users.getRole() != null && !users.getRole().isEmpty()) {
				bookmarksList = userService.getBookMarkList(users);
				modelAndView.setViewName("home");
				modelAndView.addObject("bookmarksList", bookmarksList);
				modelAndView.addObject("userDetails", users);
				request.getSession().setAttribute("userDetails", users);
			} else {
				request.getSession().setAttribute("Message", "Invalid Credentials");
				modelAndView.setViewName("login");
				modelAndView.addObject("userDetails", users);
				return modelAndView;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	/**
	 * report method to display reports
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/report")
	public ModelAndView report(ModelAndView modelAndView, HttpServletRequest request, String reportName, String bookMarkState, String bookMarkName) {
		modelAndView.setViewName("report");
		modelAndView.addObject("reportName", reportName);
		modelAndView.addObject("bookMarkState", bookMarkState);
		modelAndView.addObject("bookMarkName", bookMarkName);
		return modelAndView;
	}
	
	/**
	 * saveReport method to save the state of reports
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveReport")
	@ResponseBody
	public JSONObject saveReport(ModelAndView modelAndView, HttpServletRequest request, String json) {
		JSONObject jsonObject = new JSONObject(json);
		BookMarks bookMarks = new BookMarks();
		
		long timestamp = System.currentTimeMillis();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date = new Date(timestamp);
		
		bookMarks.setBookMarkState(jsonObject.get("state").toString());
		bookMarks.setBookMarkName(jsonObject.get("displayName").toString());
		bookMarks.setUserName(jsonObject.get("userId").toString());
		bookMarks.setBookMarkDate(dateFormat.format(date));
		
		userService.insertBookMarksDetails(bookMarks);
		
		modelAndView.addObject("userDetails", request.getSession().getAttribute("userDetails"));
		
		return jsonObject;
	}
	
	/**
	 * deleteBookMark method to save the state of reports
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteBookMark")
	public ModelAndView deleteBookMark(ModelAndView modelAndView,String userName, String bookMarkName, String bookMarkDate, HttpServletRequest req) {
		BookMarks bookMarks = new BookMarks();
		bookMarks.setUserName(userName);
		bookMarks.setBookMarkName(bookMarkName);
		bookMarks.setBookMarkDate(bookMarkDate);
		
		userService.deleteBookMarksDetails(bookMarks);
		
		modelAndView.addObject("userDetails", req.getSession().getAttribute("userDetails"));
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	/**
	 * redirect method redirect to home page from reports
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/redirect")
	public ModelAndView redirect(ModelAndView modelAndView, HttpServletRequest request) {
		UserDetails users = (UserDetails) request.getSession().getAttribute("userDetails");
		List bookmarksList = userService.getBookMarkList(users);
		modelAndView.addObject("bookmarksList", bookmarksList);
		modelAndView.addObject("userDetails", users);
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	/**
	 * Embedding details controller
	 * 
	 * @return ResponseEntity<String> body contains the JSON object with
	 *         embedUrl and embedToken
	 * @throws JsonProcessingException
	 * @throws JsonMappingException
	 */
	@RequestMapping(path = "/getembedinfo")
	@ResponseBody
	public ResponseEntity<String> embedInfoController(String reportName)
			throws JsonMappingException, JsonProcessingException {

		// Get access token
		String accessToken;
		String reportId = null;
		try {
			try {
				try {
						reportId = userService.getreportId(reportName);
					} catch (Exception e) {
					e.printStackTrace();
				}
				accessToken = AzureADService.getAccessToken(properties);
			} catch (ExecutionException | MalformedURLException | RuntimeException ex) {
				// Log error message
				logger.error(ex.getMessage());

				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());

			} catch (InterruptedException interruptedEx) {
				// Log error message
				logger.error(interruptedEx.getMessage());

				Thread.currentThread().interrupt();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(interruptedEx.getMessage());
			}

			// Get required values for embedding the report
			// Get report details
			EmbedConfig reportEmbedConfig = PowerBIService.getEmbedConfig(accessToken,
					properties.getWorkspaceId(), reportId);

			// Convert ArrayList of EmbedReport objects to JSON Array
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < reportEmbedConfig.embedReports.size(); i++) {
				jsonArray.put(reportEmbedConfig.embedReports.get(i).getJSONObject());
			}

			// Return JSON response in string
			JSONObject responseObj = new JSONObject();
			responseObj.put("embedToken", reportEmbedConfig.embedToken.token);
			responseObj.put("embedReports", jsonArray);
			responseObj.put("tokenExpiry", reportEmbedConfig.embedToken.expiration);

			String response = responseObj.toString();
			return ResponseEntity.ok(response);

		} catch (HttpClientErrorException hcex) {
			// Build the error message
			StringBuilder errMsgStringBuilder = new StringBuilder("Error: ");
			errMsgStringBuilder.append(hcex.getMessage());

			// Get Request Id
			HttpHeaders header = hcex.getResponseHeaders();
			List<String> requestIds = header.get("requestId");
			if (requestIds != null) {
				for (String requestId : requestIds) {
					errMsgStringBuilder.append("\nRequest Id: ");
					errMsgStringBuilder.append(requestId);
				}
			}

			// Error message string to be returned
			String errMsg = errMsgStringBuilder.toString();

			// Log error message
			logger.error(errMsg);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errMsg);

		} catch (RuntimeException rex) {
			// Log error message
			logger.error(rex.getMessage());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rex.getMessage());
		}
	}
}
