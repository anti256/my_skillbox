import java.math.BigInteger;
import java.time.LocalDate;

public class Operations {
  private String accountType; //Тип счёта - Текущий счёт
  private BigInteger accountNumber;  //Номер счета - 40817813206170024534
  private String currencyType; //Валюта - RUR
  private LocalDate dateOperation;  //Дата операции - 31.05.17
  private String transactionRef;  //Референс проводки - CRD_1U34U7
  private String transactionDescription; //Описание операции - 28548673++++++10    809216  /RU/CARD2CARD ALFA_MOBILE>MOSCOW          31.05.17 31.05.17 1500.00       RUR MCC6536
  private double income; //Приход
  private double expense; //Расход
  private String firm;//фирма

  public Operations (String lineCSV){//конструктор
   String[] defaultStroka = lineCSV.split(",",7);//разрезаем строку на семь частей
   accountType = defaultStroka[0];
   accountNumber = new BigInteger(defaultStroka[1]);
   currencyType = defaultStroka[2];
   transactionRef = defaultStroka[4];
   transactionDescription = defaultStroka[5];
   String[] lDate = defaultStroka[3].split("\\.");
   dateOperation = LocalDate.of(Integer.parseInt(lDate[2]),
                                Integer.parseInt(lDate[1]),
                                Integer.parseInt(lDate[0]));
    //System.out.println(defaultStroka[6]);
   income = noQuotesIncome(defaultStroka[6]);
    //System.out.println(income);
   expense = noQuotesExpense(defaultStroka[6]);
    //System.out.println(expense);
   //вырезка и присвоение полученного значения фирмы из описания операции
   firm = transactionDescription.replace("/","\\");
    firm = firm.substring(firm.lastIndexOf("\\")+1,firm.indexOf("       "));
    firm = firm.strip();
  }

  //приход - обработка двух чисел через запятую, одно в кавычках
  private double noQuotesIncome (String stroka){
    if (stroka.startsWith("\"")) {//если строка начинается с кавычек
      //вырезаем часть между кавычками
      String replay = stroka.substring(1,stroka.lastIndexOf("\""));
      //java требует разделитель точку
      replay = replay.replace(",",".");
      return Double.parseDouble(replay);}
    //если на первом числе нет кавычек - вырезаем строку до запятой
    return Float.parseFloat(stroka.substring(0,stroka.indexOf(",")));
  }

  //расход - обработка двух чисел через запятую, одно в кавычках
  private double noQuotesExpense (String stroka){
    if (stroka.endsWith("\"")){//если строка заканчивается кавычками
      //вырезаем часть между кавычками
      String replay = stroka.substring(stroka.indexOf("\"")+1,stroka.lastIndexOf("\""));
      //java требует разделитель точку
      replay = replay.replace(",",".");
      return Double.parseDouble(replay);}
    //если на втором числе нет кавычек - вырезаем строку после запятой
    return Float.parseFloat(stroka.substring(stroka.lastIndexOf(",")+1));
  }

  //геттеры
  public String getFirm() {return firm;}

  public String getAccountType() {
    return accountType;
  }

  public BigInteger getAccountNumber() {
    return accountNumber;
  }

  public String getCurrencyType() {
    return currencyType;
  }

  public LocalDate getDateOperation() {
    return dateOperation;
  }

  public String getTransactionRef() {
    return transactionRef;
  }

  public String getTransactionDescription() {
    return transactionDescription;
  }

  public double getIncome() {
    return income;
  }

  public double getExpense() {
    return expense;
  }
}
