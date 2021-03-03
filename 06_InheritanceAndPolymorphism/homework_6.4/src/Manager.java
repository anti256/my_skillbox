public class Manager extends Worker {//implements Employee{

  public Manager(){//конструктор
    super();//конструктор родительского класса
    status = "Менеджер";//должность
    salary = (int) (30_000 + 30_000 * Math.random());//оклад от 30 до 60 тыс.
  }

  //переопределенный геттер месячной зп
  @Override
  public int getMonthSalary() {return monthSalary = (int)(salary + 0.05 * (115000 + 25000 * Math.random()));}
}
/*зарплата складывается из фиксированной части и бонуса в виде 5% от заработанных для компании
денег. Количество заработанных денег для компании генерируйте случайным образом
от 115 000 до 140 000 рублей.*/