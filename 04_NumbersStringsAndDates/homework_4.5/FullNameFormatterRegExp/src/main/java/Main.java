import java.util.Scanner;

public class Main {

  private static Boolean isValidText(String stroka) {//метод проверки валидности
    if (stroka.equals("")) {return false;}//если строка пустая сразу возвращает false
    //вычитаем из строки валидное ФИО
    stroka = stroka.replaceAll("([А-ЩЭЮЯ][а-я]*(-[А-ЩЭЮЯ])?[а-я]*\\s){2}[А-ЩЭЮЯ][а-я]*-?[а-я]*", "");
    if (stroka.equals("")) {return true;}//после вычитания строка должна стать пустой, тогда метод возвращает true
    return false;//значение по умолчанию
  }

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }
      input = input.trim();//убираем пробелы по краям строки

      if (isValidText(input)) {//проверяем валидность ФИО
        //если ФИО валидное
        String[] strArray = input.split(" ");//режем строку по пробелам
        //выводим строками ФИО
        System.out.println("Фамилия: " + strArray[0]);
        System.out.println("Имя: " + strArray[1]);
        System.out.println("Отчество: " + strArray[2]);
        //если ФИО не валидное, выводим сообщение об этом
      } else {System.out.println("Введенная строка не является ФИО");}
    }
  }

}