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
        <div id="hd">SpecialsApp</div>
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
                </tr>
            </table>
        </div>
                    <div id="Title"><h1>Customer Reviews</h1></div>
        <div id="CustomerReviews">
            <c:forEach var="review" items="${breview}">
                <div id="inner">
                    <table id="TableReviews">
                        <tr id="timeForReview">
                            <td>
                                Post Date: ${review[2]}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <b>${review[1]}</b><br>
                                <img id="CustomerImage" src="ViewCustomerPicture?for=${review[3]}" onerror="this.src='images/happyface.jfif'"/>        
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
    </body>
</html>
