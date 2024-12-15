<%@ page import="dao.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>顧客詳細</title>
</head>
<body>
    <h1>顧客詳細</h1>
    <%
  
        Customer customer = (Customer) request.getAttribute("customer");
        if (customer != null) {
    %>
    <table border="1">
        <tr><th>ID</th><td><%= customer.getId() %></td></tr>
        <tr><th>氏名</th><td><%= customer.getName() %></td></tr>
        <tr><th>かな</th><td><%= customer.getKana() %></td></tr>
        <tr><th>郵便番号</th><td><%= customer.getPostCode() %></td></tr>
        <tr><th>住所</th><td><%= customer.getAddress() %></td></tr>
        <tr><th>性別</th><td><%= customer.getGender() %></td></tr>
        <tr><th>生年月日</th><td><%= customer.getBirthday() %></td></tr>
        <tr><th>電話番号</th><td><%= customer.getPhoneNumber() %></td></tr>
    </table>
    <%
        } else {
    %>
    <p>顧客情報が見つかりません。</p>
    <%
        }
    %>
    <br>
    <a href="CustomerListServlet">顧客一覧に戻る</a>
</body>
</html>