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

    public void pee() {
        if (this.weight < this.minWeight | this.weight > this.maxWeight) {
            System.out.println("\nКошечка мертва и не может больше пи-пи\n");
        } else {
            this.weight -= 50.0D;
            System.out.println("Дело сделано!\nТеперь его вес - " + this.weight + "\n");
        }

    }

    public void meow() {
        if (this.weight < this.minWeight | this.weight > this.maxWeight) {
            System.out.println("\nКошечка мертва и не может больше мяукать\n");
        } else {
            this.weight -= 50.0D;
            System.out.println("Meow");
        }

    }

    public void feed(Double amount) {
        if (this.weight < this.minWeight | this.weight > this.maxWeight) {
            System.out.println("\nКошечка мертва и не может больше есть\n");
        } else {
            this.weight += amount;
            this.eaten += amount;
        }

    }

    public void drink(Double amount) {
        if (this.weight < this.minWeight | this.weight > this.maxWeight) {
            System.out.println("\nКошечка мертва и не может больше пить\n");
        } else {
            this.weight += amount;
        }

    }

    public Double getWeight() {
        return this.weight;
    }

    public static int getCount() {
        return count;
    }

    public String getStatus() {
        if (this.weight < this.minWeight) {
            --count;
            this.stat = "Dead";
            return "Dead";
        } else if (this.weight > this.maxWeight) {
            --count;
            this.stat = "Exploded";
            return "Exploded";
        } else if (this.weight > this.originWeight) {
            this.stat = "Sleeping";
            return "Sleeping";
        } else {
            this.stat = "Playing";
            return "Playing";
        }
    }
}
