import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

  public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/skillbox";
    String user = "root";
    String password = "935117256A1B2C3D4_";
    try {
      Connection connection = DriverManager.getConnection(url, user, password);
      Statement statement = connection.createStatement();
      ResultSet resultset =  statement.executeQuery("SELECT courses.name as Courses_name, "
          + "COUNT(pl.subscription_date) /(MAX(MONTH(pl.subscription_date))- MIN(MONTH(pl.subscription_date))) as average "
          + "FROM PurchaseList pl JOIN courses ON courses.name=pl.course_name GROUP BY courses.name ORDER BY courses.name;");
      while(resultset.next()){
        String courses_name = resultset.getString("Courses_name");
        String average = resultset.getString("average");
        System.out.println(courses_name + " - " + average);
      }
      connection.close();
      statement.close();
      resultset.close();
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
  }
}
