public class Main {

  public static void main(String[] args) {
    Container container = new Container();
    container.count += 7843;//если count будет Integer, то container.count будет null  и эта строка выдаст ошибку

    //int sum = sumDigits(7843);

    int sum = sumDigits(container.count);

    System.out.println(sum);
  }

  /* Реализуйте метод sumDigits который возвращает сумму цифр числа, пример:
  передано 12345, метод должен вернуть 15
  если передано null, то должен вернуть -1

  Запустите тесты TestSumDigits для проверки корректности работы метода

  не меняйте название метода, его возвращаемое значение и модификаторы доступа (public).
  В противном случае тестовый метод не сможет проверить ваш код
   */

  //класса Integer: toString() - преобразует число в строку
  // - Integer: parseInt() - преобразует строку в число;
  // - класса String: charAt() - возвращает символ по индексу с нуля,
  // - length() - длина строки,
  // - valueOf()- возвращает строку из числа


  public static int sumDigits(Integer number) {
    String str = String.valueOf(number);        //строка из числа
    if (!str.equals("null")) {                   //если аргумент не равен null
      //длина строки
      int length = str.length();
      int summa = 0;                               //обнуляем результат
      for (int i = 0; i < length; i++) {          //перебор
        summa += Character.digit(str.charAt(i), 10);
      }
      return summa;
    } else {
      return -1;
    }
  }
}
