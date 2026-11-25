/*
OOP ：
1.  (Encapsulation)：use private attribute + public getter/setter（MenuItem, OrderItem）
2.  (Inheritance)：Customer extend Person
3.  (Abstraction)：Person Abstract，must use getDescription() method 
4.  (Polymorphism)：Reference through parent class（Person）Calling the subclass's implementation（Customer）
5.  (Composition)：Order hold Customer and List<OrderItem>
*/

import java.util.ArrayList;  // use for create storage space
import java.util.InputMismatchException;  // use for preventing system crashes when input is incorrect
import java.util.List;   // interface oriented
import java.util.Scanner;  // use for customer input

// MenuItem class
//Used to indicate the name of a dish 
class MenuItem {
    private String name;   // private encapsulation
    private double price;  // private encapsulation

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() { return name; }  // return name
    public double getPrice() { return price; }  // return price
}

// OrderItem class
// Indicates the custermer's order items and quantity
class OrderItem {
    private MenuItem item; // encapsulation
    private int quantity;  // encapsulation

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    public MenuItem getItem() { return item; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotalPrice() { return item.getPrice() * quantity; }
}

// Person class (Abstraction)
// Use for indicate guest status
abstract class Person {
    protected String name;
    public Person(String name) { this.name = name; }
    // Method Adstraction，Not implemented，Let the subclass implement
    public abstract String getDescription();
}

// Customer class (Inheritance + Abstraction Implementation)
class Customer extends Person {
    private String customerId;
    public Customer(String name, String customerId) {  // Constructor
        super(name);  // call parent class
        this.customerId = customerId;
    }
    public String getCustomerId() { return customerId; }
    @Override
    public String getDescription() {
        return "Customer: " + name + " (ID: " + customerId + ")";
    }
}

// Order class
// Indicates order
class Order {
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();  // store order items
    public Order(Customer customer) { this.customer = customer; }

    public void addItem(MenuItem item, int quantity) {
        items.add(new OrderItem(item, quantity));
    }
    public void editItem(int index, int newQuantity) {
        if (index >= 0 && index < items.size()) {
            items.get(index).setQuantity(newQuantity);
        }
    }
    public void deleteItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }
    public void viewItems() {
        System.out.println("\nCurrent Order:");
        if (items.isEmpty()) {
            System.out.println("Your order is currently empty.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);
            System.out.printf("%d. %s x%d - RM%.2f\n", //This is Print format
                i + 1,
                item.getItem().getName(),
                item.getQuantity(),
                item.getTotalPrice());
        }
    }
    public void printReceipt() {
        System.out.println("\n==== RECEIPT ====");
        System.out.println(customer.getDescription());
        double total = 0;
        for (OrderItem item : items) {
            System.out.printf("%s x%d = RM%.2f\n",
                item.getItem().getName(),
                item.getQuantity(),
                item.getTotalPrice());
            total += item.getTotalPrice();
        }
        System.out.printf("Total: RM%.2f\n", total);
    }
}

// OrderingSystem
//The entrance to thid program
public class OrderingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Food Menu 
        List<MenuItem> foodMenu = new ArrayList<>();
        foodMenu.add(new MenuItem("Burger", 5.99));
        foodMenu.add(new MenuItem("Fries", 2.99));
        foodMenu.add(new MenuItem("Pizza", 8.99));
        foodMenu.add(new MenuItem("Fried Chicken", 10.99));
        foodMenu.add(new MenuItem("Chicken sandwich",9.99 ));
        foodMenu.add(new MenuItem("Chicken nuggets",6.99 ));

        // Drinks Menu
        List<MenuItem> drinkMenu = new ArrayList<>();
        drinkMenu.add(new MenuItem("Soda", 1.50));
        drinkMenu.add(new MenuItem("Pepsi", 1.50));
        drinkMenu.add(new MenuItem("Water", 0.50));
        drinkMenu.add(new MenuItem("Fanta ", 1.50));
        drinkMenu.add(new MenuItem("Apple Juice", 1.50));
        drinkMenu .add(new MenuItem("Coffee", 1.00));

        // Customer info
        System.out.println("\n==== WELCOME ====");
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();

        Customer customer = new Customer(name, id);
        Order order = new Order(customer);

        // Choose food first or drink
        int firstChoice = 0;
        while (true) {
            System.out.println("\nWould you like to order:");
            System.out.println("1. Food first");
            System.out.println("2. Drinks first");
            System.out.print("Enter your choice (1 or 2): ");
            try {
                firstChoice = scanner.nextInt();
                if (firstChoice == 1 || firstChoice == 2) break;
                System.out.println("Invalid choice. Try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter 1 or 2.");
                scanner.next();
            }
        }

        if (firstChoice == 1) {
            handleMenuSelection(scanner, foodMenu, order, "Food Menu");
            handleMenuSelection(scanner, drinkMenu, order, "Drink Menu");
        } else {
            handleMenuSelection(scanner, drinkMenu, order, "Drink Menu");
            handleMenuSelection(scanner, foodMenu, order, "Food Menu");
        }

        // Last 1234 menu
        boolean editing = true;
        while (editing) {
            int choice = 0;
            boolean validInput = false;

            while (!validInput) {
                System.out.println("\nWould you like to:");
                System.out.println("1. View current order");
                System.out.println("2. Edit item");
                System.out.println("3. Delete item");
                System.out.println("4. Print receipt and exit");
                System.out.print("Enter choice (1-4): ");
                try {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 4) {
                        validInput = true;
                    } else {
                        System.out.println("Please enter a number between 1 and 4.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    scanner.next();
                }
            }

            switch (choice) {
                case 1:
                    order.viewItems();
                    scanner.nextLine(); // clear buffer
                    System.out.print("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case 2:
                    order.viewItems();
                    System.out.print("Enter item number to edit: ");
                    int editIndex = scanner.nextInt() - 1;
                    System.out.print("Enter new quantity: ");
                    int newQty = scanner.nextInt();
                    order.editItem(editIndex, newQty);
                    break;
                case 3:
                    order.viewItems();
                    System.out.print("Enter item number to delete: ");
                    int deleteIndex = scanner.nextInt() - 1;
                    order.deleteItem(deleteIndex);
                    break;
                case 4:
                    editing = false;
                    break;
            }
        }

        order.printReceipt();
        scanner.close();
    }

    private static void handleMenuSelection(Scanner scanner, List<MenuItem> menu, Order order, String title) {
        boolean ordering = true;
        while (ordering) {
            System.out.println("\n----- " + title + " -----");
            for (int i = 0; i < menu.size(); i++) {
                MenuItem item = menu.get(i);
                System.out.printf("%d. %s - RM%.2f\n", i + 1, item.getName(), item.getPrice());
            }
            System.out.print("Select item number (or 0 to skip this menu): ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                continue;
            }
            if (choice == 0) {
                ordering = false;
            } else if (choice > 0 && choice <= menu.size()) {
                System.out.print("Enter quantity: ");
                int qty;
                try {
                    qty = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid quantity.");
                    scanner.next();
                    continue;
                }
                order.addItem(menu.get(choice - 1), qty);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}

