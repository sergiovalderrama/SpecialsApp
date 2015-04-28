<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Specials Main Page</title>

    </head>
    <body>
        <div id="hd"></div>
        <div id="bd">
            <c:if test="${sessionScope.cuser != null}">
                <table style="float :right">
                    <tr>
                        <td>
                            User: &nbsp;<c:out value="${cuser.username}"/>
                        </td>
                        <td>
                            <form action="CustomerLogout" method="Post">
                                <input type="submit" value="Logout"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </c:if>
            <table>
                <tr>
                    <td>
                        <form action="home" method="Post">
                            <input type="submit" value="Home"/>
                        </form>
                    </td>
                    <td>
                        <form action="about" method="Post">
                            <input type="submit" value="About"/>
                        </form>
                    </td>
                    <td>
                        <form action="CustomerRegistration" method="Post">
                            <input type="submit" value="Customer Registration"/>
                        </form>
                    </td>
                    <td>
                        <form action="BusinessRegistration" method="Post">
                            <input type="submit" value="Business Registration"/>
                        </form>
                    </td>
                    <td>
                        <form action="CustomerLogin" method="Post">
                            <input type="submit" value="Customer Login"/>
                        </form>
                    </td>
                    <td>
                        <form action="BusinessLogin" method="Post">
                            <input type="submit" value="Business Login"/>
                        </form>
                    </td>
                    <td>
                        <form action="AdministratorLogin" method="Post">
                            <input type="submit" value="Administrator Login"/>
                        </form>
                    </td>
                </tr>
            </table>

            <h1>Specials Home Page</h1>
            <form action="home" method="post">
                <table>
                    <tr>
                        <td>
                            <label>Date </label>
                        </td>
                        <td>
                            <input type="date" name="sortingdate" required />
                        </td>
                        <td>
                            <label>Type</label> 
                        </td>
                        <td>
                            <select name="type" required>
                                <option value="All">All Types</option>
                                <option value="Happy Hour">Happy Hour</option>
                                <option value="Brunch">Brunch</option>
                                <option value="Lunch">Lunch</option>
                                <option value="Dinner">Dinner</option>
                                <option value="Breakfast">Breakfast</option>
                            </select>
                        </td>
                        <td>
                            <input type="hidden" name="action" value="sortbydate"/>
                            <input type="submit" value="sort"/>
                        </td>
                    </tr>
                </table>
            </form>
            <c:forEach var="special" items="${specials}">
                <div id="scontent">
                    <table id="sTable">
                        <tr>
                            <th>
                                <a href="BusinessInformation?pid=${special[1]}">${special[0]} --ID: ${special[1]}</a>
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <b>Date:</b><fmt:formatDate type="date" value="${special[2]}"/> <b>Time:</b>
                                <fmt:formatDate type="time" value="${special[3]}"/> to 
                                <fmt:formatDate type="time" value="${special[4]}"/><br>
                                <b>Type:</b>${special[5]}<br>
                                <b>Price:</b>$${special[6]}<br>
                                <b>Special:</b><br>
                                <c:out value="${special[7]}"/>    
                            </td>
                        </tr>

                    </table>
                </div>
            </c:forEach>
        </div>
        <div id="ft"></div>
    </body>
</html>
