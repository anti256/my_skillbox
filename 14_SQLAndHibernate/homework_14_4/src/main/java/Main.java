import entity.Course;
import entity.LinkedPurchaseList;
import entity.PurchaseList;
import entity.Student;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

  public static void main(String[] args) {

    SessionFactory sessionFactory = SessionFactoryCreate.Create();//создание фабрики сессий

    List<entity.PurchaseList> listPurchaseList = null;//создание пустого списка PurchaseList-ов
    try (Session session = sessionFactory.openSession()){
      Transaction transaction = session.beginTransaction();//открытие транзакции

      Query query = session.createQuery("FROM entity.PurchaseList");//создание запроса
      listPurchaseList = (List<PurchaseList>) query.getResultList();//заполнение списка по результатам запроса

      for (int i = 0; i < listPurchaseList.size(); i++) {//перебор списка

        //создание текста запроса - поиск студента из таблицы Student по имени из списка PurchaseList-ов
        String hql = "FROM " + Student.class.getSimpleName() + " WHERE name = \'" +
            listPurchaseList.get(i).getId().getStudentName() + "\'";
        Student st = (Student) session.createQuery(hql).getSingleResult();//студент по запросу
        Integer idSt = st.getId();//идентификатор студента

        //создание текста запроса - поиск курса из таблицы Courses по имени из списка PurchaseList-ов
        hql = "FROM " + Course.class.getSimpleName() + " WHERE name = \'" +
            listPurchaseList.get(i).getId().getCourseName() + "\'";
        //идентификатор курса
        Integer idCourse = ((Course) session.createQuery(hql).getSingleResult()).getId();

        //создание экземпляра linkedPurchaseList по полученным идентификаторам студента и курса
        LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList(idCourse, idSt);
        System.out.println(linkedPurchaseList.getStudentId());
        System.out.println(linkedPurchaseList.getCourseId());

        session.save(linkedPurchaseList);//сохранение изменений в таблицу
      }
        transaction.commit();//закрепление изменений
    }
    catch (Throwable th){
      th.printStackTrace();
    }

    sessionFactory.close();


  }
}

/*
Напишите код новой таблицы LinkedPurchaseList, которая заполняется на основании
 данных таблицы PurchaseList.
 - student_id
 - course_id
 */