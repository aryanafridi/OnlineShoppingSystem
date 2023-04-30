package OnlineShoppingSystem;

public class Products {
    private String name;
    private String product_id;
    private String description;
    private double price;
    private double cost;
    private int sales;
    private boolean availability;
    private double revenue_generated;
    private double profit;
    private double profit_percentage;

    public Products(String name, String id, String description, double price, double cost, boolean availability) {
        this.name = name;
        this.product_id = id;
        this.description = description;
        this.price = price;
        this.cost = cost;
        this.availability = availability;
        sales = 0;
        profit_percentage = 0;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    public void increaseSales(int sales) {
        this.sales += sales;
    }
    public double calculateRevenue() {
        revenue_generated = this.sales * this.price;
        return revenue_generated;
    }
    public double calculateProfit() {
        profit = (this.sales * (this.price - this.cost));
        return profit;
    }
    public double calculateProfit_percentage(double total_profit) {
        this.profit_percentage = (profit / total_profit) * 100;
        return this.profit_percentage;
    }
    public String getName() {
        return name;
    }
    public String getProduct_id() {
        return product_id;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public double getCost() {
        return cost;
    }
    public int getSales() {
        return sales;
    }
    public boolean getAvailability() {
        return availability;
    }
}
