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
            <div id="menuTitle"><h1>Business Menu Page</h1></div>
            <div id="bMenu">
                <table id="menuTable">
                    <tr>
                        <td>
                            <h3>Username: <c:out value="${buser.username}"/></h3>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="POST" action="BusinessLogout">
                                <input id="menuButton" type="submit" value="Logout"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="POST" action="BusinessPost">
                                <input id="menuButton" type="submit" value="Post Special"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="POST" action="BusinessPicture">
                                <input type="hidden" name="action" value="picPage"/>
                                <input id="menuButton" type="submit" value="Business Picture"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="POST" action="EditBusinessProfile">
                                <input id="menuButton" type="submit" value="View/Edit Profile"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form method="post" action="ViewSubscribedCustomers">
                                <input id="menuButton" type="submit" value="View Subscribed Customers"/>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form mehod="post" action="BusinessIndividualComments"/>
                                <input id="menuButton" type="submit" value="View Customer Comments"/>
                            </form>
                        </td>
                    </tr>
            </div>
        </div>
        <div id="ft"></div>
    </body>
</html>
