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
        <h1>Business Information</h1>
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
        </table>
    </body>
</html>
