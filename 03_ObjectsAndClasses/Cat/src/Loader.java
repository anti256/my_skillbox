//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Loader {
    public Loader() {
    }

    public static void main(String[] args) {
        new Cat();
        Cat kuzja = new Cat();
        new Cat();
        new Cat();
        new Cat();
        Cat sosiska = new Cat();
        System.out.println("Кошек " + Cat.getCount() + " шт.");
        System.out.println("\nПерекорм============================");
        System.out.println("Вес Кузи - " + kuzja.getWeight());
        System.out.println("Кузя - " + kuzja.getStatus());

        while(!kuzja.stat.equals("Exploded")) {
            kuzja.feed(1000.0D);
            System.out.println("Вес Кузи - " + kuzja.getWeight());
            System.out.println("Кузя - " + kuzja.getStatus());
            System.out.println(kuzja.getWeight());
        }

        System.out.println("Кошек " + Cat.getCount() + " шт.");
        System.out.println("\nПерекорм============================");
        System.out.println("Вес Сосиски - " + sosiska.getWeight());
        System.out.println("Сосиска - " + sosiska.getStatus());

        while(!sosiska.stat.equals("Exploded")) {
            sosiska.feed(1000.0D);
            System.out.println("Вес Сосиски - " + sosiska.getWeight());
            System.out.println("Сосиска - " + sosiska.getStatus());
            System.out.println(sosiska.getWeight());
        }

        System.out.println("Кошек " + Cat.getCount() + " шт.");
        sosiska.drink(100.0D);
        kuzja.pee();
        sosiska.feed(50.0D);
        kuzja.meow();
    }
}
