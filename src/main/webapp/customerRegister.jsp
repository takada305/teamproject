<!DOCTYPE html>
<html>
<head>
    <title>顧客登録フォーム</title>
</head>
<body>
    <h1>顧客登録フォーム</h1>
    <form action="CustomerRegisterServlet" method="post">
        <label for="customerName">氏名:</label>
        <input type="text" id="customerName" name="customerName" placeholder="山田 太郎" required><br>

        <label for="customerKana">かな:</label>
        <input type="text" id="customerKana" name="customerKana" placeholder="やまだ たろう" required><br>

        <label for="postCode">郵便番号:</label>
        <input type="text" id="postCode" name="postCode" placeholder="123-4567" required><br>

        <label for="address">住所:</label>
        <input type="text" id="address" name="address" placeholder="東京都新宿区" required><br>

        <label for="gender">性別:</label>
        <select id="gender" name="gender" required>
            <option value="男">男</option>
            <option value="女">女</option>
        </select><br>

        <label for="birthday">生年月日:</label>
        <input type="text" id="birthday" name="birthday" placeholder="19900101" required><br>

        <label for="phoneNumber">電話番号:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" placeholder="09012345678" required><br>

        <button type="submit">登録</button>
        <button type="reset">クリア</button>
        <a href="menu.jsp">メニュー画面へ</a>
    </form>
</body>
</html>
