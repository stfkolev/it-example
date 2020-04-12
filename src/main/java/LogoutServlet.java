import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Тук правим проверка дали сесийната променлива съществува
        // и ако съществува, го пренасочваме към профилната му
        // страница
        if(req.getSession().getAttribute("user") != null) {
            // Потребителят дотук е влязъл в профила си
            // В този случай, изтриваме сесията на потребителя и
            // го пренасочваме към началната страница
            req.getSession().invalidate();
            resp.sendRedirect("/");
        } else {
            // В противен случай, пренасочваме потребителят към
            // страницата за вход
            resp.sendRedirect("login");
        }
    }
}
