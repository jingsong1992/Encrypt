<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var xmlHttp;
var flag;
function createXMLHttp(){
	if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();
		}else{
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
}
function entrypt(){
	var password = document.getElementById("password").value;
	createXMLHttp();
	xmlHttp.open("POST","EncryptPasswordServlet?password="+password);
	xmlHttp.onreadystatechange = moveCallback;
	xmlHttp.send(null);
	}
function moveCallback(){
	if(xmlHttp.readyState == 4){
		if(xmlHttp.status == 200){
			var text = xmlHttp.responseText;
			document.getElementById("encryptPassword").value = text;
			}
		}
}

</script>
</head>
<body>
<form action="" method="post" id="login" name="login">
	<table align="center" border="0">
		<tr>
			<td>Password</td>
			<td><input type="text" name="password" id="password"></td>
		</tr>
		<tr>
			<td>EncryptPassword</td>
			<td><input type="text" name="encryptPassword" id="encryptPassword"></td>
		</tr>
		<tr>
			<td><input type="button" value="加密" onClick="entrypt()"></td>
		</tr>
	</table>
</form>
</body>
</html>