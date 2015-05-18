<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Post Special</title>
    </head>
    <body>
        <div id="hd"></div>
        <div id="bd">
            <table style="float : right">
                <tr>
                    <td>
                        Username: <c:out value="${buser.username}"/>
                    </td>
                    <td>
                        <form method="POST" action="BusinessLogout">
                            <input type="hidden" name="action" value="blogout"/>
                            <input type="submit" value="Logout"/>
                        </form>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td>
                        <form method="POST" action="BusinessMenu">
                            <input type="submit" value="Business Menu"/>
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="BusinessPost">
                            <input type="hidden" name="action" value="bpost"/>
                            <input type="submit" value="Post Special"/>
                        </form>
                    </td>
                </tr>
            </table>
            <hr>
            <c:out value="${flash}"/>
            <table style="float: right" border="2" >
                <tr>
                <form action="BusinessPost" method="Post">
                    <td colspan="2">

                        <input type="date" name="sortingdate" required/>
                    </td>
                    <td>
                        <input type="hidden" name="action" value="sortbydate"/>
                        <input type="submit" value="Filter"/>
                    </td>
                </form>
                <td colspan="3">
                    <form action="BusinessPost" method="Post">
                        <input type="hidden" name="action" value="all"/>
                        <input type="submit" value="View All"/>
                    </form>
                </td>
                </tr>
                <tr>
                    <th colspan="6">
                        Posted Specials 
                    </th>
                </tr>
                <form action="BusinessPost" method="Post">
                    <c:forEach var="special" items="${specials}">
                        <tr >
                            <td colspan="2">
                                <b>Date:</b> <fmt:formatDate type="date" value="${special.sdate}"/>
                            </td>
                            <td>
                                Type: <c:out value="${special.stype}"/></br>
                            </td>
                            <td>
                                Time: <fmt:formatDate type="time" value="${special.stime}"/> to 
                                <fmt:formatDate type="time" value="${special.stime2}"/>
                            </td>
                            <td>
                                <c:out value="${special.special}" escapeXml="false"/>
                            </td>
                            <td>
                                <button name ="delsbutton" value="${special.id}">Delete</button>
                            </td>
                        </tr>
                        <tr border="0" color="white">
                            <td colspan="6">
                                <hr>
                            </td>
                        </tr>
                    </c:forEach>
                    <input type="hidden" name="action" value="delspecial"/>
                </form>
            </table>
            <form method="post" action="BusinessPost">
                <table>
                    <tr>
                        <th colspan="2">
                            Post A Special
                        </th>
                    </tr>
                    <tr> 
                        <td>
                            <label>Special Date : </label>
                        </td>
                        <td>
                            <input type="date" name="date" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Time : </label>
                        </td>
                        <td>
                            <input type="time" name="time" required/> to <input type="time" name="time2" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Special Type : </label>
                        </td>
                        <td>
                            <select name="type" required>
                                <option value="Happy Hour">Happy Hour</option>
                                <option value="Brunch">Brunch</option>
                                <option value="Lunch">Lunch</option>
                                <option value="Dinner">Dinner</option>
                                <option value="Breakfast">Breakfast</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Special : </label>
                        </td>
                        <td>
                            <textarea name="special" rows="5" cols="40" required></textarea>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="action" value="verifybpost"/>
                <input type="submit" value="Submit"/>
            </form>
        </div>
        <div id="ft"></div>
    </body>
</html>
