import core.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RouteCalculator {
    private final StationIndex stationIndex;

    private static final double INTER_STATION_DURATION = 2.5;//время между станций
    private static final double INTER_CONNECTION_DURATION = 3.5;//время на станции-пересадке

    //конструктор
    public RouteCalculator(StationIndex stationIndex) {
        this.stationIndex = stationIndex;
    }

    //расчет кратчайшего маршрута
    public List<Station> getShortestRoute(Station from, Station to) {
        List<Station> route = getRouteOnTheLine(from, to);//попытка сформировать маршрут на одной линии
        if (route != null) {//если удалось, выходим из метода, возвращаем маршрут
          return route;
        }

        route = getRouteWithOneConnection(from, to);//попытка сформировать маршрут с одной пересадкой
        if (route != null) {//если удалось, выходим из метода, возвращаем маршрут
          if (!route.isEmpty()){
          return route;}
        }

        route = getRouteWithTwoConnections(from, to);//формирование маршрута с двумя пересадками
        return route;
    }

    //расчет времени маршрута
    public static double calculateDuration(List<Station> route) {
        double duration = 0;
        Station previousStation = null;
        for (int i = 0; i < route.size(); i++) {//перебор станций маршрута
            Station station = route.get(i);
            if (i > 0) {
                //текущая станция на одной линии с предыдущей
                duration += previousStation.getLine().equals(station.getLine()) ?
                        INTER_STATION_DURATION : INTER_CONNECTION_DURATION;
            }
            previousStation = station;
        }
        return duration;
    }

    //вычисление маршрута на одной линии
    private List<Station> getRouteOnTheLine(Station from, Station to) {
        if (!from.getLine().equals(to.getLine())) {//если станции не на одной линии, вернуть NULL
            return null;
        }
        List<Station> route = new ArrayList<>();
        List<Station> stations = from.getLine().getStations();//список станций на линии станции from
        int direction = 0;
        for (Station station : stations) {//перебор станций
            if (direction == 0) {
                if (station.equals(from)) {//если первой попалась станция from
                    direction = 1;
                } else if (station.equals(to)) {//если первой попалась станция to
                    direction = -1;
                }
            }
            //direction = 0 пока при переборе не попалась одна из станций - from или to

            //если при переборе встретилась from или to, к ArrayList route добавляется станция
            if (direction != 0) {
                route.add(station);
            }

            //если при переборе от from достигли to или от to достигли from, выходим из перебора
            if ((direction == 1 && station.equals(to)) ||
                    (direction == -1 && station.equals(from))) {
                break;
            }
        }//конец перебора станций
        if (direction == -1) {
            Collections.reverse(route);
        }//если перебор был от to к from, разворачиваем список route
        return route;
    }

    //вычисление маршрута с одной пересадкой
    private List<Station> getRouteWithOneConnection(Station from, Station to) {
        if (from.getLine().equals(to.getLine())) {//если станции на одной линии
            return null;//возвращаем null
        }

        List<Station> route = new ArrayList<>();

        List<Station> fromLineStations = from.getLine().getStations();//список станций с линии станции from
        List<Station> toLineStations = to.getLine().getStations();//список станций с линии станции to
        for (Station srcStation : fromLineStations) {//перебор станций линии станции from
            for (Station dstStation : toLineStations) {//перебор станций с линии станции to
                if (isConnected(srcStation, dstStation)) {//если обе текущие станции входят в один узел пересадки
                    ArrayList<Station> way = new ArrayList<>();
                    way.addAll(getRouteOnTheLine(from, srcStation));//список станций от from до станции пересадки на линии станции from
                    way.addAll(getRouteOnTheLine(dstStation, to));//в список добавляются станции от станции пресадки до станции to на линии станции to
                    //если конечный список станций пуст (первое совпадение)
                    //или предыдущий конечный список длиннее полученного при последней итерации
                    // цикла (на кольце два узла пересадки и следовательно два маршрута с одной пересадкой)
                    if (route.isEmpty() || route.size() > way.size()) {
                        route.clear();
                        route.addAll(way);
                    }
                }
            }
        }
        return route;
    }

    //проверка, что обе станции входят в один узел пересадки
    private boolean isConnected(Station station1, Station station2) {
        Set<Station> connected = stationIndex.getConnectedStations(station1);
        return connected.contains(station2);
    }

    //маршрут между станциями пересадки
    private List<Station> getRouteViaConnectedLine(Station from, Station to) {
        //станции из узла пересадки станции from
        Set<Station> fromConnected = stationIndex.getConnectedStations(from);
        //станции из узла пересадки станции to
        Set<Station> toConnected = stationIndex.getConnectedStations(to);
        //перебор всех станций пересадки, соответствующих from и to
        for (Station srcStation : fromConnected) {
            for (Station dstStation : toConnected) {
                if (srcStation.getLine().equals(dstStation.getLine())) {//если линии у станций пересадки одинаковые
                    return getRouteOnTheLine(srcStation, dstStation);//возвращается маршрут между этими станциями пересадки
                }
            }
        }
        return null;//иначе возвращается null
    }

    //вычисление маршрута с двумя пересадками
    private List<Station> getRouteWithTwoConnections(Station from, Station to) {
      if (from.getLine().equals(to.getLine())) {//если станции на одной линии
            return null;//возвращаем null
        }

        ArrayList<Station> route = new ArrayList<>();

        List<Station> fromLineStations = from.getLine().getStations();//список станций с линии станции from
        List<Station> toLineStations = to.getLine().getStations();//список станций с линии станции to

        for (Station srcStation : fromLineStations) {//перебор станций линии станции from
            for (Station dstStation : toLineStations) {//перебор станций линии станции to
                List<Station> connectedLineRoute =//попытка построить маршрут между станциями, подрузумевая, что они станции-пересадки
                        getRouteViaConnectedLine(srcStation, dstStation);
                if (connectedLineRoute == null) {
                    continue;//если не получилось, уходим на следующую итерацию цикла
                }
                List<Station> way = new ArrayList<>();//если получилось
                way.addAll(getRouteOnTheLine(from, srcStation));//маршрут от from до станции пересадки на линии станции from
                way.addAll(connectedLineRoute);//добавляем к маршруту маршрут между станциями пересадки
                way.addAll(getRouteOnTheLine(dstStation, to));//добавляем к маршруту маршрут от станции пересадки до to
                if (route.isEmpty() || route.size() > way.size()) {
                    route.clear();
                    route.addAll(way);
                }
            }
        }

        return route;
    }
}

/*Напишите тесты на все методы класса RouteCalculator в проекте SPBMetro.
С помощью тестов и отладки исправьте ошибку, которую вы найдёте в проекте SPBMetro в классе RouteCalculator.
Рекомендации

Сформируйте схему метро (StationIndex) в тестовом классе, в которой можно построить хотя бы один
маршрут с двумя пересадками. Делайте небольшую схему, чтобы легче ориентироваться в ней.
Чтобы протестировать приватные методы класса RouteCalculator, используйте различные аргументы метода
getShortestRoute() для вызова методов без пересадок, с одной и двумя пересадками.
Code Coverage — инструмент в IDEA, определяющий уровень покрытия тестами классов, методов и строк.
Чтобы запустить тесты с проверкой покрытия, надо нажать правой кнопкой мыши по классу директории с
тестами и выбрать Run All tests with Coverage. Желательно добиться 100%-ного покрытия методов
класса RouteCalculator.*/