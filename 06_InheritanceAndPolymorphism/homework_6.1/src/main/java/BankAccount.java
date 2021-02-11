public class BankAccount {
  protected double amount = 0.0d;//сумма на счету protected, чтобы нельзя было изменить из других
  // классов, но можно было наследовать

  public double getAmount() {//геттер суммы вклада
    return amount;
  }

  //положить
  //если вносимая сумма больше нуля, добавляем на счет
  public void put(double amountToPut) {
    if (amountToPut > 0){amount += amountToPut;}
  }

 //снять
 //если снимаемая сумма больше нуля и меньше или равна имеющийся
  public void take(double amountToTake) {
    if ((amountToTake > 0) && (amountToTake <= amount)){amount -= amountToTake;}
  }

  public boolean send(BankAccount receiver, double amount){
    double wasAmount = receiver.getAmount();//сумма на счете-получателе
    double wasSender = getAmount();//сумма на счете-отправителе
    receiver.put(amount);//прибавляем у получателя
    take(amount);//убавляем у отправителя
    //у получателя должно прирасти и у отправителя должно убавиться
    return (receiver.getAmount() == wasAmount + amount) && (getAmount() == wasSender - amount);
  }

}

/*
BankAccount — пополнение и списание происходит без комиссии. Если передать в метод пополнения
отрицательное значение, сумма на счёте не должна измениться. При попытке снять большую сумму,
чем есть на счёте, сумма не списывается со счёта, сумма на счёте не изменяется.
 */