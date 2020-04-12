import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Тук правим проверка дали сесийната променлива съществува
        // и ако съществува, го пренасочваме към профилната му
        // страница
        if(req.getSession().getAttribute("user") != null) {
            // Потребителят дотук е влязъл в профила си
            // В този случай, пренасочваме потребителят към профилната му страница
            resp.sendRedirect("welcome");
        } else {
            // В противен случай, просто пренасочваме заявката да зареди
            // страницата за регистрация
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Проверка дали сме натиснали бутона за регистрация
        // Ако се върнем в register.jsp, по-специфично във формичката
        // Там има бутон с име "Регистриране" и name със стойност
        // register.
        //
        // Ние вземаме тази стойност от name и проверяваме по нея.
        //
        // Тоест, ако потребителят е натиснал бутона "Регистриране"
        if(req.getParameterMap().containsKey("register")) {
            // Вземаме всички подадени данни от формата
            String username             = req.getParameter("username");
            String email                = req.getParameter("email");
            String password             = req.getParameter("password");
            String confirmPassword      = req.getParameter("confirmPassword");

            // Понеже имаме required на input-овете ни във формичката, не е
            // задължително да проверяваме дали са празни.
            //

            // Правим проверка дали двете пароли съвпадат и ако съвпадат
            // добавяме потребителят към колекцията ни(UsersRepository.users)
            // и ще го препратим към страницата за вход
            if(password.equals(confirmPassword)) {
                User user = null;

                // Правим едно обхождане на потребителите, за да не се получи
                // така да съществува вече потребител с това потребителско име
                // или имейл.
                for(User element : UsersRepository.users) {
                    // Ако потребителското име съвпада или имейлът съвпада с някой от всичките
                    // потребители, запази във временна променлива, за да изведем грешка по-късно
                    if(element.getUsername().equals(username) || element.getEmail().equals(email)) {
                        user = element;
                    }
                }

                // Ако не е намерен потребител със подобно потребителско име
                // или подобен имейл, създай потребител с тези данни
                if(user == null) {
                    user = new User(username, email, password);
                    UsersRepository.users.add(user);
                } else {
                    // В противен случай, изведи грешка
                    throw new ServletException("Потребителят вече съществува");
                }

                // След успешна регистрация, пренасочваме кът входната страница
                resp.sendRedirect("login");
            } else {
                // В противен случай, ще му изведем на потребителя, че паролите не съвпадат
                throw new ServletException("Паролите не съвпадат");
            }
        }
    }
}
