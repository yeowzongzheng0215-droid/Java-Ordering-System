🍽️ About The Project

Java Ordering System is a menu-driven restaurant console application developed as a 2-person Group Assignment to demonstrate core Object-Oriented Programming (OOP) principles in a real-world dining checkout scenario.

Customers can check in with their name and ID, choose whether to browse food or beverages first, customize quantities, review or modify their cart before checkout, and generate an itemized receipt formatted in Malaysian Ringgit (RM).

✨ Key Features

👤 Customer Check-In: Captures customer name and ID at startup using an abstract Person class hierarchy.

🔀 Flexible Ordering Flow: Allows guests to choose whether to order from the Food Menu or Drink Menu first, with an option to skip either menu (0).

🛒 Interactive Cart Management: Supports viewing current items, editing item quantities, and deleting items before final checkout.

🛡️ Crash-Proof Input Validation: Handles invalid non-numeric inputs gracefully via InputMismatchException without terminating the program.

🧾 Formatted RM Receipt: Automatically calculates item subtotals and the grand total, printing a clean receipt upon checkout.

📋 Restaurant Menu

🏗️ OOP Architecture

This project implements all 5 core Object-Oriented Programming concepts inside OrderingSystem.java:

OOP Concept

Implementation in Code

Purpose

🔒 Encapsulation

MenuItem, OrderItem (private fields + getters/setters)

Protects dish prices, names, and order quantities from unauthorized direct access.

🧬 Inheritance

Customer extends Person

Reuses base guest attributes (name) and extends them with customerId.

🧩 Abstraction

abstract class Person with abstract String getDescription()

Defines an abstract contract that all guest subclasses must implement.

🎭 Polymorphism

Customer overrides Person.getDescription()

Allows parent-type (Person) references to invoke subclass-specific descriptions.

🔗 Composition

Order holds a Customer and List<OrderItem>

Builds a complete order object from independent customer and line-item components.

📐 UML Class Diagram

classDiagram
    class Person {
        <<abstract>>
        #String name
        +Person(String name)
        +getDescription()* String
    }

    class Customer {
        -String customerId
        +Customer(String name, String customerId)
        +getCustomerId() String
        +getDescription() String
    }

    class MenuItem {
        -String name
        -double price
        +MenuItem(String name, double price)
        +getName() String
        +getPrice() double
    }

    class OrderItem {
        -MenuItem item
        -int quantity
        +OrderItem(MenuItem item, int quantity)
        +getItem() MenuItem
        +getQuantity() int
        +setQuantity(int quantity) void
        +getTotalPrice() double
    }

    class Order {
        -Customer customer
        -List~OrderItem~ items
        +Order(Customer customer)
        +addItem(MenuItem item, int quantity) void
        +editItem(int index, int newQuantity) void
        +deleteItem(int index) void
        +viewItems() void
        +printReceipt() void
    }

    class OrderingSystem {
        +main(String[] args)$ void
        -handleMenuSelection(Scanner, List~MenuItem~, Order, String)$ void
    }

    Person <|-- Customer : Inheritance
    Order *-- Customer : Composition
    Order *-- OrderItem : Composition
    OrderItem o-- MenuItem : Aggregation
    OrderingSystem ..> Order : Uses
    OrderingSystem ..> MenuItem : Uses


🚀 Getting Started

Prerequisites

Java Development Kit (JDK) 8 or higher installed on your machine.

Installation & Execution

Clone the repository

git clone https://github.com/yeowzongzheng0215-droid/Java-Ordering-System.git
cd Java-Ordering-System


Compile the Java source file

javac OrderingSystem.java


Run the application

java OrderingSystem


🖥️ Console Preview

==== WELCOME ====
Enter customer name: Alex
Enter customer ID: C001

Would you like to order:
1. Food first
2. Drinks first
Enter your choice (1 or 2): 1

----- Food Menu -----
1. Burger - RM5.99
2. Fries - RM2.99
3. Pizza - RM8.99
4. Fried Chicken - RM10.99
5. Chicken sandwich - RM9.99
6. Chicken nuggets - RM6.99
Select item number (or 0 to skip this menu): 1
Enter quantity: 2
Select item number (or 0 to skip this menu): 0

----- Drink Menu -----
1. Soda - RM1.50
2. Pepsi - RM1.50
3. Water - RM0.50
4. Fanta  - RM1.50
5. Apple Juice - RM1.50
6. Coffee - RM1.00
Select item number (or 0 to skip this menu): 6
Enter quantity: 1
Select item number (or 0 to skip this menu): 0

Would you like to:
1. View current order
2. Edit item
3. Delete item
4. Print receipt and exit
Enter choice (1-4): 4

==== RECEIPT ====
Customer: Alex (ID: C001)
Burger x2 = RM11.98
Coffee x1 = RM1.00
Total: RM12.98


👥 Team & Contributions (50% / 50%)

This project was developed collaboratively as a 2-person group assignment, with the overall design, implementation, and testing divided equally (50% / 50%) between both members:
