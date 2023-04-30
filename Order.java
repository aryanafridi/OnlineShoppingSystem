package OnlineShoppingSystem;

public class Order {
    private Products product;
    private Customer user;
    private int amount;
    private String status;

    public Order(Products product, Customer user, int amount, String status) {
        this.product = product;
        this.user = user;
        this.amount = amount;
        this.status = status;
    }

    public Products getProduct() {
        return product;
    }
    public Customer getUser() {
        return user;
    }
    public String getStatus() {
        return status;
    }
    public int getAmount() {
        return amount;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
