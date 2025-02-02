<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ page import="dao.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>顧客削除確認</title>
      <style>
        body {
            text-align: center;
        }
       </style>
</head>
<body>
    <h1>顧客削除確認</h1>
    <%
        Customer customer = (Customer) request.getAttribute("customer");
        if (customer != null) {
    %>
    <p>以下の顧客情報を削除してもよろしいですか？</p>
    <table border="1">
        <tr><th>ID</th><td><%= customer.getId() %></td></tr>
        <tr><th>氏名</th><td><%= customer.getName() %></td></tr>
        <tr><th>かな</th><td><%= customer.getKana() %></td></tr>
        <tr><th>郵便番号</th><td><%= customer.getPostCode() %></td></tr>
        <tr><th>住所</th><td><%= customer.getAddress() %></td></tr>
       <%--  <tr><th>地区</th><td><%= customer.getDistrict() %></td></tr>  --%>
        <tr><th>性別</th><td><%= customer.getGender() %></td></tr>
        <tr><th>生年月日</th><td><%= customer.getBirthday() %></td></tr>
        <tr><th>電話番号</th><td><%= customer.getPhoneNumber() %></td></tr>
    </table>
    <form action="CustomerDeleteServlet" method="post">
        <input type="hidden" name="id" value="<%= customer.getId() %>">
        <button type="submit">削除</button>
        <a href="CustomerDetailServlet?id=<%= customer.getId() %>">戻る</a>
    </form>
    <%
        } else {
    %>
    <p>顧客情報が見つかりません。</p>
    <a href="CustomerListServlet">顧客一覧に戻る</a>
    <%
        }
    %>
</body>
</html>
