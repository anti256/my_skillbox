abstract class Worker implements Comparable<Worker> , Employee {//родительский класс для всех должностей

  protected String name = "";//имя работника
  protected String status = "";//оператор - менеджер - топ-менеджер
  protected Company workPlace;//указатель на компанию

  protected int salary = 0;//оклад
  protected int monthSalary = 0;//месячная зп

  protected Worker(){//конструктор
    name = FullNameRandom.getFullName();
  }//ФИО сотрудника

  //правило сравнения сотрудников по месячной зп, сначало с самыми большими месячнымизп
  @Override
  public int compareTo(Worker worker) {
    if (monthSalary > worker.monthSalary){return -1;}
    if (monthSalary < worker.monthSalary){return 1;}
    return 0;
  }

  @Override
  public int getMonthSalary() {
    return salary;
  }//геттер месячной зп
}
