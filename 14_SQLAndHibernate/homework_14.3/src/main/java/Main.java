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
    List<PurchaseList> listPurchaseList = null;
    try (Session session = sessionFactory.openSession()){
      session.beginTransaction();

      Query query = session.createQuery( "FROM Student");
      listStudent = (List<Student>) query.getResultList();

    }
    catch (Throwable th){
      th.printStackTrace();
    }

    sessionFactory.close();

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    System.out.println("Студенты:");
    for (int i=0; i < listStudent.size(); i++) {
      System.out.println(
          " - " + listStudent.get(i).getName() + " ,id - " + listStudent.get(i).getId());
      if (listStudent.get(i) != null) {
        System.out.println("\tподписки:");
      listStudent.get(i).getSubscriptionList().forEach(a-> System.out.println("\t\t" + a.getCourse().getId() +
          " " + a.getCourse().getName() + " ;регистрация - " + df.format(a.getSubscriptionDate())
      + " " + " ;стоимость - " + a.getCourse().getPrice() + " ;преподаватель - " +
          a.getCourse().getTeacher().getName()));}
      }

  }
}

/*
список
студент - имя - id
    подписки:
        курс_id - курс_name - регистрация - стоимость - учитель
 */