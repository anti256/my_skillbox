public class Main {

  public static void main(String[] args) {

  }

  public static int calculateSalarySum(String text) {
    int summ = 0;//начальная сумма
    text = text.replaceAll("[^0-9]", " ");//заменяем все кроме чисел пробелами
    text = text.trim();//удаляем пробелы по бокам строки
    String[] strArray = text.split("\\s+");//делим строку пробелами
    if (!strArray[0].equals("")) {//если в строке не было чисел, первый элемент будет пустой строкой
      //складываем "числа" в массиве
      for (int i = 0; i < strArray.length;){summ += Integer.parseInt(strArray[i++]);}
    }
    return summ;
  }

}