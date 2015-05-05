<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title> Business Information </title>
    </head>
    <body>
        <c:if test="${sessionScope.cuser != null}">
                <table style="float :right">
                    <tr>
                        <td>
                            User: &nbsp;<c:out value="${cuser.username}"/>
                        </td>
                        <td>
                            <form action="CustomerLogout" method="Post">
                                <input type="submit" value="Logout"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </c:if>
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
                <c:if test="${sessionScope.cuser != null}">
                <td>
                    <form action="ViewSubscriptions" method="Post">
                        <input type="submit" value="View Subscriptions"/>
                    </form>
                </td>
                </c:if>
                <c:if test="${sessionScope.cuser == null}">
                <td>
                    <form action="CustomerLogin" method="Post">
                        <input type="submit" value="Customer Login"/>
                    </form>
                </td>
                </c:if>
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
        <h1>Business Information</h1>
        <h3><c:out value="${flash}"/></h3>
        <table style="background-color: white">
            <tr>
                <td>
                    <label>Subscribe</label>
                </td>
                <td>
                    <form action="BusinessInformation" method="Post">
                        <input type="hidden" name="action" value="subscribe"/>
                        <input type="hidden" name="pid" value="${bprofile.id}"/>
                        <input type="hidden" name="bid" value="${bprofile.buserid.id}"/>
                        <input type="submit" value="X"/>
                    </form>
                </td>
                <td>
                    <label>View/Add Reviews</label>
                </td>
                <td>
                    <form action="ReviewsOnBusiness" method="Post">
                        <input type="hidden" name="action" value="viewreviews">
                        <input type="hidden" name="pid" value="${bprofile.id}"/>
                        <input type="hidden" name="bid" value="${bprofile.buserid.id}"/>
                        <input type="submit"  value="X"/>
                    </form>
                </td>
            </tr>
        </table>
        <table>
            <tr>
                <th>
                    Business Information
                </th>
            </tr>
            <tr>
                <td>
                    <b>Business Name:</b> <c:out value="${bprofile.bname}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Business Phone:</b> <c:out value="${bprofile.phone}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Business Address:</b> <c:out value="${bprofile.address}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Business Website:</b> <c:out value="${bprofile.website}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <hr>
                </td>
            </tr>

        </table>
        
    </body>
</html>
