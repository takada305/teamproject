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
</head>
<body>
    <h1>顧客情報編集</h1>
    <%
        Customer customer = (Customer) request.getAttribute("customer");
        if (customer != null) {
    %>
    <form action="CustomerEditServlet" method="post">
        <input type="hidden" name="id" value="<%= customer.getId() %>">

        <label for="name">氏名:</label>
        <input type="text" id="name" name="name" value="<%= customer.getName() %>" required><br>

        <label for="kana">かな:</label>
        <input type="text" id="kana" name="kana" value="<%= customer.getKana() %>" required><br>

        <label for="postCode">郵便番号:</label>
        <input type="text" id="postCode" name="postCode" value="<%= customer.getPostCode() %>" required><br>

        <label for="address">住所:</label>
        <input type="text" id="address" name="address" value="<%= customer.getAddress() %>" required><br>
        
        <label for="district">地区:</label>
      <select id="district" name="district" required>
            <option value="未設定" <%=customer != null && customer.getDistrict() == null || customer.getDistrict().isEmpty() ? "selected" : "" %>>未設定</option>
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

        <label for="gender">性別:</label>
        <select id="gender" name="gender" required>
            <option value="男" <%= customer.getGender().equals("男") ? "selected" : "" %>>男</option>
            <option value="女" <%= customer.getGender().equals("女") ? "selected" : "" %>>女</option>
        </select><br>

        <label for="birthday">生年月日:</label>
        <input type="text" id="birthday" name="birthday" value="<%= customer.getBirthday() %>" required><br>

        <label for="phoneNumber">電話番号:</label>
        <input type="text" id="phoneNumber" name="phoneNumber" value="<%= customer.getPhoneNumber() %>" required><br>

        <button type="submit">保存</button>
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
