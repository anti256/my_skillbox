

public class ShowMetroReport {

  public static void show (LoadFromJson inputData){
    inputData.getLoadLinesJson().entrySet().forEach(line->{//перебор линий
      System.out.println("Линия " + line.getKey() + " " + line.getValue());
      System.out.println("\tКоличество станций - " + inputData.getLoadStationsJson().get(line.getKey()).size());
    });
  }

}