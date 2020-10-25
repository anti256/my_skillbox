//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Cat {
    private double originWeight;
    private double weight = 1500.0D + 3000.0D * Math.random();
    private double minWeight;
    private double maxWeight;
    private double eaten;
    public String stat;
    static int count = 0;
    public Boolean isAlive = true;

    public Cat() {
        this.originWeight = this.weight;
        this.minWeight = 1000.0D;
        this.maxWeight = 9000.0D;
        this.eaten = 0.0D;
        ++count;
    }

    public Double getEaten() {
        return this.eaten;
    }

    public void pee() {                  //пи-пи
        if (isAlive) {                   //если кошка жива
            this.weight -= 500.0D;        //уменьшаем её вес
            System.out.println("Дело сделано!\nТеперь его вес - " + this.weight + "\n");//вывадим об этом надпись
            this.getStatus();            //получаем статус кошки,также устанавливаем статус жизни
                                         //возвращаемую строку не используем, только устанавливаем статус жизни
        } else {                         //если мертва, выводим об этом надпись
            System.out.println("\nКошечка мертва и не может больше пи-пи\n");
        }
    }

    public void meow() {                 //мяукание
        if (isAlive) {                   //если кошка жива
            this.weight -= 50.0D;        //уменьшаем её вес
            System.out.println("Meow");  //выводим "Meow"
            this.getStatus();            //получаем статус кошки,также устанавливаем статус жизни
                                         //возвращаемую строку не используем, только устанавливаем статус жизни
        } else {                         //если мертва, выводим об этом надпись
            System.out.println("\nКошечка мертва и не может больше мяукать\n");
        }
    }

    public void feed(Double amount) {//кормежка
        if (isAlive) {              //если кошка жива
            this.weight += amount;  //увеличиваем её вес на величину корма
            this.eaten += amount;   //увеличиваем общее количество съеденного на величину корма
            this.getStatus();       //получаем статус кошки,также устанавливаем статус жизни
                                    //возвращаемую строку не используем, только устанавливаем статус жизни
        } else {                    //если мертва, выводим об этом надпись
            System.out.println("\nКошечка мертва и не может больше есть\n");
        }
    }

    public void drink(Double amount) {  //питье
        if (isAlive) {                  //если кошка жива
            this.weight += amount;      //увеличиваем её вес на величину питья, суммарный вес съеденного не увеличиваем
            this.getStatus();           //получаем статус кошки,также устанавливаем статус жизни
                                        //возвращаемую строку не используем, только устанавливаем статус жизни
        } else {//если мертва, выводим об этом надпись
            System.out.println("\nКошечка мертва и не может больше пить\n");
        }
    }

    public Double getWeight() {
        return this.weight;
    }

    public static int getCount() {
        return count;
    }

    public String getStatus() {
        if (isAlive) {//если кошка жива
            if (this.weight < this.minWeight) {//если вес меньше минимального
                --count;                       //уменьшаем количество кошек
                this.stat = "Dead";            //устанавливаем строковый статус жизни
                this.isAlive = false;          //устанавливаем буленовский статус жизни
                return "Dead";                 //метод возвращает строку "Dead"
            } else if (this.weight > this.maxWeight) {//иначе, если вес больше максимального
                --count;                       //уменьшаем количество кошек
                this.stat = "Exploded";        //устанавливаем строковый статус жизни
                this.isAlive = false;          //устанавливаем буленовский статус жизни
                return "Exploded";             //метод возвращает строку "Exploded"
            } else if (this.weight > this.originWeight) {//если вес в норме, но больше определенного
                this.stat = "Sleeping";        //устанавливаем строковый статус жизни
                                               //буленовский статус жизни оставляем прежним - isAlive=true
                return "Sleeping";             //метод возвращает строку "Sleeping"
            } else {                           //иначе
                this.stat = "Playing";         //устанавливаем строковый статус жизни
                                               //буленовский статус жизни оставляем прежним - isAlive=true
                return "Playing";              //метод возвращает строку "Playing"
            }
        }
        else return this.stat; //если кошка мертва, метод возвращает строку из предыдущего строкового статуса жизни
                               //который равен или "Dead" или "Exploded"
                               //буленовский статус жизни оставляем прежним - isAlive=false
    }
}
