package core;

import java.util.ArrayList;
import java.util.List;

public class Line implements Comparable<Line>
{
    private int number;//номер линии
    private String name;//название линии
    private List<Station> stations;//список станции

    public Line(int number, String name)//конструктор
    {
        this.number = number;
        this.name = name;
        stations = new ArrayList<>();
    }

    public int getNumber()
    {
        return number;
    }

    public String getName()
    {
        return name;
    }

    public void addStation(Station station)
    {
        stations.add(station);
    }

    public List<Station> getStations()
    {
        return stations;
    }

    @Override
    public int compareTo(Line line)
    {
        return Integer.compare(number, line.getNumber());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Line) obj) == 0;
    }
}