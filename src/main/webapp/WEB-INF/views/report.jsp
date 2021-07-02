<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Follet Destiny</title>

<!-- css -->
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
<!-- fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,400;0,700;1,100;1,400;1,500&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
<form>
<input type= "hidden" id = "userName" name="userName" value = "${userDetails.getUserName()}"/>
<input type= "hidden" name="password" value = "${userDetails.getPassword()}"/>
<input type= "hidden" id = "reportName" name="userName" value = "${reportName}"/>
	<div class="header">
		<div class="row">
			<div class="header_bar">
				<div class="row">
					<div class="col-lg-2 col-sm-2 logo_box"></div>


					<div class="col-lg-10 col-sm-10 headerbar_menu">
						<ul>
							<a href="/redirect"><li><img src="images/back.png"
									class="">Back</li></a>
									<c:if test="${bookMarkState eq \"'N'\" }">
							<a href="#" data-toggle="modal" data-target="#myModal"><li><img
											src="images/bookmarks.png">Create Bookmarks</li></a>	
											</c:if>	
									<c:if test="${bookMarkState != \"'N'\" }">
							<a href="javascript:saveReport('Y', ${bookMarkName});"><li><img
											src="images/savebookmark.png">Save Bookmarks</li></a>
									</c:if>	
							<a href="#"><li><img src="images/user.png"
									class="">Testuser</li></a>
							<a href="/logout"><li><img src="images/logout.png"
									class="">Log Out</li></a>

						</ul>
					</div>


				</div>
			</div>

		</div>
	</div>



	<div class="bgsection">
		<div class="Report_section">
			<div class="row">



				<!-- Modal -->
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog bookmarks_popup">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">

								<h4 class="modal-title bookmarks_title">Create Bookmarks</h4>
								<br>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<div class="modal-body bookmarks_body">
								<label class="bookmark_name" for="fname">Enter a
									Bookmark Name</label> <input class="bookmark_typename" type="text"
									id="bookname" name="firstname" placeholder="Bookmark Name">
							</div>
							<div class="modal-footer">

								<button type="button" class="btn btn-default save_button"
									data-dismiss="modal" onclick = "saveReport('N', 'N');">Save</button>
								<button type="button" class="btn btn-default canceltwo_button"
									data-dismiss="modal">Cancel</button>
							</div>
						</div>

					</div>
				</div>
			</div>
			

			<section id="report-container" style="height: 100%;"></section>
			<!-- Used to display report embed error message  -->
			<section class="error-container m-5"></section>
			
		</div>
	</div>
		
		<!-- javascript -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
		<!--<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script> -->
		<script src="<c:url value="js/powerbi.min.js" />"></script>
		<script src="<c:url value="js/reports.js" />"></script>
		<script src="<c:url value="js/destiny.js" />"></script>
		<!-- javascript -->
		<script type="text/javascript">
		
			var reportName = ${reportName};
			var bookMarkState = ${bookMarkState};
			var bookMarkName = ${bookMarkName};
		</script>
	</form>
</body>

</html>