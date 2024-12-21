package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Customer;
import dao.CustomerDAO;

@WebServlet("/CustomerEditServlet")
public class CustomerEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 編集画面への遷移処理
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // リクエストパラメータから顧客IDを取得
        String idParam = request.getParameter("id");
        try {
            int id = Integer.parseInt(idParam);

            // データベースから顧客情報を取得
            Customer customer = CustomerDAO.getCustomerById(id);

            if (customer != null) {
                // 顧客情報をリクエストスコープに設定
                request.setAttribute("customer", customer);

                // 編集画面にフォワード
                request.getRequestDispatcher("customerEdit.jsp").forward(request, response);
            } else {
                // 顧客が見つからない場合
                response.sendRedirect("error.jsp"); // エラー用ページに
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // 無効なIDの場合
        }
    }

    // 編集データの更新処理
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームデータを取得
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String kana = request.getParameter("kana");
        String postCode = request.getParameter("postCode");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phoneNumber = request.getParameter("phoneNumber");

        // データベースを更新
        boolean isUpdated = CustomerDAO.updateCustomer(id, name, kana, postCode, address, gender, birthday, phoneNumber);

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
