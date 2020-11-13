import java.util.Scanner;

public class Main {

  private static Boolean isFioValid(String fio) {
    int spaceCount = 0;//количество пробелов
    int lengthString = fio.length();
    int spacePos = lengthString;//начальную позицию пробела устанавливаем на максимум
    char symbol;
    for (int i = 0; i < lengthString; i++) {
      symbol = fio.charAt(i);
      int code = (int) symbol;
      if (((code < 1040) | (code > 1103)) & (code != 45)) {//если символ не кириллица и не тире
        if (code == 32) {    //символ пробел?
          if (i == (spacePos + 1)) {
            spaceCount += 3;//если пробелы друг за другом, фио не валидно
          }
          spaceCount++;//увеличиваем счетчик пробелов
          spacePos = i;//запоминаем позицию пробела
        } else {
          return false;
        }
      }
    }
    if (spaceCount != 2) {//в валидной строке должно быть ровно два пробела
      return false;
    }
    return true;
  }

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine();
      int spaceIndex;
      if (input.equals("0")) {
        break;
      }

      input = input.trim();                         //убираем пробелы по краям строки

      if (isFioValid(input)) {//если введеная строка валидная
        spaceIndex = input.indexOf(" ");//получаем позицию первого пробела
        System.out.println("Фамилия: " + input.substring(0, spaceIndex));//фамилия - строка до первого пробела
        input = input.substring(spaceIndex + 1);//вырезаем из строки фамилию с последующим пробелом
        spaceIndex = input.indexOf(" ");//получаем позицию следующего пробела
        System.out.println("Имя: " + input.substring(0, spaceIndex));//имя - строка до следующего пробела
        input = input.substring(spaceIndex + 1);//вырезаем из строки имя с последующим пробелом
        System.out.println("Отчество: " + input);//отчество - остаток строки
      } else {//если строка не валидная, выводим об этом надпись
        System.out.println("Введенная строка не является ФИО");
      }
    }
  }

}