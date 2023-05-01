package OnlineShoppingSystem;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;;

public class Common {
    // Common data and methods for all programs
    // Eg data for all accounts, products and orders and Scanner variable for input
    // Methods such as taking input with message, Handling menu using list of options 
    // Creating table for data
    public static Scanner input = new Scanner(System.in);
    public static HashMap <String, Accounts> all_accounts = new HashMap<String, Accounts>();
    public static HashMap <String, Products> all_products = new HashMap<String, Products>();
    public static HashMap <String, ArrayList<Order>> all_orders = new HashMap<String, ArrayList<Order>>();

    public static String input_with_prompt(String message) {
        System.out.print(message);
        return input.nextLine();
    }
    public static int input_with_prompt(String message, int num) {
        System.out.print(message);
        return inputInteger();
    }
    public static double input_with_prompt(String message, double num) {
        System.out.print(message);
        return input.nextDouble();
    }
    public static boolean input_with_prompt(String message, boolean bool) {
        System.out.print(message);
        return input.nextBoolean();
    }
    public static int inputInteger() {
        int x = input.nextInt();
        input.nextLine();
        return x;
    }
    public static void clearScreen(int lines) {  
        for (int x = 0; x < lines; x++) 
            System.out.print("\n"); 
    }

    public static String menu_handler(String[] options) {
        for (int option_index = 1; option_index <= options.length; option_index++)
            System.out.printf("%d: %s\n", option_index, options[option_index - 1]);
        System.out.printf("Choose from above [1-%d] => ", options.length);
        int choosed_option_index = inputInteger() - 1;
        if (choosed_option_index >= 0 && choosed_option_index < options.length) {
            return options[choosed_option_index];
        }
        return "";
    }
    public static void table_header(String[] columns, int[] columns_width) {
        table_line(columns_width);
        System.out.print("| ");
        for (int index = 0; index < columns.length; index++) {
            System.out.printf("%-" + columns_width[index] + "s | ", columns[index]);
        }
        System.out.println();
        table_line(columns_width);
    }
    public static void table_line(int[] columns_width) {
        int total_length = 0;
        for (int width: columns_width)
            total_length += width;
        total_length += 3 * columns_width.length + 1;
        for (int x = 0; x < total_length; x++)
            System.out.print("-");
        System.out.println();
    }
}
