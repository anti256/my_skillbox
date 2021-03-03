public class Operator extends Worker{

  public Operator(){//конструктор
    super();//конструктор родительского класса
    status = "Оператор";//должность
    salary = (int) (30_000 + 30_000 * Math.random());//оклад от 30 до 60 тыс.
  }

  @Override
  public int getMonthSalary() {//геттер месячной зп
    return monthSalary = salary;//в принципе метод можно было не писать, а месячную зп прописать в конструкторе
  }
}
//зарплата складывается только из фиксированной части