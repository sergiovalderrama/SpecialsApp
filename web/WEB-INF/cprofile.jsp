<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="css/html" href="styles/main.css">
        <title>Customer Profile</title>
    </head>
    <body>
        <div id="hd">Specials App</div>
        <div id="NavigationMenu">
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
                        <td>
                            <form action="InsertCustomerProfile" method="post">
                                <input type="submit" value="Add/Edit Profile"/>
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
        </div>
        <br>
        <div id="Title"><h1>Customer Profile</h1></div>
        <div id="LoginWhiteSpace">
            <table id="InsertCustomerProfile">
                <form action="InsertCustomerProfile" method="post">
                    <tr>
                        <td>
                            <input type ="hidden" name="action" value="edit"/>
                            <input type ="submit" value="Edit Profile"/>
                        </td>
                    <tr>
                </form>
                <form action="InsertCustomerProfile" method="post">
                    <tr>
                        <td>
                            <label>First Name :</label>
                        </td>
                        <td>
                            <input type="text" name="fname" value="<c:out value="${cprofile.fname}"/>" <c:out value="${disabled}"/> required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Last Name :</label>
                        </td>
                        <td>
                            <input type="text" name="lname" value="<c:out value="${cprofile.lname}"/>" <c:out value="${disabled}"/> required/>
                        </td>
                    </tr>
                    <tr>
                        <c:if test="${disabled == null}">
                            <td>
                                <input type="hidden" name="action" value="insert"/>
                                <input type="submit" value="Submit"/>
                            </td>
                        </c:if>
                </form>
                <c:if test="${cprofile != null}">
                    <td>
                        <form action ="CustomerPicture" method="post">
                            <input type="submit" value="Upload A Profile Picture"/>
                        </form>
                    </td>
                </c:if>
                </tr>
            </table>
        </div>
    </body>
</html>
