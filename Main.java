/** 
 [Project]
 Online Shopping System 

 [Group Members]
 1. Muhammad Aryan Afridi
 2. Hamail Ali
 3. Muhammad Ahmed
 4. Muhammad Abubakr Cheema
 5. Abubakr Azam
 
 **/
package OnlineShoppingSystem;

public class Main {
    /**
      Dealing with login, sign up and main menu for startup.
    **/
    public void login() {
        // Login for admin and users
        // Default accounts exists for admin and user with these credentials respectively
        // Username ==> Password
        // admin    ==> admin
        // user     ==> user
        System.out.print("Enter email: ");
        String email_entered = Common.input.nextLine();
        System.out.print("Enter password: ");
        String password_entered = Common.input.nextLine();
        Accounts account_found = Common.all_accounts.get(email_entered);
        if (account_found == null) {
            System.out.println("Wrong Email entered! Account Not Found!");
        }else if (account_found.authenticate(password_entered)) {
            account_found.show_menu();
        }
    }
    public void sign_up() {
        // Sign up for new accounts.
        System.out.print("Enter Name: ");
        String name_entered = Common.input.nextLine();
        System.out.print("Enter Address: ");
        String address_entered = Common.input.nextLine();
        System.out.print("Enter email: ");
        String email_entered = Common.input.nextLine();
        System.out.print("Enter password: ");
        String password_entered = Common.input.nextLine();
        System.out.print("Enter Payment Details: ");
        String payment_entered = Common.input.nextLine();
        if (Common.all_accounts.keySet().contains(email_entered)) {
            System.out.println("Account with this email already exists!");
        }else {
            Customer new_customer = new Customer(name_entered, address_entered, email_entered, password_entered, payment_entered);
            Common.all_accounts.put(email_entered, new_customer);
            System.out.println("Account created successfully!");
        }
    }
    public void show_menu() {
        // Main Menu
        boolean quit = false;
        String options[] = {"Login", "Sign Up", "Quit"};
        while (!quit) {
            String choice = Common.menu_handler(options);
            switch (choice) {
                case "Login": login(); break;
                case "Sign Up": sign_up(); break;
                case "Quit":
                    System.out.println("Quitting..");
                    quit = true;
                    break;
                default: 
                    System.out.println("Wrong Choice entered!");
            }
            
        }
    }
    
    public static void main(String args[]) {
        Customer c = new Customer("Aryan Afridi", "Islamabad", "user", "user", "my_payments");
        Admin a = new Admin("Aryan Afridi", "Islamabad", "admin", "admin");
        Common.all_accounts.put(c.email, c);
        Common.all_accounts.put(a.email, a);
        Products pr1 = new Products("Redmi 9t", "re1", "Redmi 9t 6gb RAM, 128gb ROM", 30000, 25000, true);
        Products pr2 = new Products("Infinix Hot 8 Pro", "infinix1", "Infinix Hot 8", 20000, 17000, true);
        Products pr3 = new Products("Redmi Airdots 3", "re2", "Redmi Airdots bluetooth", 6000, 4000, true);
        Common.all_products.put("1", pr1);
        Common.all_products.put("2", pr2);
        Common.all_products.put("3", pr3);
        Main main = new Main();
        main.show_menu();
    }
}
