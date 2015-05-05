<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Business Login Page</title>
    </head>
    <body>
        <div id="hd"></div>
        <div id="bd">
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
                    <td>
                    <td>
                        <form action="CustomerLogin" method="Post">
                            <input type="submit" value="Customer Login"/>
                        </form>
                    </td>
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
            <h1>Business Login</h1>
            <h3><c:out value="${flash}"/></h3>
            <form method="POST" action="BusinessLogin">
                <table>
                    <tr>
                        <td>
                            <label>Username : </label>
                        </td>
                        <td>
                            <input type="text" name="username" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Password : </label>
                        </td>
                        <td>
                            <input type="password" name="password" required/>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="action" value="verifyblogin"/>
                <input type="submit" value="Login"/>
            </form>
            <br>
            <a href="BusinessRegistration">Create a business login</a>
        </div>
        <div id="ft"></div>
    </body>
</html>
