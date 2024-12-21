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
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .menu-button:hover {
            background-color: #45a049;
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

    <form method="post" action="CustomerListServlet">
        <button type="submit" class="menu-button">顧客一覧</button>
    </form>

    <%
        if (authority.equals("A1") || authority.equals("A2")) {
    %>
    <form method="post" action="CustomerRegisterServlet">
        <button type="submit" class="menu-button">顧客登録</button>
    </form>
    <%
        }
        if (authority.equals("A2")) {
    %>
    <form method="post" action="AuthorityEditServlet">
        <button type="submit" class="menu-button">権限編集</button>
    </form>
    <%
        }
    %>
</body>
</html>
