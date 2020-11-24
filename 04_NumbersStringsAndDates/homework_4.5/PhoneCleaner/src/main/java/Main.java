import java.util.Scanner;

public class Main {

 private static String isNumberValid (String stroka){
   stroka = stroka.replaceAll("[^0-9+]","");//убираем из строки все цифры и плюсы
   //если длина оставшейся строки меньше 10 и больше 12, формат номера неверный
   if ((stroka.length() > 12) | (stroka.length() < 10)){return "Неверный формат номера\n";}
   String defaultStroka = stroka;//переменная-строка, равная аргументу метода

   //обработка длины строки 10 символов
   if (stroka.length() == 10){
     //вырезаем из строки первая цифра 9, остальные девять - любые цифры
     stroka = stroka.replaceAll("9[0-9]{9}","");
     //если после вырезки осталась пустая строка, возвращаем номер с 7 спереди
     if (stroka.length() == 0) {return ("7" + defaultStroka);}
   }
   stroka = defaultStroka; //возвращаем проверяемой строке значение аргумента метода

   //обработка длины строки 11 символов
   if (stroka.length() == 11) {
     //вырезаем из строки первая цифра 8, остальные десять - любые цифры
     stroka = stroka.replaceAll("8[0-9]{10}","");
     if (stroka.length() == 0) {//если после вырезки осталась пустая строка
       stroka = defaultStroka; //возвращаем проверяемой строке значение аргумента метода
       stroka = stroka.replaceAll("^8","7");//заменяем первую восьмерку семеркой
       return stroka;}
   }
   stroka = defaultStroka;//возвращаем проверяемой строке значение аргумента метода

   //обработка длины строки 12 символов
   if (stroka.length() == 12) {
     //вырезаем из строки плюс вначале строки, потом 7, остальные десять цифр - любые
     stroka = stroka.replaceAll("\\+7[0-9]{10}","");
     if (stroka.length() == 0) {//если после вырезки осталась пустая строка
       stroka = defaultStroka; //возвращаем проверяемой строке значение аргумента метода
       stroka = stroka.replaceAll("\\+","");//убираем из строки плюс
       return stroka;}
   }
   return "Неверный формат номера\n";//если все условия не выполнились, формат номера неверный
 }

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      if (input.equals("0")) {
        break;
      }

      //10 цифр начмнается с 9 - прибавляем спереди 7
      //11 цифр начинается с 8 - меняем 8 на 7
      //12 цифр начинается + - убираем +

      System.out.println(isNumberValid(input));//использования метода позволяет избежать большого
                                                //количества условных переходов if
    }
  }

}
