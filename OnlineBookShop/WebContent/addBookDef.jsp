<%@ taglib prefix="jlc" uri="/WEB-INF/tlds/bookshop.tld" %>   
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>JLC Online Book Shop</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/indexstyle.css">
</head>
<body bgcolor="#e4e4e4">
<jlc:validateSession/>
<jlc:removeSearchInfoFromSessionTag/>
<core:if test="${USER_TO.role ne 'Admin' }">
<jsp:forward page="searchBookDef.jsp"/>
</core:if>
<table width="90%" align="center" height="97%">
<tr height="5px" valign="top">
<td align="center">
<jsp:include page="/WEB-INF/pages/header.jsp"/>
</td>
</tr>
<tr valign="top" bgcolor="maroon" height="3px">
<td align="center">
<jsp:include page="/WEB-INF/pages/userMenu.jsp"/>
</td>
</tr>
<tr valign="top">
<td align="center">
<jsp:include page="/WEB-INF/pages/addBook.jsp"/>
</td>
</tr>
<tr valign="bottom">
<td align="center">
<jsp:include page="/WEB-INF/pages/countUsers.jsp"/>
</td>
</tr>
<tr height="2px" valign="bottom">
<td align="center">
<jsp:include page="/WEB-INF/pages/footer.html"/>
</td>
</tr>
</table>
</body></html>