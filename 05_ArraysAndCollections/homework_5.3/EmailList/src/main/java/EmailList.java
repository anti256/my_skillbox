import java.util.List;
import java.util.TreeSet;

public class EmailList {

    TreeSet<String> emailSet = new TreeSet<>();

    //обработка по слову ADD
    public void add(String email) {
      if ((email.matches("([A-Za-z]+)[@]([A-Za-z]+)[.]([A-Za-z]+)"))){//проверка валидности адреса
        email = email.toLowerCase();//перевод в нижний регистр
        emailSet.add(email);}//добавление адреса в список
        else {System.out.println(Main.WRONG_EMAIL_ANSWER);}//иначе адрес невалидный
        // TODO: валидный формат email добавляется
    }

    public List<String> getSortedEmails() {
        // TODO: возвращается список электронных адресов в алфавитном порядке
      List<String> emailSortedList = List.copyOf(emailSet);
        return emailSortedList;
    }

}
