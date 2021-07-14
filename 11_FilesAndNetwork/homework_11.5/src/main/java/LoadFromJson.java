import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LoadFromJson {
  private LinkedHashMap<String, List<Station>> loadStationsJson;//ключ - индекс линии, значение - список станций на линии
  private LinkedHashMap<String, String> loadLinesJson;//ключ - индекс линии, значение - название линии
  private ArrayList<ArrayList<Station>> loadConnectionsJson;//список узлов пересадки
  private int connectionCount;//количество станций-пересадок

  public LoadFromJson(String jsonPath) {//конструктор
    loadStationsJson = new LinkedHashMap<>();
    loadLinesJson = new LinkedHashMap<>();
    connectionCount = 0;
    loadConnectionsJson = new ArrayList<ArrayList<Station>>();
    fromJsonToObjects(jsonPath);
  }//конец конструктора

  //геттеры
  public LinkedHashMap<String, List<Station>> getLoadStationsJson() {return loadStationsJson;}

  public LinkedHashMap<String, String> getLoadLinesJson() {return loadLinesJson;}

  public ArrayList<ArrayList<Station>> getLoadConnectionsJson() {return loadConnectionsJson;}

  public int getConnectionCount() {return connectionCount;}

  private void fromJsonToObjects(String path) {
    try {
      JSONParser parser = new JSONParser();//создаем объект парсера
      //читаем парсером из файла в JSONObject
      JSONObject jsonData = (JSONObject) parser.parse(getJsonFile(path));
      //вырезаем json-массив линий из JSONObject
      JSONArray linesArray = (JSONArray) jsonData.get("lines");
      parseLines(linesArray);
      //вырезаем json-объект станций из JSONObject
      JSONObject stationsObject = (JSONObject) jsonData.get("stations");
      parseStations(stationsObject);
      //вырезаем json-массив узлов пересадок из JSONObject
      JSONArray connectionsArray = (JSONArray) jsonData.get("connections");
      parseConnections(connectionsArray);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  //чтение из файла в строку
  private String getJsonFile(String path) {
    StringBuilder builder = new StringBuilder();
    try {
      List<String> lines = Files.readAllLines(Paths.get(path));
      lines.forEach(line -> builder.append(line));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return builder.toString();
  }

  //наполнение map линий линиями из json-массива линий
  private void parseLines(JSONArray linesArray) {
    linesArray.forEach(lineObject -> {
      JSONObject lineJsonObject = (JSONObject) lineObject;
      loadLinesJson.put((String) lineJsonObject.get("number"),(String) lineJsonObject.get("name"));
    });
  }

  //наполнение map станций станциями из json-объекта станций
  private void parseStations(JSONObject stationsObject) {
    //keySet - индексы линий
    stationsObject.keySet().forEach(lineNumberObject ->
    {
      String lineIndex = (String) lineNumberObject;//текущий индекс линии
      //для текущего индекса линии lineNumberObject выбираем массив станций
      JSONArray stationsArray = (JSONArray) stationsObject.get(lineNumberObject);
      ArrayList<Station> stationList = new ArrayList<Station>();
      stationsArray.forEach(stationObject ->//проход по станциям текущей линии
      { Station station = new Station(lineIndex, (String) stationObject);//создаем объект-станцию
        stationList.add(station);//добавляем его в текущий массив
      });
      loadStationsJson.put(lineIndex,stationList);//добавляем перечень станций в map
    });
  }

  //наполнение ArrayList узлов пересадок узлами из json-массива узлов пересадок
  private void parseConnections(JSONArray connectionsArray){
    connectionsArray.forEach(connectionObject ->//перебирает все элементы-массивы с объектами-станциями (узлы)
    {
      JSONArray connection = (JSONArray) connectionObject;//connection это массив - текущий узел
      ArrayList<Station> connectionStations = new ArrayList<>();
      connection.forEach(item ->//перебирает все объекты-станции в массивах-узлах метро
      {
        JSONObject itemObject = (JSONObject) item;
        Station station = new Station((String) itemObject.get("line"), (String) itemObject.get("station"));//создаем объект-станцию
        connectionStations.add(station);//наполняем массив-узел станциями
      });
      loadConnectionsJson.add(connectionStations);//добавляем массив-узел в список узлов
    });
    connectionCount = loadConnectionsJson.size();//количество переходов
  }

}
