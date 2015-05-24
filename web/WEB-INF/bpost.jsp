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
        <div id="hd">Specials App</div>
        <div id="NavigationMenu">
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
                        <form method="POST" action="BusinessPost">
                            <input id="NavigationButton" type="submit" value="Post Special"/>
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="BusinessPicture">
                            <input type="hidden" name="action" value="picPage"/>
                            <input id="NavigationButton" type="submit" value="Business Picture"/>
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="EditBusinessProfile">
                            <input id="NavigationButton" type="submit" value="View/Edit Profile"/>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="ViewSubscribedCustomers">
                            <input id="NavigationButton" type="submit" value="View Subscribed Customers"/>
                        </form>
                    </td>
                    <td>
                        <form mehod="post" action="BusinessIndividualComments"/>
                        <input id="NavigationButton" type="submit" value="View Customer Comments"/>
                        </form>
                    </td>
                    <td>
                        <form mehod="post" action="BusinessChangePassword"/>
                        <input id="NavigationButton" type="submit" value="Change Password"/>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
        <div id="Title"><h1>Posted Specials</h1></div> 
        <table id="PostASpecial">
            <form method="post" action="BusinessPost">
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
                        <textarea name="special" rows="4" cols="50" required></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="action" value="verifybpost"/>
                        <input type="submit" value="Submit"/>
                    </td>
                </tr>
            </form>
        </table>
        <div id="IndexFilter2">
            <table>
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
            </table>
        </div>
        <div class="SpecialsScroll2">
            <form action="BusinessPost" method="Post">
                <c:forEach var="special" items="${specials}">
                    <div id="scontent">
                        <table id="SpecialsTable">
                            <tr>
                                <td style="background-color: #808080" style="height:5px">
                                    <button name ="delsbutton" value="${special.id}">Delete</button>
                                </td>
                            </tr>
                            
                            <tr>
                                <td colspan>
                                    <b>Date:</b> <fmt:formatDate type="date" value="${special.sdate}"/>
                                </td>
                                
                            </tr>
                            <tr>
                                <td>
                                    <b>Time:</b> <fmt:formatDate type="time" value="${special.stime}"/> to 
                                    <fmt:formatDate type="time" value="${special.stime2}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>Type:</b> <c:out value="${special.stype}"/></br>
                                </td>
                            </tr>
                            <tr>
                                <td id="Chalkboard">
                                    <c:out value="${special.special}" escapeXml="false"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>
                <input type="hidden" name="action" value="delspecial"/>
            </form>
        </div>
        <br>
        
    </body>
</html>
