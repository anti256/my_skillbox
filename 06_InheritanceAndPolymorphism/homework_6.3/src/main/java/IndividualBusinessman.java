public class IndividualBusinessman extends Client {

  public IndividualBusinessman(){//конструктор без аргументов
    amount = 0.0d;
  }

  //положить
  public void put(double amountToPut) {
    if (amountToPut >= 0) {//если сумма больше нуля
      if (amountToPut < 1000.0) {//если сумма меньше 1000, положить на 1% меньше
        amount += (0.99 * amountToPut);
      } else {//иначе, т.е. сумма больше или равно 1000, положить на 0,5% меньше
        amount += (0.995 * amountToPut);
      }
    }
  }

  //снять
  public void take(double amountToTake) {
    //снять, если сумма больше нуля и меньше имеющегося
    if ((amountToTake >= 0) && (amountToTake <=amount)) {amount -= amountToTake;}
  }

}
/*У IndividualBusinessman — все условия PhysicalPerson и дополнительно, пополнение с комиссией 1%,
если сумма меньше 1 000 рублей.
И пополнение с комиссией 0,5%, если сумма больше либо равна 1 000 рублей.
 */