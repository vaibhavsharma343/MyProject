<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String userName = (String) session.getAttribute("name_key");
    if(userName == null){
        response.sendRedirect("login_page.html");
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Page</title>

<style>
    body {
        font-family: Poppins, Arial, sans-serif;
        margin: 0;
        padding: 0;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        background: linear-gradient(135deg, #6c63ff, #48a8ff);
    }

    .container {
        width: 400px;
        padding: 40px;
        background: rgba(255, 255, 255, 0.15);
        backdrop-filter: blur(12px);
        border-radius: 15px;
        box-shadow: 0 8px 20px rgba(0,0,0,0.15);
        text-align: center;
        border: 1px solid rgba(255,255,255,0.2);
        animation: fadeIn 0.8s ease-out;
        color: white;
    }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(20px); }
        to { opacity: 1; transform: translateY(0); }
    }

    h1 {
        margin-bottom: 15px;
        font-size: 28px;
        font-weight: 700;
    }

    h2 {
        font-size: 22px;
        margin-top: 10px;
        font-weight: 500;
    }
</style>

</head>
<body>

<div class="container">
<h1>🙏</h1>
    <h1>Welcome to the JSP Page</h1>
    <h2>Welcome , <%=userName %></h2>
</div>

</body>
</html>
