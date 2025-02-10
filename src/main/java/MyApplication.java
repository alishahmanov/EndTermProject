import controllers.*;
import data.PostgresDB;
import models.*;
import repositories.*;
import services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PostgresDB db = new PostgresDB();

        ClientRepository clientRepo = new ClientRepository(db);
        SupplierRepository supplierRepo = new SupplierRepository(db);
        ShoesRepository shoesRepo = new ShoesRepository(db);
        OrderRepository orderRepo = new OrderRepository(db);

        ClientService clientService = new ClientService(clientRepo);
        SupplierService supplierService = new SupplierService(supplierRepo);
        ShoesService shoesService = new ShoesService(shoesRepo);
        OrderService orderService = new OrderService(orderRepo);

        ClientController clientController = new ClientController(clientService);
        SupplierController supplierController = new SupplierController(supplierService);
        ShoesController shoesController = new ShoesController(shoesService);
        OrderController orderController = new OrderController(orderService);

        while (true) {
            System.out.println("\n Welcome to the Sneakers Store!");
            System.out.println("1. Log in");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("\nChoose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> login(scanner, clientController, supplierController, shoesController, orderController);
                case 2 -> register(scanner, clientController, supplierController);
                case 0 -> {
                    System.out.println(" Goodbye!");
                    return;
                }
                default -> System.out.println(" Invalid choice. Try again.");
            }
        }
    }

    private static void login(Scanner scanner, ClientController clientController, SupplierController supplierController, ShoesController shoesController, OrderController orderController) {
        System.out.println("\n Log In");
        System.out.println("Are you a:");
        System.out.println("1. Client");
        System.out.println("2. Supplier");
        System.out.print("\nChoose an option: ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (roleChoice == 1) {
            try {
                Client client = clientController.getClientByEmail(email);
                if (client != null && client.getPassword().equals(password)) {
                    System.out.println(" Welcome back, " + client.getName() + "!");
                    clientMenu(scanner, client, shoesController, orderController);
                } else {
                    System.out.println(" Incorrect email or password.");
                }
            } catch (Exception e) {
                System.out.println(" Client not found.");
            }
        } else if (roleChoice == 2) {
            try {
                Supplier supplier = supplierController.getSupplierByEmail(email);
                if (supplier != null && supplier.getPassword().equals(password)) {
                    System.out.println(" Welcome back, " + supplier.getName() + "!");
                    supplierMenu(scanner, supplier, shoesController);
                } else {
                    System.out.println(" Incorrect email or password.");
                }
            } catch (Exception e) {
                System.out.println(" Supplier not found.");
            }
        } else {
            System.out.println(" Invalid choice.");
        }
    }

    private static void register(Scanner scanner, ClientController clientController, SupplierController supplierController) {
        System.out.println("\n Register");
        System.out.println("Register as a:");
        System.out.println("1. Client");
        System.out.println("2. Supplier");
        System.out.print("\nChoose an option: ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (roleChoice == 1) {
            System.out.print("Enter your gender (male/female): ");
            String genderInput = scanner.nextLine().toLowerCase();
            boolean gender = genderInput.equals("male");

            System.out.print("Enter your shoe size: ");
            int size = scanner.nextInt();
            System.out.print("Enter your amount of money: ");
            int amountOfMoney = scanner.nextInt();
            scanner.nextLine();

            System.out.println(clientController.addClient(name, email, password, gender, size, amountOfMoney));
        } else if (roleChoice == 2) {
            System.out.print("Enter the brand of shoes you supply: ");
            String brand = scanner.nextLine();
            System.out.print("Enter your country of origin: ");
            String country = scanner.nextLine();
            System.out.print("Enter your delivery cost: ");
            int deliveryCost = scanner.nextInt();
            scanner.nextLine();

            System.out.println(supplierController.addSupplier(brand, country, name, email, deliveryCost, password));
        } else {
            System.out.println(" Invalid choice.");
        }
    }

    private static void clientMenu(Scanner scanner, Client client, ShoesController shoesController, OrderController orderController) {
        while (true) {
            System.out.println("\n Client Menu");
            System.out.println("1. View all sneakers");
            System.out.println("2. Place an order");
            System.out.println("0. Log out");
            System.out.print("\nWhat would you like to do? ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> System.out.println(shoesController.getAllShoes());
                case 2 -> placeOrder(scanner, client, shoesController, orderController);
                case 0 -> {
                    System.out.println(" Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void placeOrder(Scanner scanner, Client client, ShoesController shoesController, OrderController orderController) {
        System.out.println("\n Place an Order");
        System.out.println("Available sneakers:");
        System.out.println(shoesController.getAllShoes());

        System.out.print("Enter the IDs of the sneakers you want to buy: ");
        String[] ids = scanner.nextLine().split(",");
        List<Shoes> shoesList = new ArrayList<>();

        for (String id : ids) {
            try {
                Shoes shoe = shoesController.getShoe(Integer.parseInt(id.trim()));
                if (shoe != null) {
                    shoesList.add(shoe);
                } else {
                    System.out.println(" Invalid shoe ID: " + id);
                }
            } catch (Exception e) {
                System.out.println(" Invalid shoe ID: " + id);
            }
        }

        System.out.println(orderController.createOrder(client, shoesList));
    }

    private static void supplierMenu(Scanner scanner, Supplier supplier, ShoesController shoesController) {
        while (true) {
            System.out.println("\nSupplier Menu");
            System.out.println("1. Add new sneakers");
            System.out.println("2. View all sneakers");
            System.out.println("0. Log out");
            System.out.print("\nWhat would you like to do? ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> System.out.println(" Functionality under development.");
                case 2 -> System.out.println(shoesController.getAllShoes());
                case 0 -> {
                    System.out.println(" Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
