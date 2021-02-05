import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {
  static String FIRST_NUMBER = "Н555СР116";//константа правильный номер - поиск true
  static String SECOND_NUMBER = "Н567СР116";//константа неправильный номер - поиск false

  //метод поиска перебором в ArrayList
  private static void arraySearchBrute (ArrayList aList, String numberCar){
    String noStatus = "";//строка не найден/найден
    Boolean searchStatus = false;//результат поиска, объвляется заранее, чтобы не увеличивать время поиска
    long start = System.nanoTime();//первая точка времени
    searchStatus = CoolNumbers.bruteForceSearchInList(aList, numberCar);//поиск
    long end = System.nanoTime();//вторая точка времени
    if (!searchStatus) {noStatus = " не";}//установка переменной, если не найден
    System.out.println("Поиск перебором: номер " + numberCar + noStatus +
        " найден, поиск занял " + (end - start) + " нс" );//вывод результата
  }

  //метод бинарного поиска в ArrayList
  private static void arraySearchBinar (ArrayList aList, String numberCar){
    String noStatus = "";//строка не найден/найден
    Boolean searchStatus = false;//результат поиска, объвляется заранее, чтобы не увеличивать время поиска
    long start = System.nanoTime();//первая точка времени
    searchStatus = CoolNumbers.binarySearchInList(aList, numberCar);//поиск
    long end = System.nanoTime();//вторая точка времени
    if (!searchStatus) {noStatus = " не";}//установка переменной, если не найден
    System.out.println("Бинарный поиск: номер " + numberCar + noStatus +
        " найден, поиск занял " + (end - start) + " нс" );//вывод результата
  }

  //метод поиска перебором в HashSet
  private static void hashsetSearchBrute (HashSet hSet, String numberCar){
    String noStatus = "";//строка не найден/найден
    Boolean searchStatus = false;//результат поиска, объвляется заранее, чтобы не увеличивать время поиска
    long start = System.nanoTime();//первая точка времени
    searchStatus = CoolNumbers.bruteForceSearchInHashSet(hSet, numberCar);//поиск
    long end = System.nanoTime();//вторая точка времени
    if (!searchStatus) {noStatus = " не";}//установка переменной, если не найден
    System.out.println("Поиск в HashSet: номер " + numberCar + noStatus +
        " найден, поиск занял " + (end - start) + " нс" );//вывод результата
  }

  //метод поиска перебором в TreeSet
  private static void treesetSearchBrute (TreeSet tSet, String numberCar){
    String noStatus = "";//строка не найден/найден
    Boolean searchStatus = false;//результат поиска, объвляется заранее, чтобы не увеличивать время поиска
    long start = System.nanoTime();//первая точка времени
    searchStatus = CoolNumbers.bruteForceSearchInTreeSet(tSet, numberCar);//поиск
    long end = System.nanoTime();//вторая точка времени
    if (!searchStatus) {noStatus = " не";}//установка переменной, если не найден
    System.out.println("Поиск в TreeSet: номер " + numberCar + noStatus +
        " найден, поиск занял " + (end - start) + " нс" );//вывод результата
  }

    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */

    public static void main(String[] args) {

      //заполнение переменных ArrayList, HashSet, TreeSet списком счастливых номеров
      ArrayList<String> arrayListNumber = new ArrayList(CoolNumbers.generateCoolNumbers());
      HashSet<String> hashSetNumber = new HashSet<>(CoolNumbers.generateCoolNumbers());
      TreeSet<String> treeSetNumber = new TreeSet<>();
      treeSetNumber.addAll(CoolNumbers.generateCoolNumbers());

      //методы поисков
      arraySearchBrute(arrayListNumber, FIRST_NUMBER);
      arraySearchBrute(arrayListNumber, SECOND_NUMBER);
      Collections.sort(arrayListNumber);//бинарный посик корректно работает только с отсортированным списком
      arraySearchBinar(arrayListNumber,FIRST_NUMBER);
      arraySearchBinar(arrayListNumber,SECOND_NUMBER);
      hashsetSearchBrute(hashSetNumber, FIRST_NUMBER);
      hashsetSearchBrute(hashSetNumber, SECOND_NUMBER);
      treesetSearchBrute(treeSetNumber, FIRST_NUMBER);
      treesetSearchBrute(treeSetNumber, SECOND_NUMBER);
    }
}

/*Поиск перебором: номер найден/не найден, поиск занял 34нс
Бинарный поиск: номер найден/не найден, поиск занял 34нс
Поиск в HashSet: номер найден/не найден, поиск занял 34нс
Поиск в TreeSet: номер найден/не найден, поиск занял 34нс*/
