import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Flight.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
      ArrayList<Flight> flightList = new ArrayList<>();
      /* код до java8
      for (int i = 0; i < airport.getTerminals().size(); i++) {
        for (int j = 0; j < airport.getTerminals().get(i).getFlights().size(); j++) {
          if (airport.getTerminals().get(i).getFlights().get(j).getType().equals(Type.DEPARTURE)) {
           if ((airport.getTerminals().get(i).getFlights().get(j).getDate().getTime()-
               System.currentTimeMillis())/3600000 < 2){
           flightList.add(airport.getTerminals().get(i).getFlights().get(j));}
          }
        }
      }*/
        return airport.getTerminals().stream().flatMap(f -> f.getFlights().stream()).
                filter(p->p.getType().equals(Type.DEPARTURE)).
            filter(a->(a.getDate().getTime()-System.currentTimeMillis())/3600000 < 2).
            collect(Collectors.toList());
    }

}
//класс аэропорт
/*
class Airport
аэропорт имеет четыре терминала с названиями A, B, C и D
поле terminals типа List - метод getTerminals
на каждом терминале от 100 до 150 самолетов - у всех одинаковое число
класс аэропорт создает рейсы с временем - текущее в миллисекундах плюс-минус 24 часа

терминал имеет поля (вызываются через геттеры)
  private String name; - название
  private List<Flight> flights; - список рейсов
  private List<Aircraft> parkedAircrafts - список самолетов на приколе

каждый рейс Flight имеет поля (вызываются через геттеры)
  private String code;
  private Flight.Type type; - ENUM - ARRIVAL или DEPARTURE
  private Date date;
  private Aircraft aircraft;
имеет метод toString() {return HOUR_FORMAT.format(this.date) + " / " + this.code + " / " + this.type;}

каждый самолет aircraft имеет поле String model - геттер и метод toString
 */