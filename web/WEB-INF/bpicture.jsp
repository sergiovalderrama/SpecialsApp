<%@page contentType="text/html" pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Business Picture</title>
    </head>
    <body>
        <div id="hd"></div>
        <div id="bd">
            <h1>Upload A Picture Of Your Business</h1>
            <br><c:out value="${flash}"/><br>
            <form method="post" action="UploadBusinessPicture"  enctype="multipart/form-data">
                <input type="file" name="pic" id="pic"/>
                <input type="submit" value="Upload Picture"/>
            </form>
            <c:choose>
                <c:when test="${bprofile.picture != null}">
                    <img id="BusinessImage" src="ViewBusinessPicture?for=${bprofile.buserid.id}"/>
                </c:when>
                <c:otherwise>
                    <img src="images/noimage.jfif"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="ft"></div>
    </body>
</html>
