<!DOCTYPE html>
<html>
<head>
    <title>権限編集</title>
</head>
<body>
    <h1>権限編集</h1>
    <form action="AuthorityEditServlet" method="post">
        <label for="userId">ユーザーID:</label>
        <input type="text" id="userId" name="userId" placeholder="ユーザーIDを入力" required><br>

        <label for="authority">新しい権限:</label>
        <select id="authority" name="authority" required>
            <option value="A0">閲覧者</option>
            <option value="A1">編集者</option>
            <option value="A2">管理者</option>
        </select><br>

        <button type="submit">保存</button>
        <a href="menu.jsp">メニュー画面へ戻る</a>
    </form>
</body>
</html>
