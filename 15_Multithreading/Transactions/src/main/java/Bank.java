import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Bank  extends Thread{

    private Map<String, Account> accounts;
    private final Random random = new Random();

    //конструктор по имени счета
    public Bank (Map<String, Account> accounts){
      this.accounts = accounts;
    }

    //проверка службой безопасности
    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

  /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        //если такие аккаунты существуют
        if (accounts.containsKey(fromAccountNum) && accounts.containsKey(toAccountNum)){
            //если хотя бы один из этих аккаунтов не заблокирован
            if (!accounts.get(fromAccountNum).isBlocked() || !accounts.get(toAccountNum).isBlocked())
                //если на счете-доноре хватает средств
                if (accounts.get(fromAccountNum).getMoney() >= amount){
                    //снимаем со счета-донора средства
                    accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
                    //зачисляем на счет-получатель средства
                    accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
                    //если переводимая сумма больше 50000
                    if (amount > 50000 &&
                        //и служба безопасности решила заблокировать счета
                        isFraud(fromAccountNum,toAccountNum,amount)){
                        //блокируем счета
                        accounts.get(fromAccountNum).setBlocked();
                        accounts.get(toAccountNum).setBlocked();
                    }
                }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public synchronized long getBalance(String accountNum) {
        Set<String> setKeys = accounts.keySet();
        if (accounts.containsKey(accountNum)) return accounts.get(accountNum).getMoney();
        else return 0;
    }

    //суммарный баланс всех аккаунтов
    public synchronized long getSumAllAccounts() {
        long balanceAll = 0l;
        for(Map.Entry<String, Account> entry: accounts.entrySet()) {// get value
            String accNum = entry.getKey();
            balanceAll += getBalance(accNum);
        }
        return balanceAll;
    }

}
