public class CardAccount extends BankAccount {

  //снять
  //если снимаемая сумма больше нуля и меньше или равна имеющийся
  //снимается 1,01 от снимаемой суммы
  public void take(double amountToTake) {
    if ((amountToTake > 0) && (amountToTake <= amount)){amount -= amountToTake*1.01;}
  }

}
/*
CardAccount — карточный счёт, в дополнение к условиям BankAccount — при снятии денег должна
взиматься комиссия 1% от суммы списания. Пример: при попытке снять со счёта 100 рублей
должен списаться 101 рубль.
 */