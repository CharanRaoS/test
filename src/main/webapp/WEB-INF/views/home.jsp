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
	<div class="header">
			<div class="row">
				<div class="header_bar">
					<div class="row">
						<div class="col-lg-4 col-sm-4 logo_box">
							<ul>
								<li><img src="images/logo.png" class="">
								 		<c:if test="${userDetails.getIsPrimeUser() eq 'Y' }">
											Destiny Analytics
										</c:if>
										<c:if test="${userDetails.getIsPrimeUser() eq 'N' }">
											Enriched Analytics
										</c:if>
								</li>
							</ul>
						</div>


						<div class="col-lg-8 col-sm-8 headerbar_menu">
							<ul>
								<li><img src="images/user.png" class="">Test
									User</li>
								<li><img src="images/logout.png" class=""><a
									href="logout" style="text-decoration: none;"> <font
										style="color: #FFFFFF;">Log Out </font></a></li>
							</ul>
						</div>


					</div>
				</div>
			</div>
		</div>

		<div class="navbar">


			<nav class="navbar navbar-expand-sm navbar-dark">
			<ul class="navbar-nav" id="menu">

				<li class="nav-item"><a class="nav-link" href="#">Home</a></li>
				<c:forEach items="${userDetails.getUsermenu()}" var="menu">

					<c:if test="${menu!=null }">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbardrop"
							data-toggle="dropdown"> ${menu} </a> <c:if
								test="${menu eq 'Resource' }">
								<div class="dropdown-menu">
									<a class="dropdown-item"
										href="/report?reportName='DRM_OneToOne Devices District Level'&bookMarkState='N'&bookMarkName='N'">1:1
										Device Reports</a> <a class="dropdown-item" href="/report?reportName='DRM_OneToOne Devices District Level'&bookMarkState='N'&bookMarkName='N'">Device
										Counts</a> <a class="dropdown-item" href="#">Deployment Report</a>
									<a class="dropdown-item" href="#">Lost / Stolen Report</a> <a
										class="dropdown-item" href="#">Circulation Reports</a> <a
										class="dropdown-item" href="#">Historical Data</a> <a
										class="dropdown-item" href="#">Combined - Library and
										Resource Reporting</a> 
										<c:if test="${userDetails.getIsPrimeUser() eq 'Y' }">
											<a class="dropdown-item" href="#">Ad Hoc Report</a>
										</c:if>
								</div>
							</c:if></li>
						</c:if>
					</c:forEach>
			</ul>
			</nav>
		</div>

	<div class="bgsection">
			<div class=" col-lg-12 login_section">
				 <div class="row">
					<div class="col-md-6 tablebox">
						<h2>Bookmarks</h2>
						<table class="table table-bordered">
							<thead>
								<tr class="table_head">
									<th>Name</th>
									<th>Saved Date & Time</th>
									<th></th>
								</tr>
							</thead>

							<c:forEach items="${bookmarksList}" var="bookmarks">

								<c:if test="${bookmarks!=null }">
									<tbody>
										<tr>
											<td>${bookmarks.getBookMarkName()}</td>
											<td>${bookmarks.getBookMarkDate()}</td>
											<td><a href = "/report?reportName='DRM_OneToOne Devices District Level'&bookMarkState='${bookmarks.getBookMarkState()}'&bookMarkName='${bookmarks.getBookMarkName()}'"><i class="fa fa-edit editicon"> </i></a><i
													class="fa fa-trash deleteicon" onClick = "deleteBookMark('${bookmarks.getBookMarkName()}', '${bookmarks.getBookMarkDate()}', '${bookmarks.getUserName()}');"></i></td>
										</tr>
									</tbody>
								</c:if>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>

		</div> 
		
		<!-- javascript -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
			<script src="<c:url value="js/powerbi.min.js" />"></script>
			<script src="<c:url value="js/destiny.js" />"></script>
		<script type="text/javascript">
        var config = {
            type: 'report',
            embedUrl: 'https://app.powerbi.com/reportEmbed',
        };
 
        var element = powerbi.preload(config);
    </script>
		<!-- javascript -->
	</form>
</body>

</html>