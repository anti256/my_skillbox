import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
  public static void main(String[] args) {
    try{
      //путь к папке куда грузить картинки
      final Path PATH = Paths.get("./11_FilesAndNetwork/homework_11.4/src/images");
      //connect - подключение к html в инете, get - парсинг, создается документ
      Document doc = Jsoup.connect("https://lenta.ru/").get();
      //из документа выбираются все элементы с тегами img с классом g-picture
      Elements elements = doc.select("img.g-picture");
      //парсинг всех полученных элементов
      elements.forEach(element ->
      {String defaultString = element.attr("abs:src");//строка - значение аттрибута src, содержащая абсолютный путь
       try {
         //InputStream - класс для получения данных из разных источников
         //URI.create создает ссылку на универсальный идентификатор ресурса URI из строки
         //toURL преобразует URI в URL
         //openStream создает подключение к данному URL
         InputStream in = URI.create(defaultString).toURL().openStream();
         //Document docFile = Jsoup.connect(defaultString).get();
         if (!Files.exists(PATH)) {//если директории не существует
           Files.createDirectory(PATH);//создаем директорию
         }
         //вырезаем имя файла из строки-пути картинки
         String inString = defaultString.substring(defaultString.lastIndexOf("/") + 1);
         System.out.println(inString);//вывод имен файлов картинок
         //копируем данные из потока, берущего данные по URL в файл на диске
         Files.copy(in, PATH.resolve(Paths.get(inString)), StandardCopyOption.REPLACE_EXISTING);
       }
       //обработчик исключений для InputStream, Files.createDirectory и Files.copy
       catch (IOException ex){
         ex.getStackTrace();
       }
      });
    }
    catch (IOException ex){//обработчик исключений для jsoup
      ex.getStackTrace();
    }
  }
}
//Напишите программу, которая://
//- получает с помощью библиотеки jsoup HTML-код страницы https://lenta.ru;
//- находит в HTML-коде теги img и получает абсолютные ссылки на изображения из атрибута src;
//- скачивает изображения в папку images проекта, при этом сохраняя оригинальные названия файлов;
//- выводит в консоль список c названиями скачанных файлов.