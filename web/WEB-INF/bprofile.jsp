<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Business Profile</title>
    </head>
    <body>
        <div id="hd"></div>
        <div id="bd">
            <table style="float : right">
                <tr>
                    <td>
                        User: <c:out value="${buser.username}"/>
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
                        <form action="CustomerRegistration" method="Post">
                            <input type="submit" value="Customer Registration"/>
                        </form>
                    </td>
                    <td>
                        <form action="BusinessRegistration" method="Post">
                            <input type="submit" value="Business Registration"/>
                        </form>
                    </td>
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
            
            <h1>Business Profile</h1>
            <h3><c:out value="${flash}"/></h3>
            <form method="POST" action="InsertBusinessProfile">
                <table>
                    <tr>
                        <th colspan="2">
                            Bussiness Profile
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <label>Business Name : </label>
                        </td>
                        <td>
                            <input type="text" name="bname" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Business Phone : </label>
                        </td>
                        <td>
                            <input type="tel" name="bphone" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Business Address : </label>
                        </td>
                        <td>
                            <input type="text" name="baddress" required/>
                        </td>
                    </tr> 
                    <tr>
                        <td>
                            <label>Business Website : </label>
                        </td>
                        <td>
                            <input type="text" name="bwebsite" required/>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <label>Business Picture : </label>
                        </td>
                        <td>
                            <input type="file" name="bpicture"/>
                        </td>
                    </tr>
                </table>
                <br>
                <input type="hidden" name="action" value="bprofile"/>
                <input type="submit" value="Submit"/>
            </form>
        </div>
        <div id="ft"></div>
    </body>
</html>
