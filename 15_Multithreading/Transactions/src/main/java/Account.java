public class Account {

    private long money;//количество денег на счету
    private String accNumber;//название счета
    private boolean blocked;//флаг блокировки счета

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
        this.blocked = false;
    }

    //флаг блокировки счета
    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked() {
        this.blocked = true;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }
}
