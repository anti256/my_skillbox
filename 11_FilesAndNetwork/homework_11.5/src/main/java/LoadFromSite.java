import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class LoadFromSite {
  private TreeMap<String, List<String>> loadStations;//ключ - индекс линии, значение - список станций на линии
  private LinkedHashMap<String, String> loadLines;//ключ - индекс линии, значение - название линии
  private HashSet<TreeSet<String>> loadConnections;//список узлов пересадки
  //переход между двумя станциями, состоит из двух станций
  //при проходе на сайте по станциям, если есть станция перехода, TreeSet это связка станции для которой указана станция перехода и
  //любая из указанных станций перехода

  //геттеры
  public TreeMap<String, List<String>> getLoadStations() {return loadStations;}

  public LinkedHashMap<String, String> getLoadLines() {
    return loadLines;
  }

  public HashSet<TreeSet<String>> getLoadConnections() {return loadConnections;}

  public LoadFromSite(String siteUrl){//конструктор
    loadStations = new TreeMap<String, List<String>>();

    loadLines = new LinkedHashMap<>();
    loadConnections = new HashSet<TreeSet<String>>();
    try {
      //connect - подключение к html в инете, get - парсинг, создается документ,
      // maxBodySize(0) - снимает ограничение на размер скачиваемых данных
      Document doc = Jsoup.connect(siteUrl).maxBodySize(0).get();
      //из документа выбираются все элементы с тегами-адресом #metrodata > div > div:nth-child(1n) > span
      //elements фактически является динамическим массивом нарезок hml-кода с данными по линиям
      Elements elements = doc.select("#metrodata > div > div:nth-child(1n) > span");
      //цикл по всем нарезкам-линиям метро
      for (int i = 0; i < elements.size(); i++) {
        String numberLine = elements.get(i).attr("data-line");//индексы линий
        String nameLine = elements.get(i).text();//название линии
        loadLines.put(numberLine,nameLine);//наполнение map линий
        //формирование адресной строки из тегов, фактически проходит по всем элементам, относящимся к определенной нарезке-линии
        String selectString = "#metrodata > div > div:nth-child(" + (2*(i+1)) + ") > div > p:nth-child(1n) > a > span.name";
        //вырезка из документа элементов с названиями станций определенной линии
        Elements elementsStation = doc.select(selectString);
        List<String> stationName = new ArrayList<>();//список станций определенной линии
        elementsStation.forEach(element1 -> stationName.add(element1.text()));//наполняем list названиями станций
        loadStations.put(numberLine,stationName);//наполнение map станций парой индекс линии-list с названиями станций

        //переходы
        for (int j = 0; j < stationName.size(); j++){//проход по всем станциям определенной линии (по list'у)
          //формирование адресной строки из тегов, фактически проходит по всем элементам,
          //относящимся к определенной линии и определенной j-ой станции на этой линии
          String selectConnectString = "#metrodata > div > div:nth-child(" + (2*(i+1)) + ") > div > p:nth-child("+ (j+1)+") > a > span:nth-child(n+3)";
          Elements elementsConnect = doc.select(selectConnectString);
          //если получившийся список не пустой, т.е. имеется какая-то информация, значит это станция перехода
          if (elementsConnect.isEmpty()){continue;}//список пустой - следующая итерация цикла
          String firstStation = numberLine + "=" + stationName.get(j);//первая станция перехода - текущая станция на текущей линии
          elementsConnect.forEach(element3 -> {//проход по списку нарезок станций в узле
            String numberConnectLine = element3.attr("class");//вырезаем аттрибут class
            //из полученного значения аттрибута вырезаем индекс линии
            numberConnectLine = numberConnectLine.replaceAll("t-icon-metroln ln-","");
            String nameConnectStation = element3.attr("title");//вырезаем аттрибут title
            //из полученного значения аттрибута вырезаем название станции
            nameConnectStation = nameConnectStation.replaceAll("переход на станцию «","");
            nameConnectStation = nameConnectStation.substring(0,nameConnectStation.indexOf("»"));
            //добавляем станцию перехода к списку-узлу
            //формат станции перехода - строка индекс линии + символ "=" + имя станции
            //данная строка используется только для записи в json-файл, этот формат проще, чем делать
            //дополнительную конструкцию в виде класса-станции
            //символ "=" нигде не применяется
            TreeSet<String> passage = new TreeSet<String>();
            passage.add(firstStation);
            passage.add(numberConnectLine + "=" + nameConnectStation);
            loadConnections.add(passage);
          });//конец прохода по списку нарезок станций в узле
        }//конец цикла по всем станциям определенной линии
      }//конец цикла по всем нарезкам-линиям метро
    }//конец try
    catch (IOException ex){ex.getStackTrace();}
  }//конец конструктора

}

