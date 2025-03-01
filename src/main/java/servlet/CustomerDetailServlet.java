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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 顧客IDを取得
        String idParam = request.getParameter("id");

        // IDがnullまたは空でないか
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/CustomerListServlet");
            return;
        }

        try {
            int customerId = Integer.parseInt(idParam);

            Customer customer = CustomerDAO.getCustomerById(customerId); 

            if (customer == null) {
                response.sendRedirect(request.getContextPath() + "/CustomerListServlet");
                return;
            }

            // 取得した顧客情報をリクエスト属性に設定
            request.setAttribute("customer", customer);

            // 顧客詳細画面にフォワード
            request.getRequestDispatcher("customerDetail.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // IDが数値でない場合 エラーハンドリング
            response.sendRedirect(request.getContextPath() + "/CustomerListServlet");
        }
    }
}
