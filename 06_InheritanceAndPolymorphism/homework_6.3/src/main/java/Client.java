public abstract class Client {
    protected double amount = 0.0d;//сумма на счету protected, чтобы нельзя было изменить из других
    // классов, но можно было наследовать

    public double getAmount() {//геттер
        return amount;
    }

    //положить
    public abstract void put(double amountToPut);

    //снять
    public abstract void take(double amountToTake);

}
