import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        //Замещаемый стримом код
        //создание списка объектов только с нужным годом
     /*List <Employee> currentYearList = new ArrayList<>();
        //перебор исходного списка и наполнение нового
        for (int i = 0; i < staff.size(); i++) {
            if (staff.get(i).getWorkStart().toString().
                substring(staff.get(i).getWorkStart().toString().length()-4).equals(Integer.
                toString(year))) {currentYearList.add(staff.get(i));}
        }
        Employee result = currentYearList.get(0);
        //перебор с получением объекта с максимальной зп
        for (int i = 0; i < currentYearList.size(); i++) {
          if (currentYearList.get(i).getSalary() > result.getSalary()){result = currentYearList
              .get(i);}
        }*/

        //TODO Метод должен вернуть сотрудника с максимальной зарплатой среди тех,
        // кто пришёл в году, указанном в переменной year
        return staff.stream().
            filter((p)-> p.getWorkStart().toString().substring(p.getWorkStart().toString().
                length()-4).equals(Integer.toString(year))).
            max(Comparator.comparing(Employee::getSalary)).
            get();
    }
}