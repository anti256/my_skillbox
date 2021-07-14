import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteLoadToJsonFile {
  private static JSONObject dataJson = new JSONObject();

  public static void writeToJsonFile (LoadFromSite loadData, String pathToJson, String jsonFile){
    stationToJson(loadData.getLoadStations());//запись в JSONObject информации по станциям
    connectionToJson(loadData.getLoadConnections());//добавление в JSONObject информации по станциям-пересадкам
    linesToJson(loadData.getLoadLines());//добавление в JSONObject информации по линиям
    try {
      if (!Files.exists(Paths.get(pathToJson))) {//если директории не существует
        Files.createDirectory(Paths.get(pathToJson));}//создаем директорию
      String pathString = pathToJson + "/" +jsonFile;//строка-адрес json-файла
      //переделка json-строки, чтоб она выглядела по-человечески
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String prettyJsonString = gson.toJson(dataJson);
      //запись
      FileWriter writer = new FileWriter(pathString);
      writer.write(prettyJsonString);
      writer.flush();
      writer.close();
    }
    catch (IOException ex) {ex.getStackTrace();}
    }

  //json-станции
  //структура - "stations":объекты
  //где объекты - "индекс линии":массив
  //где массив - массив станций определенной линии
  private static void stationToJson (Map<String, List<String>> loadMap){
    JSONObject defaultStation = new JSONObject();//json-объект
    for(Entry entry: loadMap.entrySet()) {
      //получить ключ
      String lineIndex = entry.getKey().toString();//"индекс линии"
      //создание json-массива станций определенной линии
      JSONArray defaultJsonArray = new JSONArray();
      defaultJsonArray.addAll((ArrayList) entry.getValue());
      //создание json-объекта в список "stations"
      defaultStation.put(lineIndex,defaultJsonArray);
    };
    //добавление строки-обекта в объект json-станции
    dataJson.put("stations",defaultStation);
  }

  //json-линии
  //структура - "lines": массив
  //где массив - массив объектов
  //где объекты из двух строк - "number":"индекс линии"
  //                            "name":"имя линии"
  private static void linesToJson(Map<String, String> loadMap) {
    JSONArray defaultArray = new JSONArray();//json-массив
    for (Entry entry : loadMap.entrySet()) {
      //получить ключ
      String lineIndex = entry.getKey().toString();//"индекс линии"
      //имя линии
      String lineName = entry.getValue().toString();
      JSONObject defaultLine = new JSONObject();//текущая линия
      defaultLine.put("number", lineIndex);
      defaultLine.put("name", lineName);
      defaultArray.add(defaultLine);//добавление в массив объекта-линии
    }
    //добавление строки-объекта в объект json-линии
    dataJson.put("lines", defaultArray);
  }

  //json-станции пересадки
  //структура - "connections": массив
  //где массив - массив узлов пересадки
  //где узлы пересадки - массив объектов
  //где объекты из двух строк - "line":"индекс линии"
  //                            "station":"имя станции"
  private static void connectionToJson (HashSet<TreeSet<String>> loadConnect){
    JSONArray defaultArray = new JSONArray();//массив узлов

    Iterator<TreeSet<String>> itr1 = loadConnect.iterator();
    while (itr1.hasNext()){//проход по списку узлов
      JSONArray nodeArray = new JSONArray();//массив узла
      Iterator<String> itr2 = itr1.next().iterator();
      while (itr2.hasNext()){
        JSONObject defaultConectStation = new JSONObject();//текущая станция узла
        String defString = itr2.next();
        //создание объекта станции-перехода
        defaultConectStation.put("line",defString.substring(0,defString.indexOf("=")));
        defaultConectStation.put("station",defString.substring(defString.indexOf("=")+1));
        nodeArray.add(defaultConectStation);//добавление станции в узел
      }
      defaultArray.add(nodeArray);//добавление узла
    }
    //добавление строки-объекта в объект json-узлы переходов
    dataJson.put("connections",defaultArray);
  }
}
