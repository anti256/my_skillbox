public class Station

{
    private String lineIndex;//индекс линии
    private String name;//название станции

    public Station(String lineIndex, String name)//конструктор
    {
        this.name = name;
        this.lineIndex = lineIndex;
    }

    public String getlineIndex(){return lineIndex;}

    public String getName(){return name;}

}