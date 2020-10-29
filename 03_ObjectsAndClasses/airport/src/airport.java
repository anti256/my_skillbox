import com.skillbox.airport.Airport;

public class airport {


    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        int planeCount;

        System.out.println("Список самолетов - " + airport.getAllAircrafts());
        planeCount = airport.getAllAircrafts().size();
        System.out.println("Количество самолетов - " + planeCount);

    }
}