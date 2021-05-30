

public class Main {
    public static void main(String[] args) {
        final String CSV_PATH = "./11_FilesAndNetwork/files/movementList.csv";
          Movements objMovements = new Movements(CSV_PATH);
      System.out.println("Сумма расходов: " + objMovements.getExpenseSum() + "руб.");
      System.out.println("Сумма доходов: " + objMovements.getIncomeSum() + "руб." +
          System.lineSeparator());
      System.out.println("Суммы расходов по организациям:");
      System.out.println(objMovements.getExpenceSumFirm());
      System.out.println("Суммы доходов по организациям:");
      System.out.println(objMovements.getIncomeSumFirm());
    }



}
/*
- Напишите код, который будет читать файл csv банковской выписки movementsList.csv и парсить
полученные строки. Путь к файлу выписки храните в константе. Получение суммы расхода и дохода по
всем операциями реализуйте в классе Movements, в методах getExpenseSum() и getIncomeSum()
соответственно. Проверьте парсинг и получение сумм с помощью тестов.
- Класс Movements можете дополнять необходимыми методами для реализации решения.
- Код должен выводить сводную информацию по этой выписке: общий приход, общий расход и разбивку расходов.

 Примеры работы программы

Сумма расходов: 398 563.39 руб.
Сумма доходов: 289 890.06 руб.

Суммы расходов по организациям:
RUSMOSKVA56  SHLOVE REPUBLIC        1 081.53 руб.
RUSMOSCOW42 SHCL ETOILE                     126.34 руб.
RUSPUSHKINO105ZOOMAGAZIN 4             217.65 руб.

 */