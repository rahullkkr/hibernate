<%@ taglib prefix="jlc" uri="/WEB-INF/tlds/bookshop.tld" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jlcindia.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/indexstyle.css">
</head>
<body>
<form action="updateinfo.do" method="post">
<center>
<table class="textStyle" width="50%">
<tr bgcolor="orange">
<td align="center" colspan="3">
<font size="5"><b>Personal Details</b></font>
</td>
</tr>
<core:if test="${updateError ne null }">
<tr bgcolor="lightyellow">
<td align="center" colspan="3">
<font color="red" size="4"><b>${updateError }</b></font>
</td>
</tr>
</core:if>
<tr bgcolor="#fdeaff">
<td>
<font size="3"><b>Name</b></font>
</td>
<td colspan="2">
<font size="3"><b>${USER_TO.firstName } ${USER_TO.middleName } ${USER_TO.lastName }</b></font>
</td>
</tr>
<tr bgcolor="#dee1fe">
<td>
<font size="3"><b>Email</b></font>
</td>
<td>
<core:if test="${EDIT_INFO eq null }">
<font size="3"><b>${USER_TO.email }</b></font>
</core:if>
<core:if test="${EDIT_INFO ne null }">
<input type="text" value="${EMAIL }" name="email" size="25" style="color:black;background-color:"#b4e0d2;"/>
<font color="red" size="4"><b><jlc:error property="email"/></b></font>
</core:if>
</td>
</tr>
<tr bgcolor="#e1ffde">
<td>
<font size="3"><b>Phone</b></font>
</td>
<td>
<core:if test="${EDIT_INFO eq null }">
<font size="3"><b>${USER_TO.phone }</b></font>
</core:if>
<core:if test="${EDIT_INFO ne null }">
<input type="text" value="${PHONE }" name="phone" size="25" style="color:black;background-color:"#b4e0d2;"/>
<font color="red" size="4"><b><jlc:error property="phone"/></b></font>
</core:if>
</td>
</tr>
<tr bgcolor="#fdeaff">
<td>
<font size="3"><b>Username</b></font>
</td>
<td>
<font size="3"><b>${USER_TO.username }</b></font>
</td>
</tr>
<tr bgcolor="#dee1fe">
<td>
<font size="3"><b>Password</b></font>
</td>
<td colspan="2">
<font size="3"><b><jlc:displayUserPassword/></b></font>
</td>
</tr>
<tr bgcolor="maroon">
<core:if test="${EDIT_INFO eq null }">
<td align="center" colspan="3">
<a href="editinfo.do"><font size="4"><b>Edit</b></font></a>
</td>
</core:if>
<core:if test="${EDIT_INFO ne null }">
<td align="center">
<a href="canceledit.do"><font size="4"><b>Cancel</b></font></a>
</td>
<td align="center">
<input type="submit" value="Update" class="signoutButton"/>
</td>
</core:if>
</tr>
</table>
</center>
</form>
</body>
</html>