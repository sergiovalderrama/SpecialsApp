<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Individual Reviews For Business</title>
    </head>
    <body>        
        <div class="scroll">
                <c:forEach var="review" items="${breview}">
                    <div id="inner">
                    <table id="customerReviews">
                        <tr id="timeForReview">
                            <td>
                                Post Date: ${review[2]}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b>username: ${review[1]}<b>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                ${review[0]}
                            </td>
                        </tr>
                    </table>
                    </div>
                </c:forEach>
            </div>
        <a href="BusinessMenu">Main Menu</a>
    </body>
</html>
