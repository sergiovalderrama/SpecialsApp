<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Approved Business Users</title>
    </head>
    <body>
        <div id="hd">Specials App</div>
        <div id="NavigationMenu">
            <table style="float: right">
                <tr>
                    <td>
                        Username: <c:out value="${administrator.username}"/></h3>
                    </td>
                    <td>
                        <form method="POST" action="AdministratorLogout">
                            <input type="submit" value="Logout"/>
                        </form>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td>
                        <form action="AdministratorMenu">
                            <input id="NavigationButton" type="submit" value="Pending Users"/>
                        </form>
                    </td>
                    <td>
                        <form action="AdministratorApprovedBusers">
                            <input id="NavigationButton" type="submit" value="View Approved Users"/>
                        </form>
                    </td>
                    <td>
                        <form action="AdministratorDisabledBusers">
                            <input id="NavigationButton" type="submit" value="View Disabled Users"/>
                        </form>
                    </td>
                    <td>
                        <form action="AdministratorChangePassword">
                            <input id="NavigationButton" type="submit" value="Change Password"/>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
        <div id="Title"><h1>Approved Business Users</h1></div>
        <br><h3><c:out value="${flash}"/></h3>
        <c:if test="${bprofile != null}">
            <table id="BusinessProfile">
                <tr>
                    <th>
                        Business Information
                    </th>
                </tr>
                <tr>
                    <td>
                        <b>Name:</b> <c:out value="${bprofile[0]}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>Phone:</b> <c:out value="${bprofile[1]}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>Address:</b> <c:out value="${bprofile[2]}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>City:</b> <c:out value="${bprofile[3]}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>State:</b> <c:out value="${bprofile[4]}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>Zipcode:</b> <c:out value="${bprofile[5]}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <b>Website:</b> <c:out value="${bprofile[6]}"/>
                    </td>
                </tr>
            </table>
        </c:if>
        <table id="PendingBusers">
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
                        <a href="AdministratorApprovedBusers?action=bprofile&bid=${buser.id}"><c:out value="${buser.username}"/></a> 
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
                        <form action="AdministratorApprovedBusers" method="Post">
                            <select name="status" required>
                                <option value="pending">pending</option>
                                <option value="approved">approved</option>
                                <option value="disabled">disabled</option>
                            </select>
                            <input type="hidden" name="bid" value="${buser.id}"/>
                            <input type="hidden" name="action" value="changestatus"/>
                            <input type="submit"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
