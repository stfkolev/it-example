import java.util.ArrayList;
import java.util.List;

// В този клас единствено ще съдържаме списъка от потребители
// Правим го с цел сървлетите да са независими един от друг
// И да ни е по-лесно при по-нататъчна интеграция с бази данни

public class UsersRepository {
    public static List<User> users = new ArrayList<User>();
}
