package OnlineShoppingSystem;

import java.util.ArrayList;
import java.util.HashMap;;

public class Customer extends Accounts {
    private String payment_details;
    private HashMap <String, Integer> cart = new HashMap<String, Integer>();

    public Customer(String name,String address,String email,String password, String payment_details) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.payment_details = payment_details;
    }
    

    private void buy_product(String[] product_id, int[] amount) {
        double total_price = 0;
        for (int products_index = 0; products_index < product_id.length; products_index++) {
            String current_product_id = product_id[products_index];
            int current_product_amount = amount[products_index];
            Products product = Common.all_products.get(current_product_id);
            if (product.getAvailability() == false) {
                System.out.printf("Product with id %s not available!", current_product_id);
                continue;
            }
            Order new_order = new Order(product, this, current_product_amount, "In Process");
            ArrayList<Order> orders = new ArrayList<Order>();
            if (Common.all_orders.containsKey(email)) {
                orders = Common.all_orders.get(email);
            }
            orders.add(new_order);
            Common.all_orders.put(email, orders);
            total_price += product.getPrice() * current_product_amount;
            product.increaseSales(current_product_amount);
        }
        System.out.printf("%d products has been added to orders.\nYou have been charged Rs. %.2f!\n", 
                        product_id.length, total_price);
    }

    private void add_to_cart(String id, int amount) {
        cart.put(id, amount);
        System.out.printf("Product with ID %s and amount %d has been added to cart.\n", id, amount);
    }
    private void remove_from_cart(String id) {
        cart.remove(id);
        System.out.printf("Product with ID %s has been removed from cart.\n", id);
    }
    private void view_cart() {
        Common.clearScreen(10);
        int[] col_widths = {15, 27, 50, 15, 7, 20};
        String[] col = {"ID", "Name", "Description", "Price (Rs.)", "Amount", "Total Price (Rs.)"};
        Common.table_header(col, col_widths);
        for (String key: cart.keySet()) {
            Products product = Common.all_products.get(key);
            int amount = cart.get(key);
            System.out.printf("| %-15s | %-27s | %-50s | %-15.2f | %-7d | %-20.2f\n", key, product.getName(),
                product.getDescription(), product.getPrice(), amount, product.getPrice() * amount);
        }
        Common.table_line(col_widths);
        Common.clearScreen(2);
    }
    

    private void change_payment_information() {
        this.payment_details = Common.input_with_prompt("Previous Payment Details: " + payment_details + "\nEnter new Payment Details: ");
        System.out.println("Payment info changed successfully!");
    }
    private void show_information() {
        System.out.printf("Name: %s\nAddress: %s\nEmail: %s\nPayment Details: %s\n", name, address, email, payment_details);
    }
    @Override
    public void show_menu() {
        String[] option_main_level = {"View Products", "View Cart", "View Orders", "Change Information", 
            "Change Payment Information", "Show Account Details", "Logout"};
        String[] option_products_level = {"Buy Product", "Add to cart", "Back"};
        String[] option_carts_level = {"Buy All Products", "Remove from cart", "Back"};
        String[][] all_levels = {option_main_level, option_products_level, option_carts_level};
        int current_level = 0;
        while (logged_in) {
            Common.clearScreen(10);
            switch (current_level) {
                case 0: System.out.printf("Hello %s\n", name); break;
                case 1: view_products(); break;
                case 2: view_cart(); break;
            }
            String option_choosed = Common.menu_handler(all_levels[current_level]);
            switch (option_choosed) {
                case "View Products": current_level = 1; continue;
                case "View Cart": current_level = 2; continue;
                case "View Orders": view_orders(this.email, true); break;
                case "Change Information": change_information(); break;
                case "Change Payment Information": change_payment_information(); break;
                case "Show Account Details": show_information(); break;
                case "Logout": logout(); continue;
                case "Buy Product", "Add to cart":
                    String product_id_entered = Common.input_with_prompt("Enter id of product => ");                
                    if (!Common.all_products.containsKey(product_id_entered)) {
                        System.out.println("Wrong Product Id entered!");
                    }else {
                        int amount = Common.input_with_prompt("Enter amount => ", 0);
                        if (option_choosed == "Buy Product") {
                            String[] product_ids = {product_id_entered};
                            int[] amounts = {amount};
                            buy_product(product_ids, amounts);
                        }else {
                            add_to_cart(product_id_entered, amount);
                        }
                    }
                    break;
                case "Buy All Products":
                    String[] product_ids = new String[cart.size()];
                    int[] amounts = new int[cart.size()];
                    int index = 0;
                    for (String key: cart.keySet()) {
                        product_ids[index] = key;
                        amounts[index] = cart.get(key);
                        index++;                    
                    }
                    buy_product(product_ids, amounts);
                    cart.clear();
                    break;
                case  "Remove from cart":
                    String product_id_from_cart = Common.input_with_prompt("Enter id of product => ");  
                    if (!cart.containsKey(product_id_from_cart)) {
                        System.out.println("Wrong Product Id entered!");
                    }else {
                        remove_from_cart(product_id_from_cart);
                    }
                    break;
                case "Back": current_level = 0; continue;
                default: System.out.println("Wrong Choice!");
            }
            Common.input_with_prompt("Press Enter to continue!");
        }
    }
}
