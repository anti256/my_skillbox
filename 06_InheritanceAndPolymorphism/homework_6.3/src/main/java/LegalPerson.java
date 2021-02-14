public class LegalPerson extends Client {

  public LegalPerson(){//конструктор без аргументов
    amount = 0.0d;
  }

  //положить
  public void put(double amountToPut) {
    if (amountToPut >= 0) {amount +=amountToPut;}//прибавить, если сумма больше нуля
  }

  //снять
  public void take(double amountToTake) {
    //если сумма больше нуля и меньше имеющегося, снять больше на 1%
    if ((amountToTake >= 0) && (amountToTake <=amount)) {amount -= (1.01*amountToTake);}
  }

}
  //У LegalPerson — все условия PhysicalPerson и дополнительно снятие с комиссией 1%.
