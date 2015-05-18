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
        <c:if test="${customers != null}">
            <table>
                <tr>
                    <th>
                        Username
                    </th>
                    <th>
                        E-Mail
                    </th>
                </tr>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td>
                            ${customer[0]}
                        </td>
                        <td>
                            <a href="mailto:${customer[1]}">${customer[1]}</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${cprofile != null}">
            <table>
                <tr>
                    <th>
                        Name
                    </th>
                    <th>
                        Username
                    </th>
                    <th>
                        E-Mail
                    </th>
                </tr>
                <c:forEach var="profile" items="${cprofile}">
                    <tr>
                        <td>
                            ${profile[0]} &nbsp; ${profile[1]}
                        </td>
                        <td>
                            ${profile[3]}
                        </td>
                        <td>
                            <a href="mailto:${profile[2]}">${profile[2]}</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <br>
        <c:if test="${customers != null}">
        <b>E-Mail list for all users</b><br>
        <textarea rows="10" cols="50">
            <c:forEach var="email" items="${customers}">
${email[1]},
            </c:forEach>
        </textarea>
        </c:if>
        <br>
        <a href="BusinessMenu">MAIN MENU</a>
        <a href="ViewSubscribedCustomers?action=profiles">Customers With Profiles</a>
        <a href="ViewSubscribedCustomers">View All Subscribed Customers</a>

    </body>
</html>
