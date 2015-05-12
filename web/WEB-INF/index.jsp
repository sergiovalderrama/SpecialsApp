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
                    <c:if test="${sessionScope.cuser != null}">
                        <td>
                            <form action="ViewSubscriptions" method="Post">
                                <input type="submit" value="View Subscriptions"/>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${sessionScope.cuser == null}">
                        <td>
                            <form action="CustomerLogin" method="Post">
                                <input type="submit" value="Customer Login"/>
                            </form>
                        </td>
                    </c:if>
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
                            <th rowspan="3">
                                <c:choose>
                                    <c:when test="${special[0] != null}">
                                        <img id="BusinessImage" src="ViewBusinessPicture?for=${special[1].id}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="images/noimage.jfif"/>
                                    </c:otherwise>
                                </c:choose>
                            </th>
                            <th>
                                <a href="BusinessInformation?pid=${special[3]}">${special[2]}</a>
                            </th>

                        </tr>
                        <tr>
                            <td>
                                <b>Date:</b><fmt:formatDate type="date" value="${special[4]}"/> 
                                &nbsp;&nbsp;<b>Time:</b><fmt:formatDate type="time" value="${special[5]}"/> to 
                                <fmt:formatDate type="time" value="${special[6]}"/>
                                &nbsp;&nbsp;<b>Type:</b>${special[7]}<br>
                            </td>
                        </tr>
                        <tr>
                            <td style="background-color: gainsboro">
                                <font size="5">
                                <b>Special:</b><c:out value="${special[8]}" escapeXml="false"/>
                                </font>
                            </td>
                        </tr>

                    </table>
                </div>
            </c:forEach>
        </div>
        <div id="ft"></div>
    </body>
</html>
