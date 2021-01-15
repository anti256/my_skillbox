import java.util.Scanner;

public class Main {
    public static final String WRONG_EMAIL_ANSWER = "Неверный формат email";
    public static final String WRONG_COMMAND = "Неверный формат команды";
    public static EmailList emailList = new EmailList();
    
    /* TODO:
        Пример вывода списка Email, после ввода команды LIST в консоль:
        test@test.com
        hello@mail.ru
        - каждый адрес с новой строки
        - список должен быть отсортирован по алфавиту
        - email в разных регистрах считается одинаковыми
           hello@skillbox.ru == HeLLO@SKILLbox.RU
        - вывод на печать должен быть в нижнем регистре
           hello@skillbox.ru
        Пример вывода сообщения об ошибке при неверном формате Email:
        "Неверный формат email"
    */

    public static void main(String[] args) {
      printInfo();
      Scanner scanner = new Scanner(System.in);
        
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            //преобразуем введенное в массив строк по разделителю пробелу
            String[] strokaArray = input.split(" ");

            //обработка первого слова
            //использовать switch case нецелесообразно, т.к. условий мало и они каждое состоит из нескольких условий
            //если команда состоит из двух слов и первое из них ADD
            if ((strokaArray.length == 2) && (strokaArray[0].equals("ADD"))){
              //отсылаем в класс на обработку в список
              emailList.add(strokaArray[1]);
            } else {
                   //если команда из одного слова и это LIST
                    if ((strokaArray.length == 1) && (strokaArray[0].equals("LIST"))){
                    for (String s: emailList.getSortedEmails()) {System.out.println(s);}
                     } else {System.out.println(WRONG_COMMAND);//иначе команда неправильная
                        helpInfo();}//выводим справку о формате команд
                  }
            
        }//конец бесконечного цикла
    }

  private static void printInfo() {
    //информация
    System.out.println("Введите команду в одном из форматов:" + System.lineSeparator() +
        "Добавление адреса - ADD email-адрес" + System.lineSeparator() +
        "Вывод списка адресов - LIST" + System.lineSeparator() + "Выход - 0");
  }

  private static void helpInfo(){
      //формат команд
    System.out.println("Добавление адреса - ADD email-адрес" + System.lineSeparator() +
        "Вывод списка адресов - LIST" + System.lineSeparator() + "Выход - 0");
  }

}
