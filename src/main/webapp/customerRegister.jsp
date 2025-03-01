<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>

<%
    List<String> districtList = (List<String>) request.getAttribute("districtList");
    System.out.println("JSPで受け取った地区リスト: " + districtList);

    if (districtList == null) {
        System.out.println("JSPの時点で districtList が null ");
    } else if (districtList.isEmpty()) {
        System.out.println("JSPの時点で districtList が空");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>顧客登録フォーム</title> 
    <style>
        body {
            text-align: center;
            font-family: Arial, sans-serif;
        }
        form {
            margin: 0 auto; 
            display: inline-block; 
        }
        .button {
            margin-top: 20px;
            padding: 5px 10px; 
            background-color: #E0E0E0;
            border: 1px solid black;
            text-decoration: none;
            color: black;
            border-radius: 5px;
            display: inline-block;
        }
        select {
            width: 50%; 
        }
    </style>
</head>
<body>
    <h1>顧客登録フォーム</h1>
     <form action="CustomerRegisterServlet" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" value="${customer.id}">

        <label for="customerName">氏名</label>
        <input type="text" id="customerName" name="customerName" placeholder="山田太郎" required><br>

        <label for="customerKana">かな</label>
        <input type="text" id="customerKana" name="customerKana" placeholder="やまだたろう" required><br>

        <label for="postCode">郵便番号</label>
        <input type="text" id="postCode" name="postCode" placeholder="8120037" required><br>

        <label for="district">地区</label>
        <select id="district" name="district" required>
            <option value="未設定" selected>未設定</option>
            <%
                if (districtList != null) {
                    for (String district : districtList) {
            %>
                <option value="<%= district %>"><%= district %></option>
            <%
                    }
                } else {
            %>
                <option value="エラー">地区リスト取得失敗</option>
            <%
                }
            %>
        </select><br>

        <label for="gender">性別</label>
        <input type="radio" id="male" name="gender" value="男" required>男
        <input type="radio" id="female" name="gender" value="女">女<br>

        <label for="birthday">生年月日</label>
        <input type="text" id="birthday" name="birthday" placeholder="19910105" required><br>

        <label for="phoneNumber">電話番号</label>
        <input type="text" id="phoneNumber" name="phoneNumber" placeholder="09011112222" required><br>

        <button type="submit">顧客登録確定</button>
        <button type="reset">クリア</button>
        <br> 
        <a href="menu.jsp" class="button">メニュー画面へ</a>
    </form>
</body>
</html>
