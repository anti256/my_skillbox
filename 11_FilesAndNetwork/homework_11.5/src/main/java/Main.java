

public class Main {

  public static void main(String[] args) {
    final String SITE_METRO = "https://www.moscowmap.ru/metro.html#lines";//сайт
    final String JSON_DATA = "./11_FilesAndNetwork/homework_11.5/src/data";//директория для записи json
    final String JSON_DATA_FILE = "mapMSK.json";//имя файла json
    LoadFromSite lfs = new LoadFromSite(SITE_METRO);//чтение информации из файла

    /*for (Map.Entry<IndexString, List<String>> entry : lfs.getLoadStations().entrySet()) {
      System.out.println(entry.getKey().getValue() + " - " + entry.getKey().hashCode());
    }*/

    WriteLoadToJsonFile.writeToJsonFile(lfs,JSON_DATA,JSON_DATA_FILE);//запись информации в json
    LoadFromJson lfj = new LoadFromJson(JSON_DATA + "/" + JSON_DATA_FILE);
    //lfj.getLoadLinesJson().entrySet().forEach(a-> System.out.println(a.getValue()));
    System.out.println("Количество переходов - " + lfj.getConnectionCount());
    ShowMetroReport.show(lfj);
  }

}

/*Напишите программу, которая:
1. Получает HTML-код страницы «Список станций Московского метрополитена» https://www.moscowmap.ru/metro.html#lines с помощью библиотеки jsoup.
2. Парсит полученную страницу и получает из неё:
Линии московского метро (получаете имя линии, номер линии, цвет парсить не надо).
Станции московского метро (получаете имя станции, номер линии).
3. Создаёт и записывает на диск JSON-файл со списком станций по линиям и списком линий по формату JSON-файла из проекта SPBMetro (файл map.json).
4. Читает файл и выводит в консоль количество станций на каждой линии.

5.Пропарсите и записывайте в JSON-файл переходы между станциями в дополнение к линиям и станциям (коллекции имя станции, номер линии, между которым есть переходы).
6.Выведите в консоль количество переходов в метро.
 */
