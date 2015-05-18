<%@page contentType="text/html" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="css/html" href="styles/main.css">
        <title>Customer Picture</title>
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
                        <form action="InsertCustomerProfile" method="post">
                            <input id="NavigationButton" type="submit" value="Back To Profile"/>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
        <div id="Title"><h1>Customer Picture</h1></div>
            <c:choose>
                <c:when test="${cprofile.picture != null}">
                    <img id="DisplayPicProfile" src="ViewCustomerPicture?for=${cprofile.cuserid.id}"/>
                </c:when>
                <c:otherwise>
                    <img id="DisplayPicProfile" src="images/noimage.jfif"/>
                </c:otherwise>
            </c:choose>
        <br><c:out value="${flash}"/><br>
        <form method="post" action="UploadCustomerPicture"  enctype="multipart/form-data">
            <table id="InsertPictureTable">
                <tr>
                    <td>
                        <input type="file" name="pic" id="pic"/>
                    </td>
                    <td>
                        <input type="submit" value="Upload Picture"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>