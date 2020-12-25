import java.util.Scanner;

public class Main {
    private static TodoList todoList = new TodoList();

   //обработка по слову ADD
    private static void addToArrayList (String[] strArray){
        //если введеных слов минимум два и второе это число и это число положительное
        if ((strArray.length > 1) && (strArray[1].matches("\\d+"))
            && (Integer.parseInt(strArray[1]) >= 0) ) {
          int pos = Integer.parseInt(strArray[1]); //сохраняем позицию
          //"уничтожаем" служебные слова
          strArray[0] = "";
          strArray[1] = "";
          //пересобираем строку из массива - остается только строка, которую нужно добавить в список
          String stroka = String.join(" ", strArray);
          stroka = stroka.trim();//первые два пустые элементы добавляют лишние пробелы, убираем их
          //если позиции не существует, добавляем в конец списка
          if (pos >= todoList.getTodos().size()){todoList.add(stroka);}
          //иначе добавляем в указанную позицию
          else {todoList.add(pos, stroka);}
         }
        //если не выполняется хотя бы одно из трех условий
        else {
        strArray[0] = "";//убираем слово ADD
        String stroka = String.join(" ", strArray);//пересобираем строку
        stroka = stroka.trim();//удаляем лишние пробелы
        todoList.add(stroka);//долбавляем строку в конец списка
      }
    }

    //обработка по слову EDIT
    private static void editPosArrayList(String[] strArray){
      //если второе слово - число и оно положительное
      if (strArray[1].matches("\\d+") && (Integer.parseInt(strArray[1]) >= 0)){
      int pos = Integer.parseInt(strArray[1]);//сохраняем позицию
      //"уничтожаем" служебные слова
      strArray[0] = "";
      strArray[1] = "";
      //пересобираем строку из массива - остается только нстрока под замену в списке
      String stroka = String.join(" ", strArray);
      stroka = stroka.trim();//первые два пустые элементы добавляют лишние пробелы, убираем их
      todoList.edit(stroka, pos);}}//заменяем строку по позиции

    public static void main(String[] args) {

      //информация
      System.out.println("Введите команду в одном из форматов:" + System.lineSeparator() +
          "ADD строка" + System.lineSeparator() + "ADD номер_позиции строка" +
          System.lineSeparator() + "EDIT номер_позиции строка" + System.lineSeparator()
          + "LIST" + System.lineSeparator() + "DELETE номер_позиции" + System.lineSeparator()
          + "нумерация позиций начинается с нуля" + System.lineSeparator() + "0 - выход");
      Scanner scanner = new Scanner(System.in);
        while (true) {//бесконечный цикл
          String input = scanner.nextLine();//сканер по строкам
          if (input.equals("0")) {//выход из цикла/программы по введению нуля
            break;
          }

          //преобразуем введенное в массив строк по разделителю пробелу
          String[] strokaArray = input.split(" ");

          //если первое введеное слово ADD
          if (strokaArray[0].equals("ADD")) {
            //передаем в обработку массив введеных слов
            addToArrayList(strokaArray);
          }

          //если первое введеное слово EDIT
          if (strokaArray[0].equals("EDIT")) {
            //передаем в обработку массив введеных слов
            editPosArrayList(strokaArray);
          }

          //если первое введеное слово LIST
          if (strokaArray[0].equals("LIST")) {
            //выводим список циклом for each
            for (String s : todoList.getTodos()) {
              System.out.println(s);
            }
          }

          //если первое введеное слово DELETE
          if (strokaArray[0].equals("DELETE")) {
            //проверка, что слов больше одного и второе "слово" - число
            if ((strokaArray.length > 1) && (strokaArray[1].matches("\\d+"))) {
              //передаем в экземпляр класса второе слово-число, как аргумент
              todoList.delete(Integer.parseInt(strokaArray[1]));
            }
          }
        }
    }
}
