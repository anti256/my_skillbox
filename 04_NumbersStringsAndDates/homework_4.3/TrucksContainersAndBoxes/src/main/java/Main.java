import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //System.out.println("Введите количество ящиков");
        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();
        int cases = Integer.parseInt(boxes);
        int truck = 0, container = 0, currentCase = 1;

        if (Math.abs(cases) == cases){// проверка корректности введенного числа ящиков
          if (cases > 0) {//если ящиков больше нуля
            truck = 1;    //начальное количество грузовиков и контейнеров равно единице
            container = 1;
            System.out.println("Грузовик: " + truck);
            System.out.println("\tКонтейнер: " + container);
            while (currentCase < (cases + 1)) {//начало цикла по ящикам

              if ((currentCase > 1) & ((currentCase - 1) % 27
                  == 0)) {//если количество ящиков больше 27
                container++;                                         //берем следующий контейнер
                if ((container > 1) & ((container - 1) % 12
                    == 0)) {    //если количество контейнеров больше 12
                  truck++;                                           //берем следующий грузовик
                  System.out.println("Грузовик: " + truck);
                }
                System.out.println("\tКонтейнер: " + container);
              }

              System.out.println("\t\tЯщик: " + currentCase);
              currentCase++;
            }
          }

            System.out.println("Необходимо:");
            System.out.println("грузовиков - " + truck + " шт.");
            System.out.println("контейнеров - " + container + " шт.");
        }
        else {System.out.println("Количество ящиков должно быть не отрицательное.");}


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
