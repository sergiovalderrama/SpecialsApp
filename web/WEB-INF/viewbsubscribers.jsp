<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/main.css">
        <title>JSP Page</title>
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
        <div id="Title"><h1>Subscribed Customers</h1></div>
        <table id="SubscribedFilter">
            <tr>
                <td>
                    <form action="ViewSubscribedCustomers" method="post">
                        <input type="submit" value="View All Subscribed Customers"/>
                    </form>
                </td>
                <td>
                    <form action="ViewSubscribedCustomers" method="post">
                        <input type="hidden" name="action" value="profiles"/>
                        <input type="submit" value="View Customers With Profiles"/>
                    </form>
                </td>
            </tr>
        </table>
        <c:if test="${customers != null}">
            <table id="SubscribedCustomers">
                <tr>
                    <th>
                        Username
                    </th>
                    <th>
                        E-Mail
                    </th>
                </tr>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td>
                            ${customer[0]}
                        </td>
                        <td>
                            <a href="mailto:${customer[1]}">${customer[1]}</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${cprofile != null}">
            <table id="SubscribedCustomers">
                <tr>
                    <th>
                        Name
                    </th>
                    <th>
                        Username
                    </th>
                    <th>
                        E-Mail
                    </th>
                </tr>
                <c:forEach var="profile" items="${cprofile}">
                    <tr>
                        <td>
                            ${profile[0]} &nbsp; ${profile[1]}
                        </td>
                        <td>
                            ${profile[3]}
                        </td>
                        <td>
                            <a href="mailto:${profile[2]}">${profile[2]}</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${customers != null}">
            <table id="EmailList">
                <tr>
                    <th>
                        <b>E-Mail list for all customers</b>
                    </th>
                </tr>

                <tr>
                    <td id="tdEmail">
                        <c:forEach var="email" items="${customers}">
                            <c:out value="${email[1]}"/>,
                        </c:forEach>
                    </td>
                </tr>

            </table>
        </c:if>
        <br>


    </body>
</html>
