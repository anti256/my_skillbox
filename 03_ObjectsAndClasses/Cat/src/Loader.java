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

        private static Cat copy(Cat original){
        Cat koshka = new Cat();                             //создание кошки
        koshka.setWeight(original.getWeight());            //копирование веса
        koshka.setColor(original.getColor());              //копирование цвета
        koshka.setIsAlive(original.getIsAlive());          //копирование признака жизни
        koshka.setOriginWeight();
        return koshka;
    }

    public static void main(String[] args) {
        //new Cat();
        //Cat kuzja;
        Cat kitty = new Cat(); //создаем кошку kitty по обычному конструктору
        Cat murka;             //создаем "пустую" кошку murka
        Cat vasja;             //создаем "пустую" кошку vasja
        Cat sosiska;           //создаем "пустую" кошку sosiska

        System.out.println("Вес Китти - " + kitty.getWeight());

        murka = getKitten();   //делаем Мурку "нормальной" кошкой с весом 1100
        System.out.println("Вес Мурки - " + murka.getWeight());

        //проверка конструктора
        Cat kuzja = new Cat(murka); //создаем Кузю через конструктор с использованием свойств Мурки
        System.out.println("\nПроверка\nВес Кузи - " + kuzja.getWeight());

        //проверка копирования
        vasja = copy(murka);  //у "пустого" Васьки создаем свойства - копии свойств Мурки
        System.out.println("\nПроверка\nВес Васи - " +  vasja.getWeight());
        System.out.println("originalWeight - " + vasja.getOriginWeight());

        //проверка копирования
        sosiska = copy(kitty); //у "пустой" Сосиски создаем свойства - копии свойств Китти
        System.out.println("\nПроверка\nВес Сосиски - " + sosiska.getWeight());
        System.out.println("originalWeight - " + sosiska.getOriginWeight());

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
