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
        <div id="ft"></div>
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
                        <td>
                            <form action="InsertCustomerProfile" method="post">
                                <input type="submit" value="Add/Edit Profile"/>
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
            <br>${flash}
            <div id="reviewBName"> Reviews for: <c:out value="${Bprofile.bname}" /></div>

            <div class="scroll">
                <c:forEach var="review" items="${bReviews}">
                    <div id="inner">
                        <table id="customerReviews">
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
            <table>
                <form action="ReviewsOnBusiness" method="post">
                    <tr>
                        <td>
                            <textarea id="reviewBusiness" name="creview"></textarea>
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
        <div id="ft"></div>
    </body>
</html>
