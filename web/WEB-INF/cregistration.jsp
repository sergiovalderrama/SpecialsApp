<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Customer Registration Page</title>

    </head>
    <div id="hd"></div>
    <div id="bd">
        <form action="HomeNavigationButtons" method="Post">
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
        <h1>Customer Registration Page</h1>
        <h2 class="flash"><c:out value="${flash}"/></h2>
        <form method="POST" action="CustomerRegistration">
            <table>
                <tr>
                    <td><label>Username : </label></td>
                    <td><input type="text" name="username" required/></td>
                    <td><label>Username Again : </label></td>
                    <td><input type="text" name="username2" required/></td>
                </tr>
                <tr>
                    <td><label>Password : </label></td>
                    <td><input type="password" name="password" required></td>
                    <td><label>Password Again : </label></td>
                    <td><input type="password" name="password2" required></td>
                </tr>
                <tr>
                    <td><label>E-Mail : </label></td>
                    <td><input type="email" name="email" required/></td>
                    <td><label>E-Mail Again : </label></td>
                    <td><input type="email" name="email2"required/></td>
                </tr>
            </table>
            <input type="hidden" name="action" value="verifycregistration">
            <input type="submit" value="Register"/>
        </form>
    </div>
    <div id="ft"></div>
</html>

