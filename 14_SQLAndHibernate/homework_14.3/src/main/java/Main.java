import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

  public static void main(String[] args) {

    SessionFactory sessionFactory = SessionFactoryCreate.Create();

    List<Student> listStudent = null;
    List<Subscription> listSubscription = null;
    try (Session session = sessionFactory.openSession()){
      session.beginTransaction();

      Query query = session.createQuery( "FROM Student");
      listStudent = (List<Student>) query.getResultList();

      Query query1 = session.createQuery("FROM Subscription");
      listSubscription = (List<Subscription>) query1.getResultList();
      System.out.println(listSubscription.toString());


    }
    catch (Throwable th){
      th.printStackTrace();
    }

    sessionFactory.close();

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    System.out.println("Студенты:");
    for (int i=0; i < listStudent.size(); i++){
      System.out.println(" - " + listStudent.get(i).getName());
      Student st = listStudent.get(i);
      listSubscription.forEach(a->{if(a.getId().getStudent().equals(st)){
        System.out.println("\t\t- " + a.getId().getCourse().getName() +
            " - преподаватель " + a.getId().getCourse().getTeacher().getName() +
            " - подписка " + df.format(a.getSubscriptionDate()) +
            " - цена подписки " + a.getId().getCourse().getPrice());
      }});

    }



  }
}

/*
Подключите в ваш проект библиотеку Hibernate.
Создайте класс для таблицы Courses.
Напишите код, который выводит имя и количество студентов любого курса.
 */
