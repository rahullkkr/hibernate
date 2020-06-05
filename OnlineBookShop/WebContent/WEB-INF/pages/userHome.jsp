<%@ taglib prefix="jlc" uri="/WEB-INF/tlds/bookshop.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jlcindia.css">
</head>
<body>
<center>
<table class="textStyle">
<tr>
<td align="center" colsapn="3">
<c:if test="${ USER_TO.role eq 'User'}">
<font size="7"><b>User <br/>Home Page</b></font>
</c:if>
<c:if test="${ USER_TO.role eq 'Admin'}">
<font size="7"><b>Admin<br/>Home Page</b></font>
</c:if>
</td></tr>
</table>
</center>
</body>
</html>