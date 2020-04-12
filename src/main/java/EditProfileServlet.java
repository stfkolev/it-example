import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {
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
            // След това насочваме заявката да зареди editProfile.jsp файла
            req.setAttribute("user", user);
            req.getRequestDispatcher("/editProfile.jsp").forward(req, resp);
        } else {
            // В противен случай, пренасочваме потребителят към страницата за вход
            resp.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Проверка дали сме натиснали бутона за регистрация
        // Ако се върнем в editProfile.jsp, по-специфично във формичката
        // Там има бутон с име "Запазване" и name със стойност
        // save.
        //
        // Ние вземаме тази стойност от name и проверяваме по нея.
        //
        // Тоест, ако потребителят е натиснал бутона "Запазване"
        if(req.getParameterMap().containsKey("save")) {
            // Вземаме всички подадени данни от формата
            String firstName    = req.getParameter("firstName");
            String lastName     = req.getParameter("lastName");
            String city         = req.getParameter("city");
            String address      = req.getParameter("address");

            // Понеже имаме required на input-овете ни във формичката, не е
            // задължително да проверяваме дали са празни.
            //

            // Вземаме информацията от сесийната променлива на потребителя
            // И я запазваме в наша локална променлива
            User user = (User) req.getSession().getAttribute("user");

            // Попълваме новата информация, която потребителят е задал
            user.setAddress(address);
            user.setCity(city);
            user.setFirstName(firstName);
            user.setLastName(lastName);

            // Обновяваме сесийната променлива, защото тя не се обновява сама
            // и трябва ние ръчно да го направим, защото сме добавили нова
            // информация за този потребител
            req.getSession().setAttribute("user", user);

            // След това, обхождаме всички потребители и търсим този конкретен
            // потребител в нашата колекция, за да му обновим информацията и там.
            //
            // Прави се с цел следващият път като влезе в профила си тази негова
            // информация да бъде запазена, а не да трябва да я въвежда наново.
            for(User element : UsersRepository.users) {
                // Ако потребителското име и паролата съвпадат
                if(element.getUsername().equals(user.getUsername()) && element.getPassword().equals(user.getPassword())) {
                    // Обновяваме информацията на този потребител в колекцията
                    // и прекратяваме обхождането
                    element = user;
                    break;
                }
            }

            // Пренасочваме към профилната страница, за да може потребителя
            // да види резултата от редактирането на профила си
            resp.sendRedirect("welcome");
        }
    }
}
