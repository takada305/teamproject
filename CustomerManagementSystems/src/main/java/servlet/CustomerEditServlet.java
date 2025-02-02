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
import dao.DistrictDAO;

@WebServlet("/CustomerEditServlet")
public class CustomerEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 編集画面への遷移
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        try {
            int id = Integer.parseInt(idParam);

            // 顧客情報を取得
            Customer customer = CustomerDAO.getCustomerById(id);

            // 地区リストを取得
            List<String> districtList = DistrictDAO.getAllDistricts();

            if (customer != null) {
                // 顧客情報と地区リストをリクエストスコープに設定
                request.setAttribute("customer", customer);
                request.setAttribute("districtList", districtList);

                // 編集画面にフォワード
                request.getRequestDispatcher("customerEdit.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    // 編集データの更新処理
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームデータを取得
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id")); 
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
            return; // 処理を中断
        }

        String name = request.getParameter("name");
        String kana = request.getParameter("kana");
        String postCode = request.getParameter("postCode");
        String address = request.getParameter("address");
        String district = request.getParameter("district"); 
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phoneNumber = request.getParameter("phoneNumber");

        // データベースを更新
        boolean isUpdated = CustomerDAO.updateCustomer(id, name, kana, postCode, address, district, gender, birthday, phoneNumber);

        if (isUpdated) {
            // 更新成功
            response.sendRedirect("CustomerDetailServlet?id=" + id);

        } else {
            // 更新失敗
            request.setAttribute("errorMessage", "更新に失敗しました。もう一度お試しください。");
            request.getRequestDispatcher("customerEdit.jsp").forward(request, response);
        }
    }
}
