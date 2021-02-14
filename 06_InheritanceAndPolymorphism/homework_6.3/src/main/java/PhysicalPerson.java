public class PhysicalPerson extends Client {

  public PhysicalPerson(){//конструктор без аргументов
    amount = 0.0d;
  }

  //положить
  public void put(double amountToPut) {
    if (amountToPut >= 0) {amount +=amountToPut;}//прибавить, если сумма больше нуля
  }

  //снять
  public void take(double amountToTake) {
    //снять, если сумма больше нуля и меньше имеющегося
    if ((amountToTake >= 0) && (amountToTake <=amount)) {amount -= amountToTake;}
  }

}
/*Если передать в метод пополнения отрицательное значение, сумма на счёте не должна измениться.
При попытке снять сумму больше, чем есть на счете, сумма не списывается со счёта,
сумма на счёте не изменяется.
 */