import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        System.out.println(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        Collections.sort(staff, (o1, o2) -> {
            int compareResult = o1.getSalary().compareTo(o2.getSalary());
            if (compareResult == 0) {return o1.getName().compareTo(o2.getName());}
            return compareResult;
        });
    }
}

//В классе Main реализуйте метод findEmployeeWithHighestSalary(), который должен выделить
// сотрудников, пришедших в выбранном году, и среди них выявить сотрудника с максимальным
// значением заработной платы, используя Stream API.