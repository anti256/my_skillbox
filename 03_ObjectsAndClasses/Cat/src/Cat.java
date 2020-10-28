//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Cat {
    private double originWeight;
    private double weight;
    private double minWeight;
    private double maxWeight;
    private double eaten;
    private Color catColor;
    static int count = 0;
    public Boolean isAlive = true;
    static final int EYE_COUNT = 2;
    static final double MIN_WEIGHT = 1000.0D;
    static final double MAX_WEIGHT = 9000.0D;

    public Cat() {
        weight = 1500.0D + 3000.0D * Math.random();
        originWeight = weight;
        eaten = 0.0D;
        ++count;
    }

    public Cat(Double weight){
        this();
        this.weight = weight;
    }

    public void setColor (Color color){
        catColor = color;
    }

    public Color getColor(){
        return catColor;
    }

    public Double getEaten() {
        return eaten;
    }

    //установка признака мертвой кошки и уменьшение количества кошек в зависимости от веса кошки
    private void deadCat (){
        if (isWeightNotNormal()){//если вес кошки не в норме
        isAlive = false;         //кошка - мертвая
        --count;}
    }

    public void pee() {                  //пи-пи
        if (isAlive) {                   //если кошка жива
            weight -= 500.0D;        //уменьшаем её вес
            System.out.println("\nДело сделано!");
            deadCat();
        } else {                         //если мертва, выводим об этом надпись
            System.out.println("\nКошечка мертва и не может больше пи-пи\n");
        }
    }

    public void meow() {                 //мяукание
        if (isAlive) {                   //если кошка жива
            weight -= 50.0D;        //уменьшаем её вес
            System.out.println("Meow");  //выводим "Meow"
            deadCat();
        } else {                         //если мертва, выводим об этом надпись
            System.out.println("\nКошечка мертва и не может больше мяукать\n");
        }
    }

    public void feed(Double amount) {//кормежка
        if (isAlive) {              //если кошка жива
            weight += amount;  //увеличиваем её вес на величину корма
            eaten += amount;   //увеличиваем общее количество съеденного на величину корма
            deadCat();
        } else {                    //если мертва, выводим об этом надпись
            System.out.println("\nКошечка мертва и не может больше есть\n");
        }
    }

    public void drink(Double amount) {  //питье
        if (isAlive) {              //если кошка жива
            weight += amount;       //увеличиваем её вес на величину питья
            deadCat();
        } else {                    //если мертва, выводим об этом надпись
            System.out.println("\nКошечка мертва и не может больше пить\n");
        }
    }

    public Double getWeight() {
        return weight;
    }

    public static int getCount() {
        return count;
    }

    //проверка нормальности веса
    private boolean isWeightNotNormal() { return (weight < MIN_WEIGHT | weight > MAX_WEIGHT); }

    public String getStatus() {
        if (weight < MIN_WEIGHT) {//если вес меньше минимального
            return "Dead";                 //метод возвращает строку "Dead"
        } else if (weight > MAX_WEIGHT) {//иначе, если вес больше максимального
            return "Exploded";             //метод возвращает строку "Exploded"
        } else if (weight > originWeight) {//если вес в норме, но больше определенного
            return "Sleeping";             //метод возвращает строку "Sleeping"
        } else {                           //иначе
            return "Playing";              //метод возвращает строку "Playing"
        }
    }
    }

