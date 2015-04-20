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
            <form>
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
            <h1>Business Login</h1>
            <h3><c:out value="${flash}"/></h3>
            <form method="POST" action="controller">
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
        </div>
        <div id="ft"></div>
    </body>
</html>
