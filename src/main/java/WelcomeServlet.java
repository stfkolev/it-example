import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Тук правим проверка дали сесийната променлива съществува
        // и ако съществува, използваме нея за информация
        if(req.getSession().getAttribute("user") != null) {
            // Потребителят дотук е влязъл в профила си
            // Сега ще вземем информацията от сесийната променлива
            // и ще използваме нея за информация на самата страница
            User user = (User) req.getSession().getAttribute("user");

            // Създаваме променлива, която да използваме в самият файл
            // и и задаваме стойност на взетата от сесията информация
            // за потребителя.
            //
            // След това насочваме заявката да зареди welcome.jsp файла
            req.setAttribute("user", user);
            req.getRequestDispatcher("/welcome.jsp").forward(req, resp);
        } else {
            // В противен случай, пренасочваме потребителят към страницата за вход
            resp.sendRedirect("login");
        }
    }
}
