import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
  static final String SITE_PARSE = "https://lenta.ru";
  static  final String TARGET_DIR = "./15_Multithreading/ForkJoinPool/src/out";
  static  final String TARGET_FILE = "map.txt";

  public static void main(String[] args) {
    ArrayList<String> readyList = new ArrayList<>();
    readyList.addAll(new UrlListFromSite(SITE_PARSE).getUrlReadyList());//получаем список ссылок
    ArrayList<String> readyTabList = PlusTabBefore.putTab(readyList);//расстановка табов

    //запись в файл
    try {
      if (!Files.exists(Paths.get(TARGET_DIR))) {//если директории не существует
        Files.createDirectory(Paths.get(TARGET_DIR));//создаем директорию
      }
      PrintWriter writer = new PrintWriter(TARGET_DIR + "/" + TARGET_FILE);
      for (int i = 0; i < readyTabList.size(); i++) {
        writer.write(readyTabList.get(i) + "\n");
      }
      writer.flush();
      writer.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}

