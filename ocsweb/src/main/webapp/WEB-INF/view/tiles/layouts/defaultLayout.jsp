<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="icon" type="image/x-icon"
	href="./resources/images/favicon/favicon.ico">
<link rel="icon" type="image/x-icon"
	href="../resources/images/favicon/favicon.ico">
</head>
<body>
	<div>
		<tiles:insertAttribute name="header" />
	</div>
	<%-- <div style="float:left;padding:10px;width:15%;"><tiles:insertAttribute name="menu" /></div>  
        <div style="float:left;padding:10px;width:80%;border-left:1px solid pink;">   --%>
	<div>
		<tiles:insertAttribute name="body"  />
	</div>
</body>
</html>