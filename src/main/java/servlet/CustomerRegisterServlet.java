package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import dao.DistrictDAO;

@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        System.out.println("CustomerRegisterServlet.doGet() が呼ばれた");

        // 地区リストを取得
        List<String> districtList = DistrictDAO.getAllDistricts();

        if (districtList == null) {
            System.err.println("districtList が NULL です！データベース接続エラーの可能性");
        } else if (districtList.isEmpty()) {
            System.err.println("districtList は空");
        } else {
            System.out.println("districtList 取得成功: " + districtList);
        }

        // JSP に渡す
        request.setAttribute("districtList", districtList);

        // フォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("customerRegister.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        System.out.println("doPost() が呼ばれた");

        // フォームデータを取得
        String name = request.getParameter("customerName");
        String kana = request.getParameter("customerKana");
        String postCode = request.getParameter("postCode");
        String district = request.getParameter("district");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phoneNumber = request.getParameter("phoneNumber");

        System.out.println("受け取ったデータ:");
        System.out.println("氏名: " + name);
        System.out.println("かな: " + kana);
        System.out.println("郵便番号: " + postCode);
        System.out.println("地区: " + district);
        System.out.println("性別: " + gender);
        System.out.println("生年月日: " + birthday);
        System.out.println("電話番号: " + phoneNumber);

        // データベースに登録
        boolean isInserted = CustomerDAO.addCustomer(name, kana, postCode, district, gender, birthday, phoneNumber);

        if (isInserted) {
            System.out.println("顧客登録成功");
            response.sendRedirect("CustomerListServlet");  // 登録後にリスト画面へリダイレクト
        } else {
            System.err.println("顧客登録失敗");
            request.setAttribute("errorMessage", "登録に失敗。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("customerRegister.jsp");
            dispatcher.forward(request, response);
        }
    }
}
