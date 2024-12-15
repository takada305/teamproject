package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;

@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームデータを取得
        String name = request.getParameter("customerName");
        String kana = request.getParameter("customerKana");
        String postCode = request.getParameter("postCode");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phoneNumber = request.getParameter("phoneNumber");

        // 顧客情報を登録
        boolean isAdded = CustomerDAO.addCustomer(name, kana, postCode, address, gender, birthday, phoneNumber);

        if (isAdded) {
            // 登録成功
            response.sendRedirect("CustomerListServlet"); 
        } else {
            // 登録失敗
            request.setAttribute("errorMessage", "登録に失敗しました。もう一度お試しください。");
            request.getRequestDispatcher("customerRegister.jsp").forward(request, response);
        }
    }
}
