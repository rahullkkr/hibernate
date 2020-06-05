<%@ taglib prefix="jlc" uri="/WEB-INF/tlds/bookshop.tld" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jlcindia.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/indexstyle.css">
</head>
<body>
<center>
<table class="textStyle" width="95%">
<tr>
<td>
<font color="yellow"><b>Welcome &nbsp;<font color="white">${USER_TO.firstName } &nbsp;${USER_TO.middleName } &nbsp;${USER_TO.lastName }</font> 
[${USER_TO.role }]</b></font>
</td>
<td>
<a href="userHomeDef.jsp">Home</a>
</td>
<core:if test="${ USER_TO.role eq 'Admin'}">
<td><a href="addBookDef.jsp">Add Book</a>
</core:if>
<td><a href="searchBookDef.jsp">Search Book</a>
<core:if test="${ USER_TO.role eq 'User'}">
<td><a href="userorderstatus.do">My Order Status</a>
</core:if>
<core:if test="${ USER_TO.role eq 'Admin'}">
<td><a href="allorders.do">Display Orders</a>
</core:if>
<td><a href="detailsDef.jsp">View Details</a>
</td>
<td><a href="changePasswordDef.jsp">Change Password</a>
</td>
<td><a href="logout.do">Logout</a>
</td>
</tr>
</table>
</center>
</body>
</html>