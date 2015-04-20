<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Business Menu</title>
    </head>
    <body>

        <c:out value="${flash}"/>
        <div id="hd"></div>
        <div id="bd">
            <table>
                <tr>
                    <td>
                        <h3>Username: <c:out value="${buser.username}"/></h3>
                    </td>
                    <td>
                         <form method="POST" action="controller">
                            <input type="hidden" name="action" value="blogout"/>
                            <input type="submit" value="Logout"/>
                        </form>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td>
                        <form method="POST" action="controller">
                            <input type="hidden" name="action" value="bmenu"/>
                            <input type="submit" value="Business Menu"/>
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="controller">
                            <input type="hidden" name="action" value="bpost"/>
                            <input type="submit" value="Post Special"/>
                        </form>
                    </td>
                </tr>
            </table>
            <h1>Business Menu Page</h1>
        </div>
        <div id="ft"></div>
    </body>
</html>
