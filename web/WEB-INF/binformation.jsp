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
        <h1><hr></h1>
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
        <table id="bInformationTable">
            <tr>
                <th>
                    Business Information
                    <span class="starRatingAvg">
                        <input id="averageRating5" type="radio" name="averageRating" value="5" 
                               <c:out value="${averageRating5}"/> disabled/>
                        <label for="averageRating5">5</label>
                        <input id="averageRating4" type="radio" name="averageRating" value="4" 
                               <c:out value="${averageRating4}"/> disabled/>
                        <label for="averageRating4">4</label>
                        <input id="averageRating3" type="radio" name="averageRating" value="3"
                               <c:out value="${averageRating3}"/> disabled/>
                        <label for="averageRating3">3</label>
                        <input id="averageRating2" type="radio" name="averageRating" value="2" 
                               <c:out value="${averageRating2}"/> disabled/>
                        <label for="averageRating2">2</label>
                        <input id="averageRating1" type="radio" name="averageRating" value="1" 
                               <c:out value="${averageRating1}"/>disabled>
                        <label for="averageRating1">1</label>
                    </span>
                    <font size="3">(avg rating)</font>
                </th>
            </tr>
            <tr>
                <td>
                    <b>Name:</b> <c:out value="${bprofile.bname}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Phone:</b> <c:out value="${bprofile.phone}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Address:</b> <c:out value="${bprofile.address}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <b>Website:</b> <c:out value="${bprofile.website}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <hr>
                </td>
            </tr>
            <tr>
                <td>
                    <form action="BusinessInformation" method="Post">
                        Rate Business
                        <span class="starRating" >
                            <input id="rating5" type="radio" onclick="submit()" name="rating" value="5" 
                                   <c:out value="${star5}"/>/>
                            <label for="rating5">5</label>
                            <input id="rating4" type="radio" onclick="submit()" name="rating" value="4" 
                                   <c:out value="${star4}"/> />
                            <label for="rating4">4</label>
                            <input id="rating3" type="radio" onclick="submit()" name="rating" value="3" 
                                   <c:out value="${star3}"/> />
                            <label for="rating3">3</label>
                            <input id="rating2" type="radio" onclick="submit()" name="rating" value="2" 
                                   <c:out value="${star2}"/> />
                            <label for="rating2">2</label>
                            <input id="rating1" type="radio" onclick="submit()" name="rating" value="1" 
                                   <c:out value="${star1}"/> />
                            <label for="rating1">1</label>
                        </span>
                        <input type="hidden" name="action" value="rate"/>
                        <input type="hidden" name="pid" value="${bprofile.id}"/>
                        <input type="hidden" name="bid" value="${bprofile.buserid.id}"/>
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
