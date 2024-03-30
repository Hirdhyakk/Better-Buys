<%-- 
    Document   : Home
    Created on : 19-Sept-2023, 10:48:49 pm
    Author     : akbri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
        <<link rel="stylesheet" href="style_1.css"/>
    </head>
    <body>
    <center>
        <%--Welcome <%=session.getAttribute("username")%>--%>
        Yours welcome <%=session.getAttribute("username")%>
        <header class="header">
            <a href="#" class="logo">Better Buys</a>
            <i class="bx bx-menu" id="menu-icon"></i>
            <nav class="navbar">
                <a href="#home" class="active">Home</a>
                <a href="#service">Categories</a>
                <a href="#portfolio">Contact Us</a>
                <a href="#LogoutServlet">Log Out</a>
            </nav>
        </header>
    </center>
</body>
</html>
