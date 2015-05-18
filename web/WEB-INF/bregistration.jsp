<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Business Registration Page</title>
    </head>
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
                </tr>
            </table>
        </div>
        <div id="Title"><h1>Business Registration Page</h1></div>
        <h2 class="flash"><c:out value="${flash}"/></h2>
        <div id="RegistrationWhiteSpace">
            <form method="POST" action="BusinessRegistration">
                <table id="Registration">
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
                        <td><input type="email" name="email2" required/></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="hidden" name="action" value="verifybregistration">
                            <input type="submit" value="Register"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
</html>
