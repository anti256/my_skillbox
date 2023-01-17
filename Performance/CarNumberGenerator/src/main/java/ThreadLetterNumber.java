import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ThreadLetterNumber extends Thread
{

  private char firstSign;//первая буква
  private char secondSign;//вторая буква
  private char thirdSign;//третья буква
  private PrintWriter writer;
  private long start;//начальное время программы

  //конструктор
  public ThreadLetterNumber(char firstSign, char secondSign, char thirdSign, long start)
      throws FileNotFoundException {
    this.firstSign = firstSign;
    this.secondSign = secondSign;
    this.thirdSign = thirdSign;
    this.start = start;
    String filePath = "./Performance/CarNumberGenerator/res/numbers_files/numbers" +
        firstSign + secondSign + thirdSign + ".txt";
    writer = new PrintWriter(filePath);
  }

  @Override
  public void run(){//тело потока
    for (int regionCode = 1; regionCode < 100; regionCode++) {//перебор по регионам
      StringBuilder builder = new StringBuilder();
      for (int number = 1; number < 1000; number++) {//перебор по цифрам номера
        //собираем из частей номер
        builder.append(firstSign);
        builder.append(padNumber(number, 3));
        builder.append(secondSign);
        builder.append(thirdSign);
        builder.append(padNumber(regionCode, 2));
        builder.append('\n');
      }
      writer.write(builder.toString());//запись набранного в builder
    }
    writer.flush();//запись остатка в builder
    writer.close();
    //вывод времени с начала программы до конца потока
    System.out.println(Character.toString(firstSign) + Character.toString(secondSign) +
        Character.toString(thirdSign) + " " + (System.currentTimeMillis() - start) + " ms");
  }

  //создание набора бсимволов из набора цифр
  private static String padNumber(int number, int numberLength) {
    StringBuilder builderPadNumber = new StringBuilder(Integer.toString(number));
    int padSize = numberLength - builderPadNumber.toString().length();
    for (int i = 0; i < padSize; i++) {
      builderPadNumber.insert(0,'0');
    }
    return builderPadNumber.toString();
  }

}