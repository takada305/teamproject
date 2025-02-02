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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String userId = request.getParameter("userId");
        String newAuthority = request.getParameter("authority");

        // 権限を更新
        boolean isUpdated = AuthorityDAO.updateAuthority(userId, newAuthority);

        if (isUpdated) {
            // ユーザー情報を保存
            request.getSession().setAttribute("loggedInUserId", userId);
            request.getSession().setAttribute("authority", newAuthority);

            // `menu.jsp` にリダイレクト
            response.sendRedirect("menu.jsp");
        } else {
            // 更新失敗
            request.setAttribute("errorMessage", "権限の更新に失敗しました。");
            request.getRequestDispatcher("authorityEdit.jsp").forward(request, response);
        }
    }
}
