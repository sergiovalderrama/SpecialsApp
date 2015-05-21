<%@page contentType="text/html" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>Business Picture</title>
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
        <div id="Title"><h1>Upload A Picture Of Your Business</h1></div>
        <br><c:out value="${flash}"/><br>
        <div id="PictureBTable">
        <form method="post" action="UploadBusinessPicture"  enctype="multipart/form-data">
            <table id="InsertPictureTable">
                <tr>
                    <td>
                        <input type="file" name="pic" id="pic" required/>
                    </td>
                    <td>
                        <input type="submit" value="Upload Picture"/>
                    </td>
            </table>
        </form>
        </div>
        <c:choose>
            <c:when test="${bprofile.picture != null}">
                <img id="DisplayPicBprofile"  src="ViewBusinessPicture?for=${bprofile.buserid.id}"/>
            </c:when>
            <c:otherwise>
                <img id="DisplayPicBprofile" src="images/noimage.jfif"/>
            </c:otherwise>
        </c:choose>
        <div id="ft"></div>
    </body>
</html>
