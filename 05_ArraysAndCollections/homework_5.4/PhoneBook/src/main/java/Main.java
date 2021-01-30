import java.util.Scanner;
import java.util.Set;

public class Main {
    public static PhoneBook phoneBook = new PhoneBook();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {//бесконечный цикл
          printInfo();
            String input = scanner.nextLine();

            //обработчик ввода 0
            if (input.equals("0")) {break;}

            //обработчик ввода HELP
            if (input.equals("HELP")) {
                helpInfo();
                continue;}//уходим на следующую итерацию бесконечного цикла

            //обработчик ввода LIST
            if (input.equals("LIST")) {
             for (String s: phoneBook.getAllContacts()) {System.out.println(s);}
             continue;}//уходим на следующую итерацию бесконечного цикла

            //обработчик по номеру
            String stroka = ValidationWord.isNumberValid(input);//проверяем телефон на валидность и
                                                                //преобразуем в валидный формат
            if (!stroka.equals("Mistake")){//если телефон удалось преобразовать в валидный формат
              String strokaByPhone = phoneBook.getClearNameByPhone(stroka);//проводим поиск имени по телефону
              if (strokaByPhone.length() > 0){//если есть соответствие имени по телефону
                System.out.println(phoneBook.getNameByPhone(stroka));//выводим имя по телефону
                continue;//уходим на следующую итерацию бесконечного цикла
              } else{//если нет соответствия имени по телефону
                System.out.println("Такого номера нет в телефонной книге." + System.lineSeparator() +
                    "Введите имя абонента для номера " + stroka + ":");
                String inputName = scanner.nextLine();
                if (inputName.matches("^[A-ZА-Я]{1}[a-zA-Zа-яА-Я0-9_-]*")){//если имя валидное
                  phoneBook.addContact(stroka,inputName);//сохраняем контакт
                  continue;//уходим на следующую итерацию бесконечного цикла
                } else {//если имя не валидное
                  System.out.println("Имя не соответствует формату");
                  helpInfo();
                  continue;}//уходим на следующую итерацию бесконечного цикла
               }//конец обработки - если нет соответствия имени по телефону
             } else {//если не удалось обработать команду как телефон, пытаемся обработать как имя
              if (input.matches("^[A-ZА-Я]{1}[a-zA-Zа-яА-Я0-9_-]*")) {//если строка соответствует имени
                Set<String> phoneByNameMain = phoneBook.getClearPhonesByName(input);//проводим поиск телефона по имени
                if (!phoneByNameMain.isEmpty()) {//если есть соответствие имя-телефон
                  String phonesByName = input + " - ";
                  for (String nameSet: phoneByNameMain){//создаем строку имя - телефоны
                    phonesByName = phonesByName.concat(nameSet + ", ");
                  }
                  phonesByName = phonesByName.substring(0,phonesByName.length()-2);//отрезаем от строки лишнее сзади
                  System.out.println(phonesByName);//выводим строку имя - телефоны
                  continue;//уходим на следующую итерацию бесконечного цикла
                }else {//если имени нет в списке
                  System.out.println("Такого номера нет в телефонной книге." + System.lineSeparator() +
                      "Введите номер телефона для имени " + input + ":");
                  String inputPhone = scanner.nextLine();
                  if (!inputPhone.equals("Mistake")) {//если телефон удалось преобразовать в валидный формат
                    phoneBook.addContact(inputPhone, input);//сохраняем контакт
                    continue;//уходим на следующую итерацию бесконечного цикла
                  }else {System.out.println("Номер телефона не соответствует формату");
                    helpInfo();
                    continue;}//уходим на следующую итерацию бесконечного цикла
                }
              }//если команда не соответствует ни одному формату
              else {System.out.println("Команда не соответствует формату");
                helpInfo();
                continue;}//уходим на следующую итерацию бесконечного цикла
            }
        }//конец бесконечного цикла
    }

    private static void printInfo() {
        //информация
        System.out.println(System.lineSeparator() + "Введите номер, имя или команду:" + System.lineSeparator() +
            "Имя - поиск или добавление телефона по имени" + System.lineSeparator() +
            "Телефон - поиск или добавление телефона по номеру" + System.lineSeparator() +
            "Вывод списка адресов - LIST" + System.lineSeparator() +
            "Справка о форматах ввода - HELP" + System.lineSeparator() +
            "Выход - 0");
    }

    private static void helpInfo() {
        //информация
        System.out.println(System.lineSeparator() + "Форматы ввода:" + System.lineSeparator() +
            "Добавление телефона - Имя, формат - первая буква заглавная, русские или латинские строчные или заглавные буквы или цифры" + System.lineSeparator() +
            "Добавление номера телефона, формат - без плюса, первые цифры 79, всего 11 цифр" + System.lineSeparator() +
            "Поиск телефона - Имя, формат - первая буква заглавная, русские или латинские буквы или цифры" + System.lineSeparator() +
            "Форматы команд:" + System.lineSeparator() +
            "Имя" + System.lineSeparator() +
            "если имя существует, будет выведен номер телефона, если не существует, последует запрос на номер телефона и он будет сохранен" + System.lineSeparator() +
            "номер" + System.lineSeparator() +
            "если номер существует, будет выведено имя, если не существует, последует запрос на имя и он будет сохранен" + System.lineSeparator() +
            "Вывод списка адресов - LIST" + System.lineSeparator() +
            "Справка - HELP" + System.lineSeparator() +
            "Выход - 0");
    }

}
