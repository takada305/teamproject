<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ page import="dao.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>顧客詳細</title>
</head>
<body>
    <h1>顧客詳細</h1>
    <%
        boolean isViewer = (session != null) && Boolean.TRUE.equals(session.getAttribute("isViewer"));
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
    <br>
    <%
        if (!isViewer) {
    %>
    <!-- 編集ボタン -->
    <form action="CustomerEditServlet" method="get">
        <input type="hidden" name="id" value="<%= customer.getId() %>">
        <button type="submit">編集</button>
    </form>
    <br>
    <!-- 削除ボタン -->
    <form action="CustomerDeleteConfirmServlet" method="get">
        <input type="hidden" name="id" value="<%= customer.getId() %>">
        <button type="submit">削除</button>
    </form>
    <%
        }
    %>
    <br>
    <a href="CustomerListServlet">顧客一覧</a>
    <%
        } else {
    %>
    <p>顧客情報が見つかりません。</p>
    <a href="CustomerListServlet">顧客一覧</a>
    <%
        }
    %>
</body>
</html>
