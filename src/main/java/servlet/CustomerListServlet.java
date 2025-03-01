package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Customer;
import dao.CustomerDAO;

@WebServlet("/CustomerListServlet")
public class CustomerListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("search"); // 検索クエリを取得
        List<Customer> customerList;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            // 検索ワードがあれば、名前で検索する
            customerList = CustomerDAO.searchCustomersByName(searchQuery);
        } else {
            // 検索ワードが空なら、全顧客を取得
            customerList = CustomerDAO.getAllCustomers();
        }

        // 結果をJSPに渡す
        request.setAttribute("customerList", customerList);

        // 顧客がいない場合、検索結果がないことを表示
        if (customerList.isEmpty()) {
            request.setAttribute("noResultsMessage", "検索結果はありません");
        }

        // JSPにフォワード
        request.getRequestDispatcher("/customerList.jsp").forward(request, response);
    }
}
