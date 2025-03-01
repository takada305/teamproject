<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
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
            font-size: 16px;
            margin: 10px 0 20px 0;
        }
        .logo {
            margin-bottom: 20px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-top: 10px;
        }
        input {
            width: 300px;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            display: block;
            margin: 0 auto; 
            padding: 10px 20px;
            font-size: 16px;
            color: black;
            background-color: #E0E0E0; 
            border: 1px solid #ccc; 
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: gray;
        }
    </style>
</head>
<body>
    <div class="logo">
        <img src="images/図1.png" alt="SE Assist Logo" width="150">
    </div>

    <h1>顧客管理システム</h1>

   <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <p class="error-message"><%= errorMessage %></p>
    <%
        }
    %>

    <form action="LoginServlet" method="post">
        <label for="userId">ユーザ名</label>
        <input type="text" id="userId" name="userId" required>

        <label for="password">パスワード</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">ログイン</button>
    </form>
</body>
</html>
