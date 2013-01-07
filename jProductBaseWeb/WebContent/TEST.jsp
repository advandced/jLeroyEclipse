<%@page import="java.io.*"%>
<%@page import="java.net.URL"%>
<%@ page import="java.util.*" %>
<%@ page import="fr.la.configfilereader.*" %>
<%@ page import="fr.la.jproductbase.presentation.*" %>
<%@ page import="fr.la.jproductbase.metier.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

String WEB_INF = "";
//className= package/subPackage/subsubPackage/../ClassName.class
String className=JProductBaseParameters.class.getName().replaceAll("\\.", "/" )+".class";
//Use the ClassLoader to find the absolute path to this file.
URL classPath=JProductBaseParameters.class.getClassLoader().getResource(className);

File f=new File(classPath.getPath());
while(f!=null && !f.getName().equals("WEB-INF" )){
 f=f.getParentFile();
}
//if the root is reached without finding the WEB-INF directory
//WEB-INF will equal null
if(f==null) {
	WEB_INF= "MARCHERA PAS";
}
else {
	WEB_INF=f.getPath().replace("%20", " ");
	WEB_INF = WEB_INF
			+ File.separator //+ "classes" + File.separator
			+ "jProductBase.conf";
}


%>

<h1><%=WEB_INF %></h1>
</body>
</html>