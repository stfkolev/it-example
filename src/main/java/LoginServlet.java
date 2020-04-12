import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
            // страницата за вход
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Проверка дали сме натиснали бутона за регистрация
        // Ако се върнем в login.jsp, по-специфично във формичката
        // Там има бутон с име "Вход" и name със стойност
        // login.
        //
        // Ние вземаме тази стойност от name и проверяваме по нея.
        //
        // Тоест, ако потребителят е натиснал бутона "Вход"
        if(req.getParameterMap().containsKey("login")) {
            // Вземаме всички подадени данни от формата
            String username             = req.getParameter("username");
            String password             = req.getParameter("password");

            // Понеже имаме required на input-овете ни във формичката, не е
            // задължително да проверяваме дали са празни.
            //

            // Сега, трябва да потърсим за този потребител в нашият списък,
            // затова ще проверяваме един по един елементите от списъка
            // и ако намерим съвпадение, ще позволим на потребителят да влезе
            // в акаунта. В противен случай ще му изведем грешка.

            // Създаваме си една помощна променлива, в която да съхраним
            // потребителя, който сме намерили.
            User user = null;

            // Обхождаме колекцията от потребители
            for(User element : UsersRepository.users) {

                // Ако потребителското име съвпада с въведеното и паролата съвпада с въведената
                // Запазваме потребителят в нашата временна променлива и спираме обхождането на
                // списъка
                if(element.getUsername().equals(username) && element.getPassword().equals(password)) {
                    user = element;
                    break;
                }
            }

            // Правим проверка дали потребителят, който търсим е намерен
            if(user != null) {
                // Създаваме си променлива в сесията, която да каже, че потребителят
                // е влязъл в профила си
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("welcome");
            } else {
                // В противен случай, извеждаме грешка, че потребителят не е намерен
                throw new ServletException("Потребителят не е намерен");
            }
        }
    }
}
