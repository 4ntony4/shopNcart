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
<body class="sessionInfo">
	<div>
		<h1><%=facade.getTitle()%> Shop</h1>
	</div>
	<div>
		<table>
		   <tr>
		      <th>Name</th>
		      <th>Value</th>
		   </tr>
		   <tr>
		      <td>siteId</td>
		      <td><%=facade.getSiteId()%></td>
		   </tr>
		   <tr>
		      <td>sessionTk</td>
		      <td><%=facade.getSessionTk()%></td>
		   </tr>
		   <tr>
		      <td>createdDt</td>
		      <td><%=facade.getCreatedDt()%></td>
		   </tr>
		   <tr>
		      <td>updatedDt</td>
		      <td><%=facade.getUpdatedDt()%></td>
		   </tr>
		   <tr>
		      <td>Instances created vs allSessions.size()</td>
		      <td><%=Facade.getNumOfInstances()%> - <%=Facade.getAllSessionsSize()%></td>
		   </tr>
		   <tr>
		      <td>Cookies</td>
		      <td><%= request.getCookies() %></td>
		   </tr>
		   <tr>
		      <td>Header cookie</td>
		      <td><% out.print(request.getHeader("cookie")); //$NON-NLS-1$ %></td>
		   </tr>
 		   <tr>
 		      <td>Header host</td>
 		      <td><% out.print(request.getHeader("host")); //$NON-NLS-1$ %></td>
 		   </tr>
 		   <tr>
 		      <td>Header connection</td>
 		      <td><% out.print(request.getHeader("connection")); //$NON-NLS-1$ %></td>
 		   </tr>
 		   <tr>
 		      <td>RequestURI</td>
 		      <td><%= request.getRequestURI() %></td>
 		   </tr>
 		   <tr>
 		      <td>RequestURL</td>
 		      <td><%= request.getRequestURL() %></td>
 		   </tr>
		   <tr>
		      <td>Is Requested SessionId From Cookie</td>
		      <td><%= request.isRequestedSessionIdFromCookie() %></td>
		   </tr>
		   <tr>
		      <td>Is Requested SessionId From URL</td>
		      <td><%= request.isRequestedSessionIdFromURL() %></td>
		   </tr>
		   <tr>
		      <td>Is Requested SessionId Valid</td>
		      <td><%= request.isRequestedSessionIdValid() %></td>
		   </tr>
		   <tr>
		      <td>Response Status</td>
		      <td><%= response.getStatus() %></td>
		   </tr>
		   <tr>
		      <td>New session</td>
		      <td><%= request.getSession().isNew() %></td>
		   </tr>
		</table>
	</div>
</body>
</html>
