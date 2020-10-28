//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Loader {
    public Loader() {
    }

    private static Cat getKitten() {
        return new Cat(1100.0D);
    }

    private static void copyCat(Cat catIn, Cat catOut){//метод копирования свойств существующей кошки
       catIn.setWeight(catOut.getWeight());            //копирование веса
       catIn.setColor(catOut.getColor());              //копирование цвета
       catIn.isAlive = catOut.isAlive;                 //копирование признака жизни
       catIn.setOriginWeight();
    }

    public static void main(String[] args) {
        //new Cat();
        //Cat kuzja;
        //new Cat();
        Cat murka;
        Cat vasja = new Cat();
        //Cat sosiska = new Cat();

        murka = getKitten();
        System.out.println("Вес Мурки - "+murka.getWeight());

        //проверка конструктора
        Cat kuzja = new Cat(murka);
        System.out.println("Вес Кузи - "+kuzja.getWeight());

        //проверка copyCat
        System.out.println("Вес Васи - "+vasja.getWeight());
        System.out.println("originalWeight - "+ vasja.getOriginWeight());
        copyCat (vasja, murka);
        System.out.println("Вес Васи - "+vasja.getWeight());
        System.out.println("originalWeight - "+ vasja.getOriginWeight());

/*
        //перекорм Кузи
        System.out.println("Кошек " + Cat.getCount() + " шт.");
        System.out.println("\nПерекорм============================");
        System.out.println("Вес Кузи - " + kuzja.getWeight());
        System.out.println("Кузя - " + kuzja.getStatus());

        while(kuzja.isAlive) {                                     //пока кошка жива
            kuzja.feed(1000.0D);                            //вкармливаем кошке 1000 гр
                                                                    //в классе производится проверка по весу и
                                                                    //устанавливаются соотвествующие статусы
            System.out.println("Вес Кузи - " + kuzja.getWeight());  //выводим вес кошки
            System.out.println("Кузя - " + kuzja.getStatus());      //выводим статус кошки
                                                                    //если кошка уже мертва, выводится строковый статус
                                                                    //жизни, полученный при кормлении
        }


        //перекорм Сосиски
        System.out.println("Кошек " + Cat.getCount() + " шт.");
        System.out.println("\nПерекорм============================");
        System.out.println("Вес Сосиски - " + sosiska.getWeight());
        System.out.println("Сосиска - " + sosiska.getStatus());

        while(sosiska.isAlive) {
            sosiska.feed(1000.0D);
            System.out.println("Вес Сосиски - " + sosiska.getWeight());
            System.out.println("Сосиска - " + sosiska.getStatus());
        }


        //Пи-пи до смерти
        System.out.println("Кошек " + Cat.getCount() + " шт.");
        System.out.println("\nПи-пи до смерти============================");
        System.out.println("Вес Васи - " + vasja.getWeight());
        System.out.println("Вася - " + vasja.getStatus());

        while (vasja.isAlive){
            vasja.pee();
            System.out.println("Вес Васи - " + vasja.getWeight());
            System.out.println("Вася - " + vasja.getStatus());
        }

        //проверка методов на разные ситуации у мертвых кошек
        System.out.println("Кошек " + Cat.getCount() + " шт.");
        sosiska.drink(100.0D);
        kuzja.pee();
        sosiska.feed(50.0D);
        vasja.meow();

        //проверка getStatus на мертвой кошке
        System.out.println("Сосиска - " + sosiska.getStatus());
        System.out.println(sosiska.getWeight());
        System.out.println("Кошек " + Cat.getCount() + " шт.");*/

    }
}
