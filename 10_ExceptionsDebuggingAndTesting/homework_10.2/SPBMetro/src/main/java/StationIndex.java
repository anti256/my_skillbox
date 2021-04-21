import core.Line;
import core.Station;

import java.util.*;
import java.util.stream.Collectors;

public class StationIndex {
    private final Map<Integer, Line> number2line;//map - ключ это номер линии, значение это ссылка на объект-линию
    private final TreeSet<Station> stations;//TreeSet из объектов-станций
    private final Map<Station, TreeSet<Station>> connections;//map - ключ это станция пересадки,
    // значение - TreeSet с одним значением - ответной станцией в узле пересадки

    public StationIndex() {//конструктор
        number2line = new HashMap<>();
        stations = new TreeSet<>();
        connections = new TreeMap<>();
    }

    public void addStation(Station station) {//добавление в список TreeSet станции
        stations.add(station);
    }

    public void addLine(Line line) {
        number2line.put(line.getNumber(), line);
    }

    public void addConnection(List<Station> stations) {
        for (Station station : stations) {//проход по list'у станций
            if (!connections.containsKey(station)) {//если станции нет в map connections
                connections.put(station, new TreeSet<>());//в map connections добавляется пара объект-станция - новый TreeSet
            }
            //создается TreeSet connectedStations, которая является ссылкой на значение map connections по текущей в цикле станции
            TreeSet<Station> connectedStations = connections.get(station);
            connectedStations.addAll(stations.stream()//в TreeSet добавляется станции, не текущие в цикле перебора станций
                    .filter(s -> !s.equals(station)).collect(Collectors.toList()));
        }//конец цикла
    }

    public Line getLine(int number) {
        return number2line.get(number);
    }

    public Station getStation(String name) {//возвращает объект станция по имени
        for (Station station : stations) {
            if (station.getName().equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null;
    }

    public Station getStation(String name, int lineNumber) {//возвращает объект станция по имени и номеру линии
        Station query = new Station(name, getLine(lineNumber));
        Station station = stations.ceiling(query);
        return station.equals(query) ? station : null;
    }

    public Set<Station> getConnectedStations(Station station) {
        return connections.containsKey(station) ?//если станция является станцией пересадки
            //возвращается TreeSet из станций из узла станции-пересадки
                connections.get(station) : new TreeSet<>();//иначе - пустой TreeSet
    }
}
