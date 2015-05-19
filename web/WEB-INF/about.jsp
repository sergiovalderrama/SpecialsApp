<%@page contentType="text/html" pageEncoding="UTF-8"trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About The Specials Website</title>
        <link rel="stylesheet" type="text/css" href="styles/main.css">
    </head>
    <body>
        <div id="hd">Specials App</div>
        <div id="bd">
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
            <div id="Title"><h1>About The Specials Website</h1></div>
            <div id="About">
                <p>
                    This website allows customers to view specials posted by businesses. 
                    Customers can view happy hour, brunch, lunch, dinner, and breakfast specials.  
                    Customers can post reviews about businesses, rate businesses, and subscribe to businesses. 
                    Businesses are able to view customer reviews and customers that are subscribed to their business.
                </p>
            </div>
        </div>
    </body>
</html>
