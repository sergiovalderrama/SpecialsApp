<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Change Customer Password</title>
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
                                <input  type="submit" value="Logout"/>
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
        <div id="Title"><h1>Change Customer Password</h1></div>
        <c:out value="${flash}"/>
        <div id="PasswordWhiteSpace">
            <table id="ChangePassword">
                <form action="CustomerChangePassword">
                    <tr>
                        <td>
                            <label>Current Password :</label>
                        </td>
                        <td>
                            <input type="password" name="oldpassword" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>New Password :</label>
                        </td>
                        <td>
                            <input type="password" name="newpassword1" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>New Password Again:</label>
                        </td>
                        <td>
                            <input type="password" name="newpassword2" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="hidden" name="action" value="changep"/>
                            <input type="submit" value="Submit"/>
                        </td>
                    </tr>
                </form>
            </table>
        </div>
    </body>
</html>
