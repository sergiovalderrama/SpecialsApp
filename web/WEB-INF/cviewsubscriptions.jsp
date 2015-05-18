<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Customer Subscription Page</title>
    </head>
    <div id="hd">
        Specials App
    </div>
    <body>
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
        </div>
        <div id="Title"><h1>Subscriptions</h1></div>
        <div id="SubscriptionWhiteSpace">
                <table id="SubscriptionsTable">
                    <tr>
                        <th colspan="2">
                    <div id="Title"><h1>Business Name</h1></div>
                        </th>
                    </tr>
                    <c:forEach var="sublist" items="${sublist}">
                        <tr>
                            <td>
                                <a href="BusinessInformation?pid=${sublist[3]}&bid=${sublist[0].id}">
                                    <c:out value="${sublist[1]}"/>
                                </a>
                            </td>
                            <td>
                                <form action="ViewSubscriptions" method="Post">
                                    <input type="hidden" name="action" value="unsubscribe"/>
                                    <input type="hidden" name="subscriptionid" value="${sublist[2]}"/>
                                    <input type="submit" value="Unsubscribe"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
            </table>
        </div>
    </body>
</html>
