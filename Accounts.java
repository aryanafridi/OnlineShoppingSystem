package OnlineShoppingSystem;

import java.util.ArrayList;

public class Accounts {
	protected String name;
	protected String address;
	protected String email;
	protected String password;
	protected boolean logged_in = false;

	public boolean authenticate(String password)
	{
		if(this.password.equals(password))
		{
			this.logged_in=true;
			System.out.println("Login Successful!");
		}
		else
		{
			System.out.println("Incorrect password1");
		}
		Common.input_with_prompt("Press Enter to continue!");
		return this.logged_in;
	}
	protected void change_information() {
		Common.clearScreen(10);
		System.out.println("What information do you want to change?");
		String[] options = {"Name", "Address", "Email", "Password"};
		String choice = Common.menu_handler(options);
		boolean updated = false;
		switch (choice) {
			case "Name":				
				this.name = Common.input_with_prompt("Previous Name: " + name + "\nEnter new Name: ");
				updated = true;
				break;
			case "Address":
				this.address = Common.input_with_prompt("Previous Address: " + address + "\nEnter new Address: ");
				updated = true;
				break;
			case "Email":
				this.email = Common.input_with_prompt("Previous Email: " + email + "\nEnter new Email: ");
				updated = true;
				break;
			case "Password":
				this.password = Common.input_with_prompt("Enter new password: ");
				updated = true;
				break;
			default:
				System.out.println("Wrong Choice!");
		}
		if (updated)
			System.out.println("Updated Successfully.");
	}
	protected void view_products() {
        Common.clearScreen(10);
        int[] col_widths = {15, 27, 50, 15, 10};
        String[] col = {"ID", "Name", "Description", "Price (Rs.)", "Available"};
        Common.table_header(col, col_widths);
        for (String key: Common.all_products.keySet()) {
            Products product = Common.all_products.get(key);
            System.out.printf("| %-15s | %-27s | %-50s | %-15.2f | %10s |\n", key, product.getName(), 
                product.getDescription(), product.getPrice(), (product.getAvailability()) ? "Yes": "No");
        }
        Common.table_line(col_widths);
        Common.clearScreen(2);
    }
	protected void view_orders(String email_req, boolean header) {
        ArrayList<Order> orders = Common.all_orders.get(email_req);
		int[] col_widths = {15, 27, 50, 15, 7, 20, 15};
		String[] col = {"ID", "Name", "Description", "Price (Rs.)", "Amount", "Total Price (Rs.)", "Status"};
        if (header)			
            Common.table_header(col, col_widths);
		for (int i = 0; i < orders.size(); i++) {
			Order order = orders.get(i);
			Products product = order.getProduct();
			System.out.printf("| %-15s | %-27s | %-50s | %-15.2f | %-7d | %-20.2f | %-15s |\n", 
				product.getProduct_id(), product.getName(), product.getDescription(), product.getPrice(), 
				order.getAmount(), product.getPrice() * order.getAmount(), order.getStatus());
		}
		if (header)
            Common.table_line(col_widths);
    }
	protected void logout() {
		this.logged_in=false;
		System.out.println("Logged out Successfully!");
	}
	public void show_menu() {}
}
