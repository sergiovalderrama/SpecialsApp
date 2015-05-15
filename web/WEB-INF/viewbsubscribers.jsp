<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <th colspan="2">
                    Subscribed Customers
                </th>
            </tr>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td>
                        <label>username: </label>
                        ${customer[0]}
                    </td>
                    <td>
                        <label>E-Mail :</label>
                        <a href="mailto:${customer[1]}">${customer[1]}</a>
                    </td>
                <tr>
                </c:forEach>
        </table>
        <br>
        <a href="BusinessMenu">MAIN MENU</a>
    </body>
</html>
