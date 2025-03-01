package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Customer;
import dao.CustomerDAO;
import dao.DistrictDAO;

@WebServlet("/CustomerEditServlet")
public class CustomerEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("CustomerListServlet");
            return;
        }

        try {
            int customerId = Integer.parseInt(idParam);
            Customer customer = CustomerDAO.getCustomerById(customerId);

            if (customer == null) {
                response.sendRedirect("CustomerListServlet");
                return;
            }

            // 地区リストを取得
            List<String> districtList = DistrictDAO.getAllDistricts();

            if (districtList == null || districtList.isEmpty()) {
                System.out.println("⚠ CustomerEditServlet: districtList が NULL または空です！");
            }

            // JSP にデータを渡す
            request.setAttribute("customer", customer);
            request.setAttribute("districtList", districtList);

            // フォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("customerEdit.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("CustomerListServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String kana = request.getParameter("kana");
            String postCode = request.getParameter("postCode");
            String district = request.getParameter("district");
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");
            String phoneNumber = request.getParameter("phoneNumber");

            // 顧客情報の更新
            boolean success = CustomerDAO.updateCustomer(id, name, kana, postCode, district, gender, birthday, phoneNumber);

            if (success) {
                // 更新成功時、詳細ページへリダイレクト
                response.sendRedirect("CustomerDetailServlet?id=" + id);
            } else {
                // 失敗時
                request.setAttribute("errorMessage", "顧客情報の更新に失敗しました。");
                doGet(request, response); // 編集画面に戻す
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("CustomerListServlet");
        }
    }
}
