import java.util.List;

public class SearchStudentsCount {

  public static Integer Search (List<Course> list, String name){
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(name)){
        return list.get(i).getStudentsCount();
      }
    }
    return -1;
  }

}
