import core.Line;
import core.Station;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

//Проверка класса RoadCalculator
public class RoadCalculatorTest extends TestCase {

  List<Station> route, route1, route2, route3,conect1, conect2;
  StationIndex stationIndex;
  RouteCalculator rt;

  Station station11;
  Station station21;
  Station station31;
  Station station41;
  Station station12;
  Station station22;
  Station station32;
  Station station42;
  Station station52;
  Station station13;
  Station station23;
  Station station33;
  Station station43;

  @Override
  protected void setUp() throws Exception {
    route = new ArrayList<>();//маршрут для вычисления времени в пути
    route1 = new ArrayList<>();//маршрут на одной линии
    route2 = new ArrayList<>();//маршрут с одной пересадкой
    route3 = new ArrayList<>();//маршрут с двумя пересадками
    conect1 = new ArrayList<>();//узел пересадки 1
    conect2 = new ArrayList<>();//узел пересадки 2
    stationIndex = new StationIndex();
    rt = new RouteCalculator(stationIndex);

    Line line1 = new Line(1,"первая");
    Line line2 = new Line(2,"вторая");
    Line line3 = new Line(3,"третья");
    stationIndex.addLine(line1);
    stationIndex.addLine(line2);
    stationIndex.addLine(line3);

    station11 = new Station("бронзовая",line1);
    station21 = new Station("золотая",line1);
    station31 = new Station("медная",line1);
    station41 = new Station("стальная",line1);
    station12 = new Station("зеленая",line2);
    station22 = new Station("красная",line2);
    station32 = new Station("желтая",line2);
    station42 = new Station("синяя",line2);
    station52 = new Station("фиолетовая",line2);
    station13 = new Station("яблоневая",line3);
    station23 = new Station("березовая",line3);
    station33 = new Station("ольховая",line3);
    station43 = new Station("грушевая",line3);
    stationIndex.addStation(station11);
    stationIndex.addStation(station21);
    stationIndex.addStation(station31);
    stationIndex.addStation(station41);
    stationIndex.addStation(station12);
    stationIndex.addStation(station22);
    stationIndex.addStation(station32);
    stationIndex.addStation(station42);
    stationIndex.addStation(station52);
    stationIndex.addStation(station13);
    stationIndex.addStation(station23);
    stationIndex.addStation(station33);
    stationIndex.addStation(station43);

    line1.addStation(station11);
    line1.addStation(station21);
    line1.addStation(station31);
    line1.addStation(station41);
    line2.addStation(station12);
    line2.addStation(station22);
    line2.addStation(station32);
    line2.addStation(station42);
    line2.addStation(station52);
    line3.addStation(station13);
    line3.addStation(station23);
    line3.addStation(station33);
    line3.addStation(station43);

    route.add(station11);//"бронзовая",line1
    route.add(station21);//"золотая",line1
    route.add(station31);//"медная",line1
    route.add(station41);//"стальная",line1

    route1.add(station12);//"зеленая",line2
    route1.add(station22);//"красная",line2
    route1.add(station32);//"желтая",line2
    route1.add(station42);//"синяя",line2

    route2.add(station11);//"бронзовая",line1
    route2.add(station21);//"золотая",line1
    route2.add(station31);//"медная",line1
    route2.add(station22);//"красная",line2
    route2.add(station32);//"желтая",line2

    route3.add(station21);//"золотая",line1
    route3.add(station31);//"медная",line1
    route3.add(station22);//"красная",line2
    route3.add(station32);//"желтая",line2
    route3.add(station42);//"синяя",line2
    route3.add(station33);//"ольховая",line3
    route3.add(station23);//"березовая",line3
    route3.add(station13);//"яблоневая",line3

    conect1.add(station31);
    conect1.add(station22);
    stationIndex.addConnection(conect1);
    conect2.add(station42);
    conect2.add(station33);
    stationIndex.addConnection(conect2);

  }

  //Проверка на вычисление времени поездки
  public void testCalculateDuration(){
    double actual = RouteCalculator.calculateDuration(route1);
    double expected = 7.5d;

    assertEquals(expected, actual);
  }

  //Проверка на вычисление времени поездки с двумя пересадками
  public void testCalculateDurationTwoConnection(){
    double actual = RouteCalculator.calculateDuration(route3);
    double expected = 19.5d;

    assertEquals(expected, actual);
  }

  //Проверка на построение маршрута на одной линии
  public void testGetRouteOnTheLine(){
    List<Station> actual = rt.getShortestRoute(station12,station42);
    List<Station> expected = route1;

    assertEquals(expected,actual);
  }

  //Проверка на построение маршрута с одной пересадкой
  public void testGetRouteWithOneConnection(){
    List<Station> actual = rt.getShortestRoute(station11,station32);
    List<Station> expected = route2;

    assertEquals(expected,actual);
  }

  //Проверка на построение маршрута с двумя пересадками
  public void testGetRouteWithTwoConnections(){
    List<Station> actual = rt.getShortestRoute(station21,station13);
    List<Station> expected = route3;

    assertEquals(expected,actual);
  }



}
