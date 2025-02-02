package servlet;

import java.io.IOException;
import java.util.List;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<String> districtList = DistrictDAO.getAllDistricts();
        System.out.println("取得した地区リスト（Servlet内）: " + districtList); 

        // リクエスト属性に設定
        request.setAttribute("districtList", districtList);

        // 顧客登録画面にフォワード
        request.getRequestDispatcher("customerRegister.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerName = request.getParameter("customerName");
        String customerKana = request.getParameter("customerKana");
        String postCode = request.getParameter("postCode");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String phoneNumber = request.getParameter("phoneNumber");
        
       
        String district = request.getParameter("district");

        boolean isRegistered = CustomerDAO.addCustomer(customerName, customerKana, postCode, address, district, gender, birthday, phoneNumber);

        if (isRegistered) {
            response.sendRedirect("CustomerListServlet");
        } else {
            request.setAttribute("errorMessage", "登録に失敗しました。もう一度お試しください。");
            request.getRequestDispatcher("customerRegister.jsp").forward(request, response);
        }
    }
}
