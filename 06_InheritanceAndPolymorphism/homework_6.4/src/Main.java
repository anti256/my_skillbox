import java.util.Collections;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите название создаваемой компании");
    String inputCompanyName = scanner.nextLine();
    Company company;
    if (inputCompanyName.length() == 0) {System.exit(0);}//если пустой ввод - выход из программы
    company = new Company(inputCompanyName);


    while (true) {//бесконечный цикл
      printInfo();
      String input = scanner.nextLine();

      //обработчик ввода 0 - выход из программы
      if (input.equals("0")) {break;}

      //обработчик ввода LIST - вывод списка сотрудников
      if (input.equals("LIST")) {
        System.out.println("Компания - " + company.getCompanyName() + System.lineSeparator() +
            "Количество сотрудников - " + company.staff.size());//информация о компании
        for (int i = 0; i < company.staff.size(); i++){
          System.out.println((i+1) + "." + company.staff.get(i).name + " - " +
              company.staff.get(i).status + " - " + company.staff.get(i).getWorkPlace().getCompanyName());
        }
        continue;
      }

      //преобразуем введенное в массив строк по разделителю пробелу
      String[] strokaArray = input.split(" ");
      switch (strokaArray[0]){
        case ("HIRE")://если первое слово HIRE - прием на работу одного сотрудника
          //если команда состояла из двух слов и второе из них Operator, Manager или TopManager
          if (strokaArray.length == 2 && (strokaArray[1].equals("Operator") ||
              strokaArray[1].equals("Manager") || strokaArray[1].equals("TopManager"))) {
            company.hire(strokaArray[1]);}//найм одного на соответствующую должность;
          break;
        case ("HIRE_ALL")://если первое слово HIRE_ALL - прием на работу списка сотрудников
          //если команда из четырех слов и 2, 3, и 4-е - числа
          if (strokaArray.length == 4 && strokaArray[1].matches("\\d+") && strokaArray[2].matches("\\d+") &&
              strokaArray[3].matches("\\d+")){
            company.hireAll(Integer.parseInt(strokaArray[1]),Integer.parseInt(strokaArray[2]),Integer.parseInt(strokaArray[3]));
          }
          break;
        case ("LIST_HIGH")://если первое слово LIST_HIGH - вывод списка самых высокооплачиваемых сотрудников
          //если команда состояла из двух слов и второе из них число
          if (strokaArray.length == 2 && strokaArray[1].matches("\\d+")){
          company.setIncome();//генерация дохода компании
          company.setMonthSalaryToAll();//пересмотр месячных зп
          Collections.sort(company.staff);//сортировка сотрудников
          //вывод списка пп. ФИО - должность - зп
          //список заканчикается либо на введеном числе либо на последнем в компании сотруднике
          for (int j = 0; (j < Integer.parseInt(strokaArray[1]) && (j < company.staff.size()));j++){
            System.out.println((j+1) + "." + company.staff.get(j).name + " - " +
                company.staff.get(j).status + " зп - " + company.staff.get(j).monthSalary);}
          }
          break;
        case ("LIST_LOW")://если первое слово LIST_LOW - вывод списка самых низкооплачиваемых сотрудников
          //если команда состояла из двух слов и второе из них число
          if (strokaArray.length == 2 && strokaArray[1].matches("\\d+")){
            company.setIncome();//генерация дохода компании
            company.setMonthSalaryToAll();//пересмотр месячных зп
            Collections.sort(company.staff);//сортировка сотрудников
            //вывод списка пп. ФИО - должность - зп
            //список заканчикается либо на введеном числе либо на последнем в компании сотруднике
            for (int j = company.staff.size()-1; (j > (company.staff.size()-1 - Integer.parseInt(strokaArray[1]))) && (j > 0);j--){
              System.out.println((j+1) + "." + company.staff.get(j).name + " - " +
                  company.staff.get(j).status + " зп - " + company.staff.get(j).monthSalary);}
          }
          break;
        case ("FIRE")://если первое слово FIRE - увольнение нескольких случайных сотрудников
          //если команда состояла из двух слов, второе из них число и это число не больше количества сотрудников
          if (strokaArray.length == 2 && strokaArray[1].matches("\\d+") &&
              Integer.parseInt(strokaArray[1]) <= company.staff.size()) {
            for (int i = 0; i < Integer.parseInt(strokaArray[1]); i++){company.fire();}
          }
          break;
      }

    } //конец бесконечного цикла
  }

  private static void printInfo () {
    //информация
    System.out.println(
        System.lineSeparator() + "Введите команду:" + System.lineSeparator() +
            "HIRE должность - найм одного сотрудника на должность Operator, Manager или TopManager" + System.lineSeparator() +
            "HIRE_ALL A B C (через пробел) - найм A операторов, B менеджеров и C топ-менеджеров" + System.lineSeparator() +
            "LIST_HIGH A - вывод количества А самых больших зарплат " + System.lineSeparator() +
            "LIST_LOW A - вывод количества A самых низких зарплат" + System.lineSeparator() +
            "FIRE A - увольнение количества A случайных работников" + System.lineSeparator() +
            "LIST - вывод списка сотрудников" + System.lineSeparator() +
            "Выход - 0");
  }

}
/*
Создайте два метода, возвращающие список указанной длины (count). Они должны содержать сотрудников, отсортированных по убыванию и возрастанию заработной платы:

List<Employee> getTopSalaryStaff(int count),
List<Employee> getLowestSalaryStaff(int count).
 */

/*
Создайте и наймите в компанию: 180 операторов Operator, 80 менеджеров по продажам Manager, 10 топ-менеджеров TopManager.
Распечатайте список из 10–15 самых высоких зарплат в компании.
Распечатайте список из 30 самых низких зарплат в компании.
Увольте 50% сотрудников.
 */