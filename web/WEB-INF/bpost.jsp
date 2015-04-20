<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <table>
                <tr>
                    <td>
                        <h3>Username: <c:out value="${buser.username}"/></h3>
                    </td>
                    <td>
                        <form method="POST" action="controller">
                            <input type="hidden" name="action" value="blogout"/>
                            <input type="submit" value="Logout"/>
                        </form>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td>
                        <form method="POST" action="controller">
                            <input type="hidden" name="action" value="bmenu"/>
                            <input type="submit" value="Business Menu"/>
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="controller">
                            <input type="hidden" name="action" value="bpost"/>
                            <input type="submit" value="Post Special"/>
                        </form>
                    </td>
                </tr>
            </table>
            <c:out value="${flash}"/>
            <table style="float: right" border="2" >
                <tr>
                    <th colspan="2">
                       Posted Specials 
                    </th>
                </tr>
                <form action="controller" value="POST">
                <c:forEach var="special" items="${specials}">
                    <tr >
                        <td colspan="2">
                            <b>Date:</b> <c:out value="${special.sdate}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Type: <c:out value="${special.stype}"/></br>
                            Time: <c:out value="${special.stime} to ${special.stime2}"/><br>
                            Price: $<c:out value="${special.price}"/><br>
                            <c:out value="${special.special}"/>
                        </td>
                        <td>
                            <button name ="delsbutton" value="${special.id}">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
                    <input type="hidden" name="action" value="delspecial"/>
                </form>
            </table>
            <h1>Post Special Page</h1>
            <form method="post" action="controller">
                <table>
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
                            <label>Price : </label>
                        </td>
                        <td>
                            $<input type="number" name="price" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Special : </label>
                        </td>
                        <td>
                                <input type="textarea" name="special" rowspan="5" required></textarea>
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
