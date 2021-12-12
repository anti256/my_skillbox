import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

public class BankTest extends TestCase{

  long bankMoney;//общее количество денег в банке
  int accountCount = 50;//количество счетов
  Map<String, Account> accountMap = new HashMap<>();//мапа для экземпляра банка
  static ArrayList<Thread> threadList = new ArrayList<>();//список потоков
  Bank bank;//экземпляр банка

  @Override
  public void setUp() throws Exception {
    bankMoney = (long)(accountCount * 100_000);
    for (int i = 0; i < accountCount; i++) {
      String s = "000" + String.valueOf(i + 1);//код счета
      //наполнение мапы аккаунтов. Первоначально всем записываем баланс 100 тыс.
      accountMap.put(s, new Account(100_000, s));
    }
    bank = new Bank(accountMap);//инициализация экземпляра банка
    //создание списка Entry. Со списком легче работать, чем с мапой
    ArrayList<Map.Entry<String, Account>> entryAccountMap = new ArrayList<>(accountMap.entrySet());

    //наполнение списка потоков потоками bank.transfer
    for (int i = 0; i < accountCount-1; i++) {
      String s1 = entryAccountMap.get(i).getKey();//название первого счета
      String s2 = entryAccountMap.get(i+1).getKey();//название второго счета
      Long ht = (long)(45000 + 10 * Math.random());//сумма перевода от 45 тыс. до 55 тыс.
      Runnable task = new Runnable() {//создаем поток
        public synchronized void run() {//прописываем внутренности потока
        try {
          bank.transfer(s1, s2, ht);//перевод денег
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }};
      threadList.add(new Thread(task));//заносим поток в список
    }
  }

  //Проверка
  public void testCalculateBankMoney() throws InterruptedException {

    //перебор всех потоков в списке
    for (Thread t:threadList
    ) {
      t.start();//запуск всех потоков
      //Thread.sleep(1000);//приостановка, чтоб можно было успеть засечь потоки в visualLM
    }

    double actual = bank.getSumAllAccounts();//актуальное значение вычислений
    double expected = bankMoney;//ожидаемое значение

    assertEquals(expected, actual);//сравнение вычисленного и ожидаемого
  }
}
