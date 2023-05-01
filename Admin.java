package OnlineShoppingSystem;

import java.util.ArrayList;

public class Admin extends Accounts {
    Sales_Statistics sales_statistics = new Sales_Statistics();

    public Admin(String name,String address,String email,String password) {
        this.name=name;
        this.address=address;
        this.email=email;
        this.password=password;
    }
    private void show_information() {
        System.out.printf("Name: %s\nAddress: %s\nEmail: %s\nPayment Details: %s", name, address, email);
    }
    private void add_product() {
        String name = Common.input_with_prompt("Enter Product Name: ");
        String id = Common.input_with_prompt("Enter Product ID: ");
        String description = Common.input_with_prompt("Enter Description: ");
        double price = Common.input_with_prompt("Enter Price: ", 0.0);
        double cost = Common.input_with_prompt("Enter Cost: ", 0.0);
        boolean availability = Common.input_with_prompt("Enter Availabality [true: false]: ", true);
        Products new_product = new Products(name, id, description, price, cost, availability);
        if (Common.all_products.keySet().contains(id)) {
            System.out.println("Product with this id already exists.");
        }else {
            Common.all_products.put(id, new_product);
            System.out.printf("%s added successfully in products.\n", name);
        }
    }
    private void remove_product() {
        String id = Common.input_with_prompt("Enter Product ID to remove: ");
        if (Common.all_products.containsKey(id)) {
            Common.all_products.remove(id);
            System.out.printf("Product with ID %s successfully removed.\n", id);
        }else {
            System.out.println("No Product found with the given ID.");
        }
    }
    private boolean change_product_information() {
        String id = Common.input_with_prompt("Enter Product ID to change: ");
        if (Common.all_products.containsKey(id)) {
            Products product = Common.all_products.get(id);
            System.out.println("What information do you want to change?");
            String[] options = {"Name", "Product ID", "Description", "Price", "Cost", "Availability"};
            String choice = Common.menu_handler(options);
            switch(choice) {
                case "Name": product.setName(Common.input_with_prompt("Enter new Name: ")); break;
                case "Product ID": product.setProduct_id(Common.input_with_prompt("Enter new ID: ")); break;
                case "Description": product.setDescription(Common.input_with_prompt("Enter new Description: ")); break;
                case "Price": product.setPrice(Common.input_with_prompt("Enter new Price: ", 0.0)); break;
                case "Cost": product.setCost(Common.input_with_prompt("Enter new Cost: ", 0.0)); break;
                case "Availability": product.setAvailability(Common.input_with_prompt("Enter new Availabality [true: false]: ", true)); break;
                default: System.out.println("Invalid Option selected!"); return false;
            }
            Common.all_products.put(id, product);
            System.out.printf("%s changed successfully\n", product.getName());
            return true;
        }else {
            System.out.println("No Product found with the given ID.");
            return false;
        }
    }
    private void view_all_orders() {        
        for (String key: Common.all_orders.keySet()) {
            System.out.println(key);
            view_orders(key, false);
        }
    }
    private void change_order_status() {
        String email = Common.input_with_prompt("Enter email of customer whose orders status you want to change: \n => ");
        if (Common.all_orders.containsKey(email)) {
            ArrayList<Order> orders = Common.all_orders.get(email);
            view_orders(email, true);
            int order_index = Common.input_with_prompt("Enter order index from above list (From 1):  ", 0) - 1;
            orders.get(order_index).setStatus(Common.input_with_prompt("Previous status: " + 
                orders.get(order_index).getStatus() + "\nEnter order new status: ")); 
            System.out.println("Order status updated successfully!");
        }else {
            System.out.println("Wrong Email Entered!");
        }
    }
    private void view_accounts() {
        String[] columns = {"Name", "Email", "Address"};
        int[] width = {20, 30, 50};
        Common.table_header(columns, width);
        for (String key: Common.all_accounts.keySet()) {
            Accounts account = Common.all_accounts.get(key);
            System.out.printf("| %-20s | %-30s | %-50s |\n", account.name, account.email, account.address);
        }
        Common.table_line(width);
    }
    private void add_account() {
        int privelege = Common.input_with_prompt("Enter 1 For Admin Account and 2 For Customer Account: ", 0);
        if (privelege != 1 && privelege != 2) {
            String name = Common.input_with_prompt("Enter Name: ");
            String address = Common.input_with_prompt("Enter Address: ");
            String email = Common.input_with_prompt("Enter Email: ");
            String password = Common.input_with_prompt("Enter Password: ");
            if (Common.all_accounts.keySet().contains(email)) {
                System.out.println("Account with this email already exists.");
            }else {
                if (privelege == 2) {
                    String payment_details = Common.input_with_prompt("Enter Payment Details: ");
                    Common.all_accounts.put(email, new Customer(name, address, email, password, payment_details));
                }else {
                    Common.all_accounts.put(email, new Admin(name, address, email, password));
                }
                System.out.printf("Account for %s created successfully!\n", name);
            }
        }else {
            System.out.println("Wrong number entered!");
        }
    }
    private void remove_account() {
        String email = Common.input_with_prompt("Enter Email of account to remove: ");
        if (Common.all_accounts.containsKey(email)) {
            Common.all_accounts.remove(email);
            System.out.printf("Account with email %s successfully removed.\n", email);
        }else {
            System.out.println("No Account found with the given email.");
        }
    }
    @Override
    public void show_menu() {
        String[][] all_menu_options = {
            {"Manage Products", "Manage Orders", "Manage Accounts", "View Sales Statistics", "Change Information",
                "Show Account Details", "Logout"},
            {"View Products", "Add product", "Remove Product", "Change Product Information", "Back"},
            {"View Orders", "Change Order Status", "Back"},
            {"View Accounts", "Add Account", "Remove Account", "Back"}
        };
        int menu_level = 0;
        while (logged_in) {
            String choice = Common.menu_handler(all_menu_options[menu_level]);            
            if (choice == all_menu_options[0][0]) {menu_level = 1; continue;}
            else if (choice == all_menu_options[0][1]) {menu_level = 2; continue;}
            else if (choice == all_menu_options[0][2]) {menu_level = 3; continue;}
            else if (choice == all_menu_options[0][3]) {sales_statistics.show_statistics();}
            else if (choice == all_menu_options[0][4]) {change_information();}
            else if (choice == all_menu_options[0][5]) {show_information();}
            else if (choice == all_menu_options[0][6]) {logout(); continue;}
            else if (choice == all_menu_options[1][0]) {view_products();}
            else if (choice == all_menu_options[1][1]) {add_product();}
            else if (choice == all_menu_options[1][2]) {remove_product();}
            else if (choice == all_menu_options[1][3]) {change_product_information();}
            else if (choice == all_menu_options[2][0]) {view_all_orders();}
            else if (choice == all_menu_options[2][1]) {change_order_status();}
            else if (choice == all_menu_options[3][0]) {view_accounts();}
            else if (choice == all_menu_options[3][1]) {add_account();}
            else if (choice == all_menu_options[3][2]) {remove_account();}
            else if (choice == "Back") {menu_level = 0; continue;}
            else {System.out.println("Wrong Choice Entered!");}
            Common.input_with_prompt("Press Enter to Continue!");
        }
    }
}
