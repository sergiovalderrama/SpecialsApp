<%@page contentType="text/html" pageEncoding="UTF-8"trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Change Administrator Password</title>
    </head>
    <body>
        <div id="hd">Specials App</div>
        <div id="NavigationMenu">
            <table style="float: right">
                <tr>
                    <td>
                        Username: <c:out value="${administrator.username}"/></h3>
                    </td>
                    <td>
                        <form method="POST" action="AdministratorLogout">
                            <input type="submit" value="Logout"/>
                        </form>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td>
                        <form action="AdministratorMenu">
                            <input id="NavigationButton" type="submit" value="Pending Users"/>
                        </form>
                    </td>
                    <td>
                        <form action="AdministratorApprovedBusers">
                            <input id="NavigationButton" type="submit" value="View Approved Users"/>
                        </form>
                    </td>
                    <td>
                        <form action="AdministratorDisabledBusers">
                            <input id="NavigationButton" type="submit" value="View Disabled Users"/>
                        </form>
                    </td>
                    <td>
                        <form action="AdministratorChangePassword">
                            <input id="NavigationButton" type="submit" value="Change Password"/>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
        <div id="Title"><h1>Change Password</h1></div>
        <c:out value="${flash}"/>
        <div id="PasswordWhiteSpace">
            <table id="ChangePassword">
                <form action="AdministratorChangePassword">
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
