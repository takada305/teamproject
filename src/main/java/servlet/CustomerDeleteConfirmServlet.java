package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Customer;
import dao.CustomerDAO;

@WebServlet("/CustomerDeleteConfirmServlet") 
public class CustomerDeleteConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 顧客IDを取得
        String idParam = request.getParameter("id");
        try {
            int id = Integer.parseInt(idParam);

            // 顧客情報を取得
            Customer customer = CustomerDAO.getCustomerById(id);

            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("customerDeleteConfirm.jsp").forward(request, response);
            } else {
                response.sendRedirect("CustomerListServlet"); // 該当なしの場合一覧へ
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
