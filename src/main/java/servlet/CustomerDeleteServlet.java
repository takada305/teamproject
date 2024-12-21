package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;

@WebServlet("/CustomerDeleteServlet")
public class CustomerDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // リクエストパラメータから顧客IDを取得
        String idParam = request.getParameter("id");
        try {
            int id = Integer.parseInt(idParam);

            // データベースから顧客情報を削除
            boolean isDeleted = CustomerDAO.deleteCustomerById(id);

            if (isDeleted) {
                // 成功
                response.sendRedirect("CustomerListServlet");
            } else {
                // 失敗
                request.setAttribute("errorMessage", "削除に失敗しました。");
                request.getRequestDispatcher("customerDeleteConfirm.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // 無効なID
        }
    }
}
