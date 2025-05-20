<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2025. 05. 13.
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag dynamic-attributes="params" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <meta charset="UTF-8">
    <title>${params.get("title")}</title>
</head>
<body>
<h3>Udvozollek</h3>
<div>
    <div>
        <a href="${pageContext.request.contextPath}/index.html">index</a>
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/companyList.jsp">mozi lista</a>
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/logout">kijelentkezes</a>
    </div>
</div>
<div>
    <jsp:doBody/>
</div>
</body>
</html>
