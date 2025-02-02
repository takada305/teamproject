<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
        }
        .header img {
            height: 60px; 
        }
        .logout-button {
            background-color: lightgray;
            border: 1px solid black;
            padding: 5px 10px;
            text-decoration: none;
            color: black;
            border-radius: 5px;
            cursor: pointer;
        }
        .logout-button:hover {
            background-color: gray;
        }
    </style>
</head>
<body>
    <div class="header">
        <!-- ロゴ -->
        <img src="images/図1.png" alt="SE Assist Logo">
        <!-- ログアウトボタン -->
		<form action="<%= request.getContextPath() %>/LogoutServlet" method="get" style="margin: 0;">
    		<button type="submit" class="logout-button">ログアウト</button>
		</form>
    </div>
</body>
</html>
