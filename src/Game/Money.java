package Game;

public class Money {
    private int money;
    private int silver;

    public Money(int money, int silver) {
        this.money = money;
        this.silver = silver;
    }

    public void converter(double price) { //подаем золото/2 (unit price)
        if (price % 1 == 0) {
            this.money = this.money - (int) price;
        } else { //22 --11.5 = 10.5 = 10 gold 1 silver
            this.silver = this.silver + 1;
            this.money = this.money - (int) Math.ceil(price);
        }
    }

    public int getMoney() {
        return this.money;
    }

    public int getSilver() {
        return this.silver;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }
}
