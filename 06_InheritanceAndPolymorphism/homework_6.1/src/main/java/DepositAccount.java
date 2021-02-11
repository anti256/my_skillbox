import java.time.LocalDate;

public class DepositAccount extends BankAccount {

  private LocalDate lastIncome;//дата внесения платежа

  public LocalDate getLastIncome() {//геттер даты внесения платежа
    return lastIncome;
  }

  //положить
  public void put(double amountToPut) {
    if (amountToPut > 0){//если вносимая сумма больше нуля
      lastIncome = LocalDate.now();//запоминаем дату внесения платежа
      amount += amountToPut;}//добавляем сумму на счет
  }

  //снять
  public void take(double amountToTake) {
    LocalDate nowDate, plusMonthByLastIncome;
    nowDate = LocalDate.now();//запоминаем сегодняшнюю дату
    plusMonthByLastIncome = lastIncome.plusMonths(1);//дата внесения платежа плюс месяц
    plusMonthByLastIncome = plusMonthByLastIncome.minusDays(1);//плюс месяц минус один день
    //если прошло минимум месяц и снимаемая сумма больше нуля и меньше или равна имеющийся
    if (nowDate.isAfter(plusMonthByLastIncome) && (amountToTake > 0) &&
        (amountToTake <= amount)){amount -= amountToTake;}//уменьшаем имеющуюся сумму
  }
}
/*
DepositAccount — депозитный расчётный счёт, в дополнение к условиям BankAccount — нельзя снимать
деньги в течение одного месяца после последнего пополнения. Переменную, в которой хранится дата
последнего внесения, назовите lastIncome. Тип переменной используйте Calendar или LocalDate.
 */