package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AuthorityDAO;

@WebServlet("/AuthorityEditServlet")
public class AuthorityEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // フォームデー取得
        String userId = request.getParameter("userId");
        String newAuthority = request.getParameter("authority");

        // 権限更新
        boolean isUpdated = AuthorityDAO.updateAuthority(userId, newAuthority);

        if (isUpdated) {
            // 成功時
            response.sendRedirect("success.jsp"); 
        } else {
            // 失敗時
            request.setAttribute("errorMessage", "権限の更新に失敗しました。");
            request.getRequestDispatcher("authorityEdit.jsp").forward(request, response);
        }
    }
}
