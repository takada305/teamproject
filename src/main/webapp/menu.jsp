<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>メニュー画面</title>
    <style>
        body {
            text-align: center;
            font-family: Arial, sans-serif;
        }
        .menu-button {
            display: block;
            margin: 10px auto;
            padding: 10px 20px;
            font-size: 16px;
            background-color: #E0E0E0;
            color: black;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .menu-button:hover {
            background-color: #E0E0E0;
        }
        form {
            margin: 0; 
        }
    </style>
</head>
<body>
    <h1>顧客管理システム</h1>
    <%
        // 権限コード取得
        String authority = (String) session.getAttribute("authority");

        if (authority == null) {
            authority = "A0"; 
        }
    %>

    <!-- 顧客一覧 -->
    <form method="get" action="CustomerListServlet">
        <button type="submit" class="menu-button">顧客一覧</button>
    </form>
    <%
        if (authority.equals("A1") || authority.equals("A2")) {
    %>
    <!-- 顧客登録 -->
	<form method="get" action="CustomerRegisterServlet">
   		 <button type="submit" class="menu-button">顧客登録</button>
	</form>
    <%
        }
        if (authority.equals("A2")) {
    %>
    <!-- 権限編集 -->
    <form method="post" action="AuthorityEditServlet">
        <button type="submit" class="menu-button">権限編集</button>
    </form>
    <%
        }
    %>
</body>
</html>
