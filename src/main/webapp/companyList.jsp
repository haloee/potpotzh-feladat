<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<t:page>
    <style type="text/css">
        td {
            border: 1px solid black;
        }
    </style>
    <div>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>nev</th>
                <th>alapitas eve</th>
                <th>ismert termek</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${companies}" var="company">
                <tr>
                    <td><c:out value="${company.id}"/></td>
                    <td><c:out value="${company.nev}"/></td>
                    <td><c:out value="${company.alapitasi_ev}"/></td>
                    <td><c:out value="${company.ismert_termek}"/></td>

                    <td>
                        <button type="button" onclick="getById('${company.id}',${company.nev}, ${company.alapitasi_ev},${company.ismert_termek})">XML</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</t:page>
