package toyShop.Core;

public class Toy {
    private int id;
    private String name;

    private int amount;
    private int frequency;

    public Toy(int id, String name, int amount, int frequency) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "id = " + id +
                ", name = " + name +
                ", amount = " + amount +
                ", frequency = " + frequency;
    }
}
