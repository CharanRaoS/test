<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<form action="#" id = "login_form" name="login_form" method="post">
		<div class="header">
			<div class="row">
				<div class="header_bar">
					<div class="row">
						<div class="col-lg-4 col-sm-4 logo_box">
							<ul>
								<li><img src="images/logo.png" class=""></li>


							</ul>

						</div>


						<div class="col-lg-8 col-sm-8 headerbar_menu">
							<ul>
								<li><img src="images/logout.png" class="">Login</li>
								<li><img src="images/user.png" class="">Create
									Account</li>

							</ul>
						</div>


					</div>
				</div>

			</div>
		</div>

		<div class="breadcrumb">
			<h2>Login</h2>

		</div>


		</div>

		<div style="text-align: center;"
			class="bgsection">
			<div class=" col-lg-12 login_section">
				<div class="row">

					<div class="col-md-6 tablebox" style="margin-right: auto; margin-left: auto; border-radius: 5px; border-spacing: 0 15px;">
						<h2>Log in using your Destiny account</h2>
						<c:if test="${not empty Message}">
							<div style="color: red; text-align: center;">
								<c:out value="${Message}" />
							</div>
						</c:if>
						<table class="login_table">

							<tr>
								<td class="username"><b>User Name :</b></font>&nbsp;&nbsp;<input
									type="text" name="userName" /></td>
							</tr>
							<tr>
								<td class="password"><b>Password &nbsp;&nbsp;:</b></font>&nbsp;&nbsp;<input
									type="password" name="password" /></td>
							</tr>

							<tr>
								<td><input type="button" value="Log In"
									class="login_button" onClick = "login();"/> <input type="submit" value="Cancel"
									disabled="disabled" class="cancel_button" /></td>

							</tr>
							<tr>
								<td>
								<div class="row" style="border-spacing: 0 15px;"></div>
								</td>
							</tr>
						</table>

					</div>










				</div>
			</div>
		</div>






		<!-- javascript -->
		<script src="<c:url value="js/jquery.min.js" />"></script>
		<script src="<c:url value="js/bootstrap.min.js" />"></script>
		<script src="<c:url value="js/destiny.js" />"></script>

		<!-- javascript -->

	</form>
</body>

</html>