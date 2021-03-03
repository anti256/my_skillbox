public class TopManager extends Worker {//implements Employee{

  public  TopManager(){//конструктор
    super();//конструктор родительского класса
    status = "Топ-Менеджер";//должность
    salary = (int) (100_000 + 30_000 * Math.random());//оклад от 100 до 130 тыс.
  }

  //геттер месячной зп
  @Override
  public int getMonthSalary() {
    if (workPlace.getIncome() > 10_000_000){return monthSalary = (int) ((int)(2.5 * salary));}
    return monthSalary = salary;}
}
/*зарплата складывается из фиксированной части и бонуса в виде 150% от заработной платы,
если доход компании более 10 млн рублей*/