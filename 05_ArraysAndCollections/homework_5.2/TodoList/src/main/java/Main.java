import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static TodoList todoList = new TodoList();

    public static void main(String[] args) {

      printInfo();

      Scanner scanner = new Scanner(System.in);
        while (true) {//бесконечный цикл
          String input = scanner.nextLine();//сканер по строкам
          if (input.equals("0")) {//выход из цикла/программы по введению нуля
            break;
          }

          //преобразуем введенное в массив строк по разделителю пробелу
          String[] strokaArray = input.split(" ");

         //обработка первого слова
         switch (strokaArray[0]) {
              case "ADD":
                  todoList.addToArrayList(strokaArray);
                  break;
              case "EDIT":
                  todoList.editPosArrayList(strokaArray);
                  break;
              case "LIST":
                  for (String s : todoList.getTodos()) {
                      System.out.println(s);}
                  break;
              case "DELETE":
                  System.out.println("del");
                  if ((strokaArray.length > 1) && (strokaArray[1].matches("\\d+"))) {
                      //передаем в экземпляр класса второе слово-число, как аргумент
                      todoList.delete(Integer.parseInt(strokaArray[1]));
                  }
                  break;
             default:
                 break;
          }
        }
    }

    private static void printInfo() {
        //информация
        System.out.println("Введите команду в одном из форматов:" + System.lineSeparator() +
                "ADD строка" + System.lineSeparator() + "ADD номер_позиции строка" +
                System.lineSeparator() + "EDIT номер_позиции строка" + System.lineSeparator()
                + "LIST" + System.lineSeparator() + "DELETE номер_позиции" + System.lineSeparator()
                + "нумерация позиций начинается с нуля" + System.lineSeparator() + "0 - выход");
    }
}
