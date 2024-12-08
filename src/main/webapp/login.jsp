<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン画面</title>
    <style>
        body {
            text-align: center;
            font-family: Arial, sans-serif;
        }
        .error-message {
            color: red;
            margin-top: 10px;
        }
        .logo {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <!-- ロゴ -->
    <div class="logo">
        <img src="/Users/takadaakira/Desktop/ログイン画面 2.jpg" alt="SE Assist Logo" width="150">
    </div>

    <h1>顧客管理システム</h1>
    <!-- ログインフォーム -->
    <form action="LoginServlet" method="post">
        <label for="userId">ユーザ名:</label>
        <input type="text" id="userId" name="userId" required><br><br>
        <label for="password">パスワード:</label>
        <input type="password" id="password" name="password" required><br><br>
        <button type="submit">ログイン</button>
    </form>

    <!-- エラーメッセージの表示 -->
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p class="error-message"><%= errorMessage %></p>
    <%
        }
    %>
</body>
</html>
