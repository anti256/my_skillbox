import core.*;
import core.Camera;

import java.util.Scanner;

public class RoadController
{
    private static double passengerCarMaxWeight = 3500.0; // kg
    private static int passengerCarMaxHeight = 2000; // mm
    private static int controllerMaxHeight = 4000; // mm

    private static int passengerCarPrice = 100; // RUB
    private static int cargoCarPrice = 250; // RUB
    private static int vehicleAdditionalPrice = 200; // RUB


    public void setPassengerCarMaxWeight (double weight){
        passengerCarMaxWeight = weight;
    }

    public double getPassengerCarMaxWeight (){
        return passengerCarMaxWeight;
    }

    public void setPassengerCarMaxHeight (int height){
        passengerCarMaxHeight = height;
    }

    public int getPassengerCarMaxHeight (){
        return passengerCarMaxHeight;
    }

    public void setControllerMaxHeight (int height){
        controllerMaxHeight = height;
    }

    public int getControllerMaxHeight (){
        return controllerMaxHeight;
    }

    public void setPassengerCarPrice(int prise){
        passengerCarPrice = prise;
    }

    public int getPassengerCarPrice(){
        return passengerCarPrice;
    }

    public void setCargoCarPrice (int prise){
        cargoCarPrice = prise;
    }

    public int getCargoCarPrice (){
        return cargoCarPrice;
    }

    public void setVehicleAdditionalPrice(int prise){
        vehicleAdditionalPrice = prise;
    }

    public int getVehicleAdditionalPrice(){
        return vehicleAdditionalPrice;
    }



    public static void main(String[] args)
    {
        System.out.println("Сколько автомобилей сгенерировать?");

        Scanner scanner = new Scanner(System.in);
        int carsCount = scanner.nextInt();

        for(int i = 0; i < carsCount; i++)
        {
            Car car = Camera.getNextCar();
            System.out.println(car);

            //Пропускаем автомобили спецтранспорта бесплатно
            if (car.isSpecial) {
                openWay();
                continue;
            }

            //Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
            int price = calculatePrice(car);
            if(price == -1) {
                continue;
            }

            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }
    }

    /**
     * Расчёт стоимости проезда исходя из массы и высоты
     */
    private static int calculatePrice(Car car)
    {
        int carHeight = car.height;
        int price = 0;
        if (carHeight > controllerMaxHeight)
        {
            blockWay("высота вашего ТС превышает высоту пропускного пункта!");
            return -1;
        }
        else if (carHeight > passengerCarMaxHeight)
        {
            double weight = car.weight;
            //Грузовой автомобиль
            if (weight > passengerCarMaxWeight)
            {
                price = passengerCarPrice;
                if (car.hasVehicle) {
                    price = price + vehicleAdditionalPrice;
                }
            }
            //Легковой автомобиль
            else {
                price = cargoCarPrice;
            }
        }
        else {
            price = passengerCarPrice;
        }
        return price;
    }

    /**
     * Открытие шлагбаума
     */
    private static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    /**
     * Сообщение о невозможности проезда
     */
    private static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}