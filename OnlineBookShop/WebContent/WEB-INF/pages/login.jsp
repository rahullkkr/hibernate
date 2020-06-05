<%@ taglib prefix="jlc" uri="/WEB-INF/tlds/bookshop.tld" %>
<%@ page session="false" %>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jlcindia.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>
<form action="login.do" method="post">
<center>
<table class="textStyle">
<tr >
	<td align="center" colspan="3">
	<font size="7"><b>Account Login</b></font>
	</td>
	</tr>
<tr>
<td align="center" colspan="3">
<font size="4" color="red"><b><jlc:error property="loginError"/></b></font>
</td>
</tr>
<tr><td><br/></td></tr>
<tr>
<td align="center" height="">
<font size="5"><b>Username</b></font></td>
<td>
<input type="text"  name="uname" size="35" style="color:black;background-color:#b4e0d2;font-size=20"/>
</td>
<td>
<font size="4" color="red"><b><jlc:error property="uname"/></b></font>
</td>
</tr>
<tr><td colspan="3"><br/></td></tr>
<tr>
<td align="center">
<font size="5"><b>Password</b></font>
</td>
<td>
<input type="password"  name="pass" size="35" style="color:black;background-color:#b4e0d2;font-size=20"/>
</td>
<td>
<font size="4" color="red"><b><jlc:error property="pass"/></b></font>
</td>
<tr>
<td colspan="3" align="center"><font  size="2"><b>Username and Password are case-sensitive.</b></font></td>
</tr>
<tr>
<td colspan="3" align="center">
<br/>
<input type="submit"  value="Login Me"  style="color:white;background-color:maroon;font-size=35"/>
</td>
</tr>
<tr><td><br/></td></tr>
<tr>
<td align="center" colspan="3">
<b><font size="5">New User</font>&nbsp;<a href="registerDef.jsp">
<font size="5">SignUp Here</font></a></b>
</td>
</tr>
<tr>
<td align="center" colspan="3">
<b><font size="5">Forget Password</font>&nbsp;<a href="forgetPasswordDef.jsp">
<font size="5">Click Here</font></a></b>
</td>
</tr>
</table>	
</center>
</form>
</body>
</html>