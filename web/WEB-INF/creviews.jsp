<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Customer Reviews</title>
    </head>
    <body>
        <div id="hd">Specials App</div>
        <div id="NavigationMenu">
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
                </tr>
            </table>
        </div>
        <div id="Title"> <h1>Reviews for&nbsp;<c:out value="${Bprofile.bname}" /></h1></div>
        <div id="GreySpaceForPost">
            <c:out value="${flash}"/>
            <table id="PostReview">
                <form action="ReviewsOnBusiness" method="post">
                    <tr>
                        <td>
                            <textarea id="SubmitPost" name="creview" required></textarea>
                        </td>
                        <td>
                            <input type="hidden" name="bid" value="<c:out value="${bid}"/>"/>
                            <input type="hidden" name="action" value="reviews"/>
                            <input type="submit" value="Post A Review"/>  
                        </td>
                    </tr>
                </form>
            </table>
        </div>
        <br>
        <div id="CustomerReviews">
            <c:forEach var="review" items="${bReviews}">
                <div id="inner">
                    <table id="TableReviews">
                        <tr id="timeForReview">
                            <td>
                                Post Date: ${review[1]}
                            </td>

                        </tr>
                        <tr>
                            <td>
                                <b>${review[3]}</b><br>
                                <img id="CustomerImage" src="ViewCustomerPicture?for=${review[4]}" onerror="this.src='images/happyface.jfif'"/>
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
