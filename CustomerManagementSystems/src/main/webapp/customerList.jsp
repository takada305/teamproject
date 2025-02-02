<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="header.jsp" %>
<%@ page import="dao.Customer" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>顧客一覧</title>
    <style>
        body {
            text-align: center;
            font-family: Arial, sans-serif;
        }
        .button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: lightgray;
            border: 1px solid black;
            text-decoration: none;
            color: black;
            border-radius: 5px;
            display: inline-block;
        }
        table {
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h1>顧客一覧</h1>
    <form method="get" action="CustomerListServlet">
        <input type="text" name="search" placeholder="検索">
        <button type="submit">検索</button>
    </form>

    <%
        List<Customer> customers = (List<Customer>) request.getAttribute("customerList");
        if (customers == null || customers.isEmpty()) {
    %>
        <p>検索結果はありません</p>
    <%
        } else {
    %>
    <table>
        <tr>
            <th>顧客ID</th>
            <th>氏名</th>
            <th>かな</th>
            <th>性別</th>
            <th>操作</th>
        </tr>
        <%
            for (Customer customer : customers) {
        %>
		<tr>
    		<td><%= customer.getId() %></td>
    		<td><%= customer.getName() %></td>
    		<td><%= customer.getKana() %></td>			
    		<td><%= customer.getGender() %></td>
    		<td>
        		<form method="get" action="CustomerDetailServlet">
            		<input type="hidden" name="id" value="<%= customer.getId() %>">
            		<button type="submit">詳細</button>
        		</form>
    		</td>
		</tr>	
        <%
            }
        %>
    </table>
    <%
        }
    %>

    <a href="menu.jsp" class="button">メニュー画面へ</a>
</body>
</html>
