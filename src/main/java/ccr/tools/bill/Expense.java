package ccr.tools.bill;

import java.time.LocalDate;

public class Expense {
    float amount;
    LocalDate date;
    String shop;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%f;", shop, date, amount);
    }
}
