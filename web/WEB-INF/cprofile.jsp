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
        <form action="InsertCustomerProfile" method="post">
            <input type ="hidden" name="action" value="edit"/>
            <input type ="submit" value="Edit Profile"/>
        </form>
        <form action="InsertCustomerProfile" method="post">
            <table>
                <tr>
                    <td>
                        <label>First Name :</label>
                        <input type="text" name="fname" value="<c:out value="${cprofile.fname}"/>" <c:out value="${disabled}"/> required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Last Name :</label>
                        <input type="text" name="lname" value="<c:out value="${cprofile.lname}"/>" <c:out value="${disabled}"/> required/>
                    </td>
                </tr>
                <c:if test="${disabled == null}">
                <tr>
                    <td>
                    <input type="hidden" name="action" value="insert"/>
                    <input type="submit" value="Submit"/>
                    </td>
                </tr>
                </c:if>
            </table>
        </form>
                    <a href="home">Home</a><br>
                    <c:if test="${cprofile != null}">
                    <a href="CustomerPicture">Upload A Picture</a>
                    </c:if>
    </body>
</html>
