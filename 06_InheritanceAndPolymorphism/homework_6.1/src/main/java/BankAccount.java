public class BankAccount {
  protected double amount = 0.0d;

  public double getAmount() {
    return amount;
  }

  //положить
  public void put(double amountToPut) {
    if (amountToPut > 0){amount += amountToPut;}
  }

 //снять
  public void take(double amountToTake) {
    if ((amountToTake > 0) && (amountToTake <= amount)){amount -= amountToTake;}
  }

  public boolean send(BankAccount receiver, double amount){
    double wasAmount = receiver.getAmount();
    double wasSender = getAmount();
    receiver.put(amount);
    take(amount);
    return (receiver.getAmount() == wasAmount + amount) && (getAmount() == wasSender - amount);
  }

}

/*
BankAccount — пополнение и списание происходит без комиссии. Если передать в метод пополнения
отрицательное значение, сумма на счёте не должна измениться. При попытке снять большую сумму,
чем есть на счёте, сумма не списывается со счёта, сумма на счёте не изменяется.
 */