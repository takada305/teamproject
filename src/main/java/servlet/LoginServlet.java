package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームから送信されたデータを取得する
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        // 認証
        boolean isValidUser = LoginDAO.authenticate(userId, password);

        // ログイン成功
        if (isValidUser) {
            // 権限を設定
            String authority = LoginDAO.getAuthority(userId);
            request.getSession().setAttribute("authority", authority);

            response.sendRedirect("menu.jsp");
        } else {
            request.setAttribute("errorMessage", "ログイン失敗しました。もう一度入力してください。");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
