<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>ログアウト</title>
    <style>
        body {
            text-align: center;
            font-family: Arial, sans-serif;
        }
        .container {
            display: inline-block;
            text-align: left;
        }
        .button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #E0E0E0;
            border: 1px solid black;
            text-decoration: none;
            color: black;
            border-radius: 5px;
            display: inline-block;
        }
        .button:hover {
            background-color: gray;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>userさん ログアウトしました</h1>
        <a href="login.jsp" class="button">ログイン画面へ</a>
    </div>
</body>
</html>
