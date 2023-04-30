package OnlineShoppingSystem;

public class Sales_Statistics {
    private int total_sales;
    private double total_revenue;
    private double total_profit;

    Sales_Statistics() {
        total_sales = 0;
        total_revenue = 0;
        total_profit = 0;
    }

    private void calculate_all_totals() {
        total_sales = 0;
        total_revenue = 0;
        total_profit = 0;
        for (String key: Common.all_products.keySet()) {
            Products product = Common.all_products.get(key);
            total_sales += product.getSales();
            total_revenue += product.calculateRevenue();
            total_profit += product.calculateProfit();
        }
    }
    public void show_statistics() {
        calculate_all_totals();
        String[] col = {"ID", "Name", "Price (Rs.)", "Cost (Rs.)", "Sales", "Revenue (Rs.)", "Profit (Rs.)", "Profit (%)"};
        int[] col_widths = {15, 27, 15, 15, 10, 15, 15, 10};
        Common.table_header(col, col_widths);
        for (String key: Common.all_products.keySet()) {
            Products product = Common.all_products.get(key);
            System.out.printf("| %-15s | %-27s | %-15.2f | %-15.2f | %-10d | %-15.2f | %-15.2f | %-10.2f |\n", 
                product.getProduct_id(), product.getName(), product.getPrice(), product.getCost(), product.getSales(),
                product.calculateRevenue(), product.calculateProfit(), product.calculateProfit_percentage(total_profit));
        }
        Common.table_line(col_widths);
        System.out.printf("| %-15s | %-27s | %-15s | %-15s | %-10d | %-15.2f | %-15.2f | %-10s |\n", 
                "Total", "", "", "", total_sales, total_revenue, total_profit, "");
        Common.table_line(col_widths);
        Common.clearScreen(2);
    }
}
