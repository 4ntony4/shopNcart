<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="facade.Facade"
%>
<%
	Facade facade = new Facade(request);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title><%=facade.getTitle()%></title>
<link rel="stylesheet" href="WebContent/css/style.css">
<link rel="stylesheet" href=<%=facade.getStylePath()%>>
<link rel="icon" type="image/png" href=<%=facade.getLogoPath()%>>
</head>
<body class="login">
	<div id="j_security_check">
		<!-- action="j_security_check" -->
		<form action="login.jsp" method=post>
	   	 	<p><strong>E-mail: </strong>
	    	<input type="text" size="20" name="j_username" >
	    	<p><p><strong>Password: </strong>
	    	<input type="password" size="18" name="j_password">
	    	<p><p>
	    	<input type="submit" value="Submit">
	    	<input type="reset" value="Reset">
		</form>
	</div>
</body>
</html>
