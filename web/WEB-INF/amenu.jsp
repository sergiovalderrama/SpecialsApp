<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Administrator Menu</title>
    </head>
    <body>
        <div id="hd"></div>
        <div id="bd">
            <h1>Administrator Menu</h1>
            <table id="pendingTable" border>
                <tr>
                    <th>
                        Business Username
                    </th>
                    <th>
                        E-Mail
                    </th>
                    <th colspan="2">
                        Status
                    </th>
                </tr>
                <c:forEach var="buser" items="${pending}">
                <tr>
                    <td>
                      <c:out value="${buser.username}"/> 
                    </td>
                    <td>
                        <a href="mailto:<c:out value="${buser.email}"/>">  
                         ${buser.email}
                        </a>    
                    </td>
                    <td>
                       <c:out value="${buser.status}"/>
                    </td>
                    <td>
                        <form action="AdministratorMenu" method="Post">
                        <select name="status" required>
                            <option value="pending">pending</option>
                            <option value="approved">approved</option>
                        </select>
                        <input type="hidden" name="bid" value="${buser.id}"/>
                        <input type="hidden" name="action" value="changestatus"/>
                        <input type="submit"/>
                        </form>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
        <div id="ft">
        </div>
    </body>
</html>
