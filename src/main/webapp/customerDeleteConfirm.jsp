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
        table {
            width: 90%;
            margin: 20px auto;
            border: 1px solid black;
        }
        th, td {
            border: 1px double black;
            padding: 10px;
            text-align: center;
            white-space: nowrap;
        }
        .button-container {
            text-align: center;
            margin-top: 7px; 
        }
        .button {
            padding: 8px 16px; 
            font-size: 16px;
            color: black;
            border: 1px solid black;
            background-color: #E0E0E0;
            border-radius: 8px;
            cursor: pointer;
            margin: 3px auto; 
            display: block;
        }
        .delete-button {
            width: 120px; 
        }
        .back-button {
            width: 150px;
        }
    </style>
</head>
<body>
    <%
        Customer customer = (Customer) request.getAttribute("customer");
        if (customer != null) {
    %>
    <h1>顧客を削除してもよろしいですか？</h1>
    
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
        </tr>
    </table>

    <div class="button-container">
        <form action="<%= request.getContextPath() %>/CustomerDeleteServlet" method="post">
            <input type="hidden" name="id" value="<%= customer.getId() %>">
            <button type="submit" class="button delete-button">削除確定</button>
        </form>

        <form action="<%= request.getContextPath() %>/CustomerDetailServlet" method="get">
    		<input type="hidden" name="id" value="<%= customer.getId() %>">
    		<button type="submit" class="button back-button">詳細画面へ</button>
		</form>
        
    </div>
    
    <%
        } else {
    %>
    <p>顧客情報が見つかりません。</p>
    <form action="<%= request.getContextPath() %>/CustomerListServlet" method="get">
        <button type="submit" class="button">顧客一覧に戻る</button>
    </form>
    <%
        }
    %>
</body>
</html>
