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
        <div id="hd">Specials App</div>
        <div id="NavigationMenu">
            <table style="float : right">
                <tr>
                    <td>
                        Username: <c:out value="${buser.username}"/>
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
                        <form method="POST" action="BusinessPost">
                            <input id="NavigationButton" type="submit" value="Post Special"/>
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="BusinessPicture">
                            <input type="hidden" name="action" value="picPage"/>
                            <input id="NavigationButton" type="submit" value="Business Picture"/>
                        </form>
                    </td>
                    <td>
                        <form method="POST" action="EditBusinessProfile">
                            <input id="NavigationButton" type="submit" value="View/Edit Profile"/>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="ViewSubscribedCustomers">
                            <input id="NavigationButton" type="submit" value="View Subscribed Customers"/>
                        </form>
                    </td>
                    <td>
                        <form mehod="post" action="BusinessIndividualComments"/>
                        <input id="NavigationButton" type="submit" value="View Customer Comments"/>
                        </form>
                    </td>
                    <td>
                        <form mehod="post" action="BusinessChangePassword"/>
                        <input id="NavigationButton" type="submit" value="Change Password"/>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
        <div id="Title"><h1>Edit Business Profile</h1></div>
        <div id="BprofileWhiteSpace">
            <table id="EditBusinessProfile">
                <tr>
                    <td>
                        <form action="EditBusinessProfile" method="Post">
                            <input type="hidden" name="action" value="edit"/>
                            <input type="submit" value="Edit"/>
                        </form>
                    </td>
                </tr>
                <form action="EditBusinessProfile" method="post">
                    <tr>
                        <td>
                            <label>
                                Name:
                            </label>
                        </td>
                        <td>
                            <input type="text" name="bname" value="${bprofile.bname}" 
                                   <c:out value="${disabled}"/> required/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Phone Number:</label>
                        </td>
                        <td>
                            <input type="text" name="phone" value="${bprofile.phone}"
                                   <c:out value="${disabled}"/>  required />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Address:</label>
                        </td>
                        <td>
                            <input type="text" name="address" value="${bprofile.address}"
                                   <c:out value="${disabled}"/> required />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>City:</label>
                        </td>
                        <td>
                            <input type="text" name="city" value="${bprofile.city}"
                                   <c:out value="${disabled}"/> required />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>State:</label>  
                        </td>
                        <td>
                            <input type="text" name="bstate" value="${bprofile.bstate}"
                                   <c:out value="${disabled}"/>  required />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Zipcode:</label>
                        </td>
                        <td>
                            <input type="text" name="zipcode" value="${bprofile.zipcode}"
                                   <c:out value="${disabled}"/> required />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Website:</label>
                        </td>
                        <td>
                            <input type="text" name="website" value="${bprofile.website}"
                                   <c:out value="${disabled}"/> required />
                        </td>
                    </tr>
                    <tr>
                        <c:if test="${disabled == null}">
                            <td>
                                <input type="hidden" name="action" value="update"/>
                                <input type="submit" value="Update Profile"/>
                            </td>
                        </c:if>
                    </tr>
                </form>
            </table>
        </div>
        <div id="ft"></div>
    </body>
</html>
