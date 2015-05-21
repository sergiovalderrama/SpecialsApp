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
        <div id="hd">Specials App</div>
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
        <div id="NavigationMenu">
            <table>
                <tr>
                    <td>
                        <form action="home" method="Post">
                            <input id="NavigationButton" type="submit" value="Home"/>
                        </form>
                    </td>
                    <td>
                        <form action="about" method="Post">
                            <input id="NavigationButton" type="submit" value="About"/>
                        </form>
                    </td>
                    <c:if test="${sessionScope.cuser != null}">
                        <td>
                            <form action="ViewSubscriptions" method="Post">
                                <input id="NavigationButton" type="submit" value="View Subscriptions"/>
                            </form>
                        </td>
                        <td>
                            <form action="InsertCustomerProfile" method="post">
                                <input id="NavigationButton" type="submit" value="Add/Edit Profile"/>
                            </form>
                        </td>
                        <td>
                            <form mehod="post" action="CustomerChangePassword"/>
                            <input id="NavigationButton" type="submit" value="Change Password"/>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${sessionScope.cuser == null}">
                        <td>
                            <form action="CustomerLogin" method="Post">
                                <input id="NavigationButton" type="submit" value="Customer Login"/>
                            </form>
                        </td>
                        <td>
                            <form action="BusinessLogin" method="Post">
                                <input id="NavigationButton" type="submit" value="Business Login"/>
                            </form>
                        </td>
                        <td>
                            <form action="AdministratorLogin" method="Post">
                                <input id="NavigationButton" type="submit" value="Administrator Login"/>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </table>
        </div>
        <br>
        <div id="IndexFilter">
            <table>
                <tr>
                    <td>
                        <label>Date </label>
                    </td>
                <form action="home" method="post">
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
                        <input type="submit" value="Sort"/>
                    </td>
                </form>
                <form>
                    <td>
                        <input type="hidden" name="action" value="viewall"/>
                        <input type="submit" value="View All"/>
                    </td>
                </form>
                </tr>
            </table>
        </div>
        <div class="SpecialsScroll">
            <c:forEach var="special" items="${specials}">
                <div id="scontent">
                    <table id="SpecialsTable">
                        <tr>
                            <th>
                                <a href="BusinessInformation?pid=${special[3]}&bid=${special[1].id}">${special[2]}</a>
                            </th>
                        </tr>
                        <tr>
                            <td>
                                <c:if test="${special[0] != null}">
                                    <img id="BusinessImage" src="ViewBusinessPicture?for=${special[1].id}"/>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b>Type:</b>${special[7]}<br>
                                <b>Date:</b><fmt:formatDate type="date" value="${special[4]}"/> 
                                &nbsp;&nbsp;<b>Time:</b><fmt:formatDate type="time" value="${special[5]}"/> to 
                                <fmt:formatDate type="time" value="${special[6]}"/>
                                &nbsp;&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td id="Chalkboard">
                                <font size="5">
                                <b>Special:</b><c:out value="${special[8]}" escapeXml="false"/>
                                </font>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
