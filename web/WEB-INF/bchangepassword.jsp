<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Change Business Password</title>
    </head>
    <body>
        <div id="hd">Specials App</div>
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
                    <td>
                        <form mehod="post" action="BusinessChangePassword"/>
                        <input id="NavigationButton" type="submit" value="Change Password"/>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
        <div id="Title"><h1>Change Business Password</h1></div>
        <c:out value="${flash}"/>
        <div id="PasswordWhiteSpace">
            <table id="ChangePassword">
                <form action="BusinessChangePassword">
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
