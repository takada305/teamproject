<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="header.jsp" %>
<%@ page import="dao.Customer, java.util.List" %>

<%
    List<String> districtList = (List<String>) request.getAttribute("districtList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>顧客情報編集</title>
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
    <h1>顧客編集フォーム</h1>
    <%
        Customer customer = (Customer) request.getAttribute("customer");
        if (customer != null) {
    %>
    <form action="CustomerEditServlet" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" value="<%= customer.getId() %>">

        <label for="name">氏名:</label>
        <input type="text" id="name" name="name" value="<%= customer.getName() %>" required><br>

        <label for="kana">かな:</label>
        <input type="text" id="kana" name="kana" value="<%= customer.getKana() %>" required><br>

        <label for="postCode">郵便番号:</label>
        <input type="text" id="postCode" name="postCode" value="<%= customer.getPostCode() %>" required><br>
        
        <label for="district">地区:</label>
        <select id="district" name="district" required>
            <option value="未設定" <%= customer != null && customer.getDistrict() == null || customer.getDistrict().isEmpty() ? "selected" : "" %>>未設定</option>
            <%
                if (districtList != null && !districtList.isEmpty()) {
                    for (String district : districtList) {
            %>
            <option value="<%= district %>" <%= district.equals(customer != null && customer.getDistrict() != null ? customer.getDistrict() : "") ? "selected" : "" %>><%= district %></option>
            <%
                    }
                } else {
            %>
            <option value="エラー">地区リスト取得失敗</option>
            <%
                }
            %>
        </select><br> 

        <label>性別:</label>
        <input type="radio" id="male" name="gender" value="男" <%= customer.getGender().equals("男") ? "checked" : "" %>>
        <label for="male">男</label>
        <input type="radio" id="female" name="gender" value="女" <%= customer.getGender().equals("女") ? "checked" : "" %>>
        <label for="female">女</label><br>

        <label for="birthday">生年月日:</label>
        <input type="date" id="birthday" name="birthday" value="<%= customer.getBirthday() %>" required><br>

        <label for="phoneNumber">電話番号:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" value="<%= customer.getPhoneNumber() %>" required><br>

        <button type="submit">編集確定</button>
        <button type="reset">クリア</button>
        <br>
        <a href="menu.jsp" class="button">メニュー画面へ</a>
    </form>
    <%
        } else {
    %>
    <p>顧客情報が見つかりません。</p>
    <a href="CustomerListServlet" class="button">顧客一覧に戻る</a>
    <%
        }
    %>
</body>
</html>
