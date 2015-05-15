<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Edit Business Profile</title>
    </head>
    <body>
        <div id="hd"></div>
        <div id="bd">
            <table>
                <tr>
                    <td>
                        <form action="EditBusinessProfile" method="Post">
                            <input type="hidden" name="action" value="edit"/>
                            <input type="submit" value="Edit Profile"/>
                        </form>
                    </td>
                </tr>
            </table>
            <form action="EditBusinessProfile" method="post">
                <table>
                    <tr>
                        <td>
                            <label>
                                Name:
                            </label>
                        </td>
                        <td>
                            <input type="text" name="bname" value="${bprofile.bname}" 
                                   <c:out value="${disabled}"/> />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Phone Number:</label>
                        </td>
                        <td>
                            <input type="text" name="phone" value="${bprofile.phone}"
                                   <c:out value="${disabled}"/> />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Address:</label>
                        </td>
                        <td>
                            <input type="text" name="address" value="${bprofile.address}"
                                   <c:out value="${disabled}"/> />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>City:</label>
                        </td>
                        <td>
                            <input type="text" name="city" value="${bprofile.city}"
                                   <c:out value="${disabled}"/> />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>State:</label>  
                        </td>
                        <td>
                            <input type="text" name="bstate" value="${bprofile.bstate}"
                                   <c:out value="${disabled}"/> />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Zipcode:</label>
                        </td>
                        <td>
                            <input type="text" name="zipcode" value="${bprofile.zipcode}"
                                   <c:out value="${disabled}"/> />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Website:</label>
                        </td>
                        <td>
                            <input type="text" name="website" value="${bprofile.website}"
                                   <c:out value="${disabled}"/> />
                        </td>
                    </tr>
                    <tr>
                        <c:if test="${disabled == null}">
                            <td>
                                <input type="hidden" name="action" value="update"/>
                                <input type="submit" name="Update Profile"/>
                            </td>
                        </c:if>
                    </tr>
                </table>
            </form>
            <a href="BusinessMenu">Main Menu</a>
        </div>
        <div id="ft"></div>
    </body>
</html>
