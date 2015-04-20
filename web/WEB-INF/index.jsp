<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form>
        <table>
            <tr>
                <td>
                    <button name="action" value="home">Home</button>
                </td>
                <td>
                    <button name="action" value="about">About</button>
                </td>
                <td>
                    <button name="action" value="cregistration">Customer Registration</button>
                </td>
                <td>
                    <button name="action" value="bregistration">Business Registration</button>
                </td>
                <td>
                    <button name="action" value="blogin">Business Login</button>
                </td>
            </tr>
        </table>
        </form>     
        <h1>Specials Home Page</h1>
        <c:forEach var="special" items="${scontent}">
            <div id="scontent">
                <table id="sTable">
                    <tr>
                        <th>
                            <a href="controller?action=binfo&pid=${special[1]}">${special[0]} --ID: ${special[1]}</a>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <b>Date:</b>${special[2]} <b>Time:</b>${special[3]} to ${special[4]}<br>
                            <b>Type:</b>${special[5]}<br>
                            <b>Price:</b>$${special[7]}<br>
                            <b>Special:</b><br>
                            <c:out value="${special[6]}"/>    
                        </td>
                    </tr>
            
                </table>
            </div>
        </c:forEach>
    </div>
    <div id="ft"></div>
    </body>
</html>
