<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <form:form action="submitAddForm" modelAttribute="quotation">  
        Deal ID: <form:input path="dealID" />         
        <br><br>  
        Company Name: <form:input path="companyName" />  
        <br><br>  
        Registration Number: <form:input path="registrationNumber" />         
        <br><br> 
        <input type="submit" value="Submit" />      
    </form:form>  
</body>
</html>