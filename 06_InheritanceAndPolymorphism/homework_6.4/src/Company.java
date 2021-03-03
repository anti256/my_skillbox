import java.util.ArrayList;

public class Company{
  private String companyName;//название компании
  private double income = 0.0d;//начальный доход компании при создании

  public Company (String nameCompany){//конструктор
    companyName = nameCompany;
    this.setIncome();//генерация дохода компании
  }

  public double getIncome() {
    return income;
  }//геттер дохода компании

  //метод генерации дохода компании от 1 млн до 16 млн
  public void setIncome (){income = 1_000_000.0 + 15_000_000.0 * Math.random();}

    ArrayList<Worker> staff = new ArrayList<>();//список сотрудников

   //метод найма одного сотрудника на конкретную должность
   public void hire (String position){
      switch (position){
        case ("Operator"):
          hireOperator();
          break;
        case ("Manager"):
          hireManager();
          break;
        case ("TopManager"):
          hireTopManager();
          break;}
    }

    //метод найма списком на разные должности
    public void hireAll(int operatorCount, int managerCount, int topManagerCount){
      for (int i = 0; i < operatorCount; i++){hireOperator();}
      for (int i = 0; i < managerCount; i++){hireManager();}
      for (int i = 0; i < topManagerCount; i++){hireTopManager();}
    }

  //метод увольнения одного случайного сотрудника
  public void fire(){
      if (staff.size() > 0){staff.remove((int) (staff.size()*Math.random()));}
  }

  private void hireOperator (){//найм одного оператора
    Worker operator = new Operator();
    staff.add(operator);
    //сотруднику прописывается поле указатель на компанию
    staff.get(staff.indexOf(operator)).workPlace = Company.this;
  }

  private void hireManager (){//найм одного менеджера
    Worker manager = new Manager();
    staff.add(manager);
    //сотруднику прописывается поле указатель на компанию
    staff.get(staff.indexOf(manager)).workPlace = Company.this;
  }

  private void hireTopManager (){//найм одного топ-менеджера
    Worker topmanager = new TopManager();
    staff.add(topmanager);
    //сотруднику прописывается поле указатель на компанию
    staff.get(staff.indexOf(topmanager)).workPlace = Company.this;
  }

  //метод пересмотра месячных зп всех сотрудников
  public void setMonthSalaryToAll(){
      for (int i = 0; i <staff.size(); i++){
        staff.get(i).monthSalary = staff.get(i).getMonthSalary();
      }
  }

  public String getCompanyName() {//геттер названия компании
    return companyName;
  }

}


/*Создайте класс компании Company, содержащей сотрудников и реализующей методы:

найм одного сотрудника — hire(),
найм списка сотрудников – hireAll(),
увольнение сотрудника – fire(),
получение значения дохода компании – getIncome().*/
