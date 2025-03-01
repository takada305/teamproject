<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ page import="dao.Customer" %>
<!DOCTYPE html>
<html>
<head>
    <title>顧客詳細</title>
    <style>
        body {
            text-align: center; 
        }
        h1 {
            text-align: center;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border: 1px double black;
        }
        th, td {
            border: 1px double black;
            padding: 10px;
            text-align: center;
            white-space: nowrap; 
        }
        th {
            min-width: 120px; 
        }
        td {
            min-width: 120px;
        }
        .button-container {
            text-align: center; 
            margin-top: 20px; 
        }
        .button {
            padding: 5px 10px; 
            font-size: 18px;
            color: black;
            background-color: #E0E0E0;
            border: 1px solid black;
            border-radius: 8px; 
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>顧客詳細</h1>
    <%
        String authority = (String) session.getAttribute("authority"); // 権限コードをセッションから取得
        Customer customer = (Customer) request.getAttribute("customer");

        if (customer != null) {  
    %>
    <table>
        <tr>
            <th>顧客ID</th>
            <th>氏名</th>
            <th>かな</th>
            <th>郵便番号</th>
            <th>地区</th>
            <th>性別</th>
            <th>生年月日</th>
            <th>電話番号</th>
            <th>登録日時</th> 
            <th>更新日時</th> 
            <% if (!"A0".equals(authority)) { %> 
                <th>編集</th>
                <th>削除</th>
            <% } %>
        </tr>
        <tr>
            <td><%= customer.getId() %></td>
            <td><%= customer.getName() %></td>
            <td><%= customer.getKana() %></td>
            <td><%= customer.getPostCode() %></td>
            <td><%= customer.getDistrict() %></td>
            <td><%= customer.getGender() %></td>
            <td><%= customer.getBirthday() %></td>
            <td><%= customer.getPhoneNumber() %></td>
            <td><%= customer.getCreatedAt() %></td>  
            <td><%= customer.getUpdatedAt() %></td> 
            <% if (!"A0".equals(authority)) { %> 
                <td>
                    <form action="CustomerEditServlet" method="get">
                        <input type="hidden" name="id" value="<%= customer.getId() %>">
                        <button type="submit">編集</button>
                    </form>
                </td>
                <td>
                    <form action="<%= request.getContextPath() %>/CustomerDeleteConfirmServlet" method="get">
                        <input type="hidden" name="id" value="<%= customer.getId() %>">
                        <button type="submit">削除</button>
                    </form>                  
                </td>
            <% } %> 
        </tr>
    </table>
    <br>
    <% } else { %> 
    <p>顧客情報が見つかりません。</p>
    <% } %>

    <div class="button-container">
        <form action="CustomerListServlet" method="get">
            <button type="submit" class="button">顧客一覧</button>
        </form>
    </div>

</body>
</html>
