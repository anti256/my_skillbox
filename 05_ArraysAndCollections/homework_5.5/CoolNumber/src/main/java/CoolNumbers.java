import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class CoolNumbers {
 //массив-константа с допустимыми буквами
 static String[] NUMBER_LETTERS = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};

    public static List<String> generateCoolNumbers() {
        ArrayList<String> autoNumbers = new ArrayList<>();//объявление ArrayList
        StringBuilder builder = new StringBuilder();//объявление StringBuilder для быстроты сложения строк
        for (int region = 1; region < 200; region++){//регион
            String defaultRegion = Integer.toString(region);
            if (region < 10){defaultRegion = "0" + defaultRegion;}//если число меньше десяти, в номере региона
                                                                  //будет ведущий ноль
         for (int digit = 1; digit <10; digit++){//цифра счастливого числа
             String digitOfNumber = Integer.toString(digit*111);

             //комбинаторика в трех циклах
             for (int first = 0; first < NUMBER_LETTERS.length;first++){
              for (int second = 0; second < NUMBER_LETTERS.length;second++){
               for (int third = 0; third < NUMBER_LETTERS.length;third++){
                   builder.delete(0,builder.length());
                   //сборка строки - счастливого номера
                   builder.append(NUMBER_LETTERS[first]);
                   builder.append(digitOfNumber);
                   builder.append(NUMBER_LETTERS[second]);
                   builder.append(NUMBER_LETTERS[third]);
                   builder.append(defaultRegion);
                   autoNumbers.add(builder.toString());
               }
              }
             }
         }
        }
        return autoNumbers;//Collections.emptyList();
    }

    //поиск в ArrayList перебором
    public static boolean bruteForceSearchInList(List<String> list, String number) {
      for (String num:list){
        if (num.equals(number)) {return true;}
      }
      return false;
    }

    //бинарный поиск в ArrayList
    public static boolean binarySearchInList(List<String> sortedList, String number) {
      int search = Collections.binarySearch(sortedList,number);
      if (search >= 0) {return true;}
      return false;
    }

    //поиск в HashSet перебором
    public static boolean bruteForceSearchInHashSet(HashSet<String> hashSet, String number) {
      Iterator<String> itr = hashSet.iterator();
      while (itr.hasNext()) {
        if (itr.next().equals(number)){return true;}
      }
        return false;
    }

    //поиск в TreeSet перебором
    public static boolean bruteForceSearchInTreeSet(TreeSet<String> treeSet, String number) {
      Iterator<String> itr = treeSet.iterator();
      while (itr.hasNext()){
        if (itr.next().equals(number)){return true;}
      }
        return false;
    }

}
