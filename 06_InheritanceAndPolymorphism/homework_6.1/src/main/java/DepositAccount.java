import java.util.Calendar;
import java.util.GregorianCalendar;

public class DepositAccount extends BankAccount {
  public Calendar lastIncome = new GregorianCalendar();

  //положить
  public void put(double amountToPut) {
    if (amountToPut > 0){
      lastIncome = GregorianCalendar.getInstance();
      amount += amountToPut;}
  }

  //снять
  public void take(double amountToTake) {
    Calendar nowDate;
    nowDate = GregorianCalendar.getInstance();
    if (nowDate.get(Calendar.MONTH) >= lastIncome.get(Calendar.MONTH) && (amountToTake > 0) &&
        (amountToTake <= amount)){amount -= amountToTake;}
  }
}
/*
DepositAccount — депозитный расчётный счёт, в дополнение к условиям BankAccount — нельзя снимать
деньги в течение одного месяца после последнего пополнения. Переменную, в которой хранится дата
последнего внесения, назовите lastIncome. Тип переменной используйте Calendar или LocalDate.
 */