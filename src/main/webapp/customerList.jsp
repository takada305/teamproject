<%@ page import="java.util.List" %>
<%@ page import="dao.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>顧客一覧</title>
</head>
<body>
    <h1>顧客一覧</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>氏名</th>
            <th>かな</th>
            <th>郵便番号</th>
            <th>住所</th>
            <th>性別</th>
            <th>生年月日</th>
            <th>電話番号</th>
            <th>操作</th>
        </tr>
        <%
            // 顧客リストをリクエスト属性から取得
            List<Customer> customers = (List<Customer>) request.getAttribute("customerList");
            if (customers != null && !customers.isEmpty()) {
                // 顧客リストが存在する場合、各顧客情報表示
                for (Customer customer : customers) {
        %>
        <tr>
            <td><%= customer.getId() %></td>
            <td><%= customer.getName() %></td>
            <td><%= customer.getKana() %></td>
            <td><%= customer.getPostCode() %></td>
            <td><%= customer.getAddress() %></td>
            <td><%= customer.getGender() %></td>
            <td><%= customer.getBirthday() %></td>
            <td><%= customer.getPhoneNumber() %></td>
            <td>
                <a href="CustomerDetailServlet?id=<%= customer.getId() %>">詳細を見る</a> <!-- 詳細リンク -->
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="9">データがありません。</td> <!-- データがない場合 -->
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <a href="menu.jsp">メニューに戻る</a> <!-- メニュー画面へ -->
</body>
</html>
