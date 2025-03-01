<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>権限編集</title>
    <style>
        body {
            text-align: center;
            font-family: Arial, sans-serif;
        }
        .form-container {
            display: grid;
            grid-template-columns: 1fr 1fr;
            grid-template-rows: auto;
            padding: 5px;
            width: 250px;
            margin: 0 auto;
            border: 1px solid #E0E0E0; 
        }
        .form-cell {
            text-align: center;
            padding: 0px;
            font-size: 15px; 
    		font-family: "MS Gothic", "ヒラギノ角ゴ ProN", "メイリオ", sans-serif; 
    		width: 100%; 
    		box-sizing: border-box; 
            
        }
        .form-label {
        	font-weight: bold;
        }
        .form-label, .form-select {
    		display: block;
    		margin-bottom: 2px;
    		border: 1px solid black;
    		padding: 6px;
    		width: 98%;  
    		box-sizing: border-box; 
		}      
        .button {
            margin-top: 10px;
            padding: 5px 10px;
            background-color: #E0E0E0;
            border: 1px solid black;
            text-decoration: none;
            color: black;
            border-radius: 5px;
            display: inline-block;
        }
    </style>
</head>
<body>
    <h1>権限編集フォーム</h1>
    <form action="AuthorityEditServlet" method="post">
     <div class="form-container">
            <div class="form-cell">
                <label for="userId" class="form-label">ユーザー名</label>
                <select id="userId" name="userId" class="form-select" required>
                    <option value="readerU">readerU</option>
                    <option value="editU">editU</option>
                    <option value="managerU">managerU</option>
                </select>
            </div>
            <div class="form-cell">
                <label for="authority" class="form-label">権限</label>
                <select id="authority" name="authority" class="form-select" required>
                    <option value="A0">閲覧者</option>
                    <option value="A1">編集者</option>
                    <option value="A2">管理者</option>
                </select>
            </div>
        </div>
        <br>
        <button type="submit">権限編集確定</button>
        <br>
        <a href="menu.jsp" class="button">メニュー画面へ</a>
    </form>
</body>
</html>
