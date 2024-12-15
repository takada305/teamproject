package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Customer;
import dao.CustomerDAO;

@WebServlet("/CustomerDetailServlet")
public class CustomerDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GETリクエスト処理
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // リクエストから顧客IDを取得
        String idParam = request.getParameter("id"); // リンクから渡されたIDを取得
        int customerId = Integer.parseInt(idParam); // Stringからintに変換

        // DAOを使用して顧客情報を取得
        Customer customer = CustomerDAO.getCustomerById(customerId);

        // 取得した顧客情報をリクエスト属性に設定
        request.setAttribute("customer", customer);

        // 顧客詳細画面にフォワード
        request.getRequestDispatcher("customerDetail.jsp").forward(request, response);
    }
}
