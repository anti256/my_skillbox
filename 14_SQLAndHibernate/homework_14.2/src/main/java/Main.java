import java.util.List;
import java.util.Scanner;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

  public static void main(String[] args) {

    SessionFactory sessionFactory = SessionFactoryCreate.Create();

    List<Course> list = null;
    try (Session session = sessionFactory.openSession()){
      session.beginTransaction();

      Query query = session.createQuery("FROM Course");
      list = (List<Course>) query.getResultList();

    }
    catch (Throwable th){
      th.printStackTrace();
    }

    sessionFactory.close();

    System.out.println("Названия курсов:");
    for (int i = 0; i < list.size(); i++) {
      System.out.println(" - " + list.get(i).getName());
    }

  Scanner scanner = new Scanner(System.in);
  while (true){
    System.out.println("Введите название курса (0 - выход):");
    String input = scanner.nextLine();//сканер по строкам
    if (input.equals("0")) {//выход из цикла/программы по введению нуля
      break;
    }
    Integer count = SearchStudentsCount.Search(list,input);
    if (count.equals(-1)) {
      System.out.println("Неверное название курса");
    } else {
      System.out.println("Количество студентов - " + count);
    }
  } //конец while

  }
}

/*
Подключите в ваш проект библиотеку Hibernate.
Создайте класс для таблицы Courses.
Напишите код, который выводит имя и количество студентов любого курса.
 */
