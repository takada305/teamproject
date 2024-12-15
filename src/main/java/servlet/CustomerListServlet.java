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
        // DAOから顧客情報を取得
        List<Customer> customerList = CustomerDAO.getAllCustomers();

        // 顧客情報をリクエスト属性に設定
        request.setAttribute("customerList", customerList);

        request.getRequestDispatcher("customerList.jsp").forward(request, response);
    }
}
