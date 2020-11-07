import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    //System.out.println("Введите количество ящиков");
    Scanner scanner = new Scanner(System.in);
    String boxes = scanner.nextLine();
    int cases = Integer.parseInt(boxes); //количество ящиков в цифрах
    int truck = 0, container = 0, currentCase = 0, currentContainer = 0;

    if (Math.abs(cases) == cases) {// проверка корректности введенного числа ящиков
      if (cases > 0) {//если ящиков больше нуля

        //подсчет необходимого количества грузовиков и контейнеров
        container = Math.floorDiv(cases, 27);//количество контейнеров
        if (!(cases % 27 == 0)) {
          container++;
        }//если есть остаток, прибавляем один контейнер
        truck = Math.floorDiv(container, 12);//количество грузовиков
        if (!(container % 12 == 0)) {
          truck++;
        }//если есть остаток, прибавляем один грузовик

        //отчет
        for (int t = 0; t < truck; t++) {             //цикл по грузовикам
          System.out.println("Грузовик: " + (t + 1));
          for (int c = 1; c < 13; c++) {              //цикл по контейнерам в машине - 12 шт.
            currentContainer++;                       //текущий контейнер в общей нумерации
            System.out.println("\tКонтейнер: " + currentContainer);
            for (int b = 1; b < 28; b++) {            //цикл по ящикам в контейнере - 27 шт.
              if (currentCase >= cases) {
                break;
              }
              currentCase++;                         //текущий ящик в общей нумерации
              System.out.println("\t\tЯщик: " + currentCase);
            }
            if (currentContainer >= container) {
              break;
            }
          }
        }
      }

      System.out.println("Необходимо:");
      System.out.println("грузовиков - " + truck + " шт.");
      System.out.println("контейнеров - " + container + " шт.");
    } else {
      System.out.println("Количество ящиков должно быть не отрицательное.");
    }

    // пример вывода при вводе 2
    // для отступа используйте табуляцию - \t

        /*
        Грузовик: 1
            Контейнер: 1
                Ящик: 1
                Ящик: 2
        Необходимо:
        грузовиков - 1 шт.
        контейнеров - 1 шт.
        */
  }

}
