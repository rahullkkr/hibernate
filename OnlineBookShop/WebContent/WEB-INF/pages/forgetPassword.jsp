<%@ taglib prefix="jlc" uri="/WEB-INF/tlds/bookshop.tld" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Forget Password</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jlcindia.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>
<center>
<form action="forgetPassword.do" method="post">
<table class="textStyle">
<tr>
<td align="center" colspan="3">
<font size="7"><b>Retreive Your Password</b></font>
</td>
</tr>
<tr>
<td>
<td align="center" colspan="3">
<font size="4" color="red" ><b><jlc:error property="forgetPasswordError"/></b></font>
</td>
</tr>
<tr><td><br/></td></tr>
<core:if test="${PASSWORD eq null }">
<tr>
<td align="center" height="">
<font size="5"><b>Username</b></font>
</td>
<td>
<input type="text"  name="uname" size="35" style="color:black;background-color:#b4e0d2;font-size=20"/>
</td>
<td>
<font size="4" color="red" ><b><jlc:error property="uname"/></b></font>
</td>
</tr>
<tr><td colspan="3"><br/></td></tr>
<tr><td align="center"><font size="5"><b>Email</b></font>
</td>
<td>
 <input type="text"  name="email" size="35" style="color:black;background-color:#b4e0d2;font-size=20"/>
 </td>
 <td>
 <font size="4" color="red"><b><jlc:error property="email"/></b></font>
 </td>
 </tr>
 <tr>
 <td align="center" colspan="3">
 <br/>
 <input type="submit"  value="Show Password"  style="color:white;background-color:maroon;font-size=35"/>
 </td>
 </tr>
 <tr>
 <td align="center" colspan="3">
 <b><font size="5">New User</font>&nbsp;<a href="registerDef.jsp"><font size="5">SignUp Here</a>
 </b>
 </td>
 </tr>
</core:if>
<core:if test="${PASSWORD ne null }">
<tr>
<td align="center" colspan="3">
<b><font color="green" size="5">Your Password is </font><font color="red" size=6>${PASSWORD }
</font></b>
</td>
</tr>
</core:if>
<tr>
<td align="center" colspan="3">
<b><font size="5">Login</font>&nbsp;<a href="index.jsp"><font size="5">Click Here</font></a>
</b>
</td>
</tr>
</table>
</form>
</center>
</body>
</html>