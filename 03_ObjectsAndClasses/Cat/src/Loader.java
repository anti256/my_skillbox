
public class Loader
{
    public static void main(String[] args)
    {
        //Cat vasiliy = new Cat();
        //Cat kuzja = new Cat();
        //Cat pushok = new Cat();
        //Cat snegok = new Cat();
        Cat pendal = new Cat();
        //Cat sosiska = new Cat();

        /*System.out.println("Вес Василия - "+ vasiliy.getWeight());
        System.out.println("Вес Кузи - "+ kuzja.getWeight());
        System.out.println("Вес Пушка - "+ pushok.getWeight());
        System.out.println("Вес Снежка - "+ snegok.getWeight());
        System.out.println("Вес Сосиски - "+ sosiska.getWeight());*/
        System.out.println("Вес Пендаля - "+ pendal.getWeight()+"\n");

        pendal.feed(150.0);
        System.out.println("Пендаль съел "+pendal.getEaten()+" грамм");
        pendal.feed(150.0);
        System.out.println("Пендаль съел "+pendal.getEaten()+" грамм\n");
        pendal.pee();
        pendal.pee();
        pendal.pee();


        /*pendal.feed(100.0);
        sosiska.feed(200.0);
        System.out.println("\nВес Пендаля - "+ pendal.getWeight());
        System.out.println("Вес Сосиски - "+ sosiska.getWeight());*/

        /*System.out.println("\nПерекорм============================");
        System.out.println("Вес Кузи - "+ kuzja.getWeight());
        System.out.println("Кузя - "+ kuzja.getStatus());
        while (!kuzja.getStatus().equals("Exploded")){
            kuzja.feed(200.0);
            System.out.println("Вес Кузи - "+ kuzja.getWeight());
            System.out.println("Кузя - "+ kuzja.getStatus());
        }*/

        /*System.out.println("\nЗамяукивание============================");
        System.out.println("Вес Пушка - "+ pushok.getWeight());
        System.out.println("Пушок - "+ pushok.getStatus());
        while (!pushok.getStatus().equals("Dead")) {
            pushok.meow();
            System.out.println("Вес Пушка - " + pushok.getWeight());
            System.out.println("Пушок - " + pushok.getStatus());
        }*/
        //System.out.println(cat.getStatus());
    }
}