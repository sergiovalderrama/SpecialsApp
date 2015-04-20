<%@page contentType="text/html" pageEncoding="UTF-8"trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About The Specials Website</title>
        <link rel="stylesheet" type="text/css" href="styles/main.css">
    </head>
    <body>
        <div id="hd"></div>
        <div id="bd">
            <form>
                <table>
                    <tr>
                        <td>
                            <button name="action" value="home">Home</button>
                        </td>
                        <td>
                            <button name="action" value="about">About</button>
                        </td>
                        <td>
                            <button name="action" value="cregistration">Customer Registration</button>
                        </td>
                        <td>
                            <button name="action" value="bregistration">Business Registration</button>
                        </td>
                        <td>
                            <button name="action" value="blogin">Business Login</button>
                        </td>
                    </tr>
                </table>
            </form>
            <h1>About The Specials Website</h1>
            <p>
                This website allows customers to view specials posted by businesses. 
                Customers can view happy hour, brunch, lunch, dinner, and breakfast specials.  
                Customers can post reviews about businesses, rate businesses, and subscribe to businesses. 
                Businesses are able to view customer reviews and customers that are subscribed to their business.
            </p>
        </div>
        <div id="ft"></div>
    </body>
</html>
