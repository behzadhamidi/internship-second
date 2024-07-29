package test;


import bankcard.*;
import order.*;
import orderitem.*;
import payment.*;
import product.*;
import user.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class test {
    private static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/behzad_db";
        String username = "behzad";
        String password = "3549Bb";
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = connect()) {
            
            UserController userController = new UserController(connection);
            BankCardController bankCardController = new BankCardController(connection);
            ProductController productController = new ProductController(connection);
            OrderController orderController = new OrderController(connection);
            OrderItemController orderItemController = new OrderItemController(connection);
            PaymentController paymentController = new PaymentController(connection);

            while (true) {
                System.out.println("Select an option:");
                System.out.println("1. Manage Users");
                System.out.println("2. Manage Bank Cards");
                System.out.println("3. Manage Products");
                System.out.println("4. Manage Orders");
                System.out.println("5. Manage Order Items");
                System.out.println("6. Manage Payments");
                System.out.println("7. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        System.out.println("User Management:");
                        System.out.println("1. Add User");
                        System.out.println("2. Update User");
                        System.out.println("3. Delete User");
                        System.out.println("4. Get User");
                        System.out.println("5. List Users");
                        int userChoice = scanner.nextInt();
                        scanner.nextLine(); 

                        switch (userChoice) {
                            case 1:
                                System.out.print("Enter user ID: ");
                                int userId = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter name: ");
                                String name = scanner.nextLine();
                                System.out.print("Enter email: ");
                                String email = scanner.nextLine();
                                System.out.print("Enter password: ");
                                String password = scanner.nextLine();
                                System.out.println("Enter username");
                                Date createdAt = new Date();
                                Date updatedAt = new Date();

                                User user = new User(userId, name, email, password, createdAt, updatedAt);
                                userController.addUser(user);
                                System.out.println("User added.");
                                break;

                            case 2:
                            
                                break;

                            case 3:
                                System.out.print("Enter user ID to delete: ");
                                int deleteUserId = scanner.nextInt();
                                userController.deleteUser(deleteUserId);
                                System.out.println("User deleted.");
                                break;

                            case 4:
                                System.out.print("Enter user ID to get: ");
                                int getUserId = scanner.nextInt();
                                User fetchedUser = userController.getUser(getUserId);
                                System.out.println(fetchedUser);
                                break;

                            case 5:
                                List<User> users = userController.getAllUsers();
                                for (User u : users) {
                                    System.out.println(u);
                                }
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }
                        break;

                    case 2:
                        System.out.println("Bank Card Management:");
                        System.out.println("1. Add Bank Card");
                        System.out.println("2. Update Bank Card");
                        System.out.println("3. Delete Bank Card");
                        System.out.println("4. Get Bank Card");
                        System.out.println("5. List Bank Cards");
                        int bankCardChoice = scanner.nextInt();
                        scanner.nextLine(); 

                        switch (bankCardChoice) {
                            case 1:
                                System.out.print("Enter card ID: ");
                                int cardId = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter user ID: ");
                                int userIdForCard = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter card number: ");
                                String cardNumber = scanner.nextLine();
                                System.out.print("Enter card holder: ");
                                String cardHolder = scanner.nextLine();
                                System.out.print("Enter expiration date (yyyy-mm-dd): ");
                                Date expirationDate = Date.from(Instant.parse(scanner.nextLine()));
                                System.out.print("Enter card type: ");
                                String cardType = scanner.nextLine();

                                BankCard bankCard = new BankCard(cardId, userIdForCard, cardNumber, cardHolder, expirationDate, cardType);
                                bankCardController.addCard(bankCard);
                                System.out.println("Bank card added.");
                                break;

                            case 2:
                               
                                break;

                            case 3:
                                System.out.print("Enter card ID to delete: ");
                                int deleteCardId = scanner.nextInt();
                                bankCardController.deleteCard(deleteCardId);
                                System.out.println("Bank card deleted.");
                                break;

                            case 4:
                                System.out.print("Enter card ID to get: ");
                                int getCardId = scanner.nextInt();
                                BankCard fetchedCard = bankCardController.getCard(getCardId);
                                System.out.println(fetchedCard);
                                break;

                            case 5:
                                List<BankCard> bankCards = bankCardController.getAllCards();
                                for (BankCard b : bankCards) {
                                    System.out.println(b);
                                }
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }
                        break;

                    case 3:
                        System.out.println("Product Management:");
                        System.out.println("1. Add Product");
                        System.out.println("2. Update Product");
                        System.out.println("3. Delete Product");
                        System.out.println("4. Get Product");
                        System.out.println("5. List Products");
                        int productChoice = scanner.nextInt();
                        scanner.nextLine(); 

                        switch (productChoice) {
                            case 1:
                                System.out.print("Enter product ID: ");
                                int productId = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter name: ");
                                String productName = scanner.nextLine();
                                System.out.print("Enter description: ");
                                String description = scanner.nextLine();
                                System.out.print("Enter price: ");
                                BigDecimal price = scanner.nextBigDecimal();
                                System.out.print("Enter quantity: ");
                                int quantity = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter category: ");
                                String category = scanner.nextLine();
                                Date productCreatedAt = new Date();
                                Date productUpdatedAt = new Date();

                                Product product = new Product(productId, productName, description, price, quantity, category, productCreatedAt, productUpdatedAt);
                                productController.addProduct(product);
                                System.out.println("Product added.");
                                break;

                            case 2:

                                break;

                            case 3:
                                System.out.print("Enter product ID to delete: ");
                                int deleteProductId = scanner.nextInt();
                                productController.deleteProduct(deleteProductId);
                                System.out.println("Product deleted.");
                                break;

                            case 4:
                                System.out.print("Enter product ID to get: ");
                                int getProductId = scanner.nextInt();
                                Product fetchedProduct = productController.getProduct(getProductId);
                                System.out.println(fetchedProduct);
                                break;

                            case 5:
                                List<Product> products = productController.getAllProducts();
                                for (Product p : products) {
                                    System.out.println(p);
                                }
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }
                        break;

                    case 4:
                        System.out.println("Order Management:");
                        System.out.println("1. Add Order");
                        System.out.println("2. Update Order");
                        System.out.println("3. Delete Order");
                        System.out.println("4. Get Order");
                        System.out.println("5. List Orders");
                        int orderChoice = scanner.nextInt();
                        scanner.nextLine(); 

                        switch (orderChoice) {
                            case 1:
                                System.out.print("Enter order ID: ");
                                int orderId = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter user ID: ");
                                int userIdForOrder = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter total price: ");
                                BigDecimal totalPrice = scanner.nextBigDecimal();
                                System.out.print("Enter order date (yyyy-mm-dd): ");
                                Date orderDate = Date.from(Instant.parse(scanner.nextLine()));

                                System.out.println("Enter order item details (orderItemId, productId, quantity, price) separated by space (type 'done' when finished):");
                                List<OrderItem> orderItems = new ArrayList<>();
                                while (true) {
                                    String line = scanner.nextLine();
                                    if (line.equalsIgnoreCase("done")) break;
                                    String[] parts = line.split(" ");
                                    int orderItemId = Integer.parseInt(parts[0]);
                                    int productIdForOrderItem = Integer.parseInt(parts[1]);
                                    int quantity = Integer.parseInt(parts[2]);
                                    BigDecimal priceForOrderItem = new BigDecimal(parts[3]);
                                    orderItems.add(new OrderItem(orderItemId, orderId, productIdForOrderItem, quantity, priceForOrderItem));
                                }

                                Order order = new Order(orderId, userIdForOrder, orderItems, totalPrice, orderDate);
                                orderController.addOrder(order);
                                System.out.println("Order added.");
                                break;

                            case 2:

                                break;

                            case 3:
                                System.out.print("Enter order ID to delete: ");
                                int deleteOrderId = scanner.nextInt();
                                orderController.deleteOrder(deleteOrderId);
                                System.out.println("Order deleted.");
                                break;

                            case 4:
                                System.out.print("Enter order ID to get: ");
                                int getOrderId = scanner.nextInt();
                                Order fetchedOrder = orderController.getOrder(getOrderId);
                                System.out.println(fetchedOrder);
                                break;

                            case 5:
                                List<Order> orders = orderController.getAllOrders();
                                for (Order o : orders) {
                                    System.out.println(o);
                                }
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }
                        break;

                    case 5:
                        System.out.println("Order Item Management:");
                        System.out.println("1. Add Order Item");
                        System.out.println("2. Update Order Item");
                        System.out.println("3. Delete Order Item");
                        System.out.println("4. Get Order Item");
                        System.out.println("5. List Order Items");
                        int orderItemChoice = scanner.nextInt();
                        scanner.nextLine(); 

                        switch (orderItemChoice) {
                            case 1:
                                System.out.print("Enter order item ID: ");
                                int orderItemId = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter order ID: ");
                                int orderIdForOrderItem = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter product ID: ");
                                int productIdForOrderItem = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter quantity: ");
                                int quantityForOrderItem = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter price: ");
                                BigDecimal priceForOrderItem = scanner.nextBigDecimal();

                                OrderItem orderItem = new OrderItem(orderItemId, orderIdForOrderItem, productIdForOrderItem, quantityForOrderItem, priceForOrderItem);
                                orderItemController.addOrderItem(orderItem);
                                System.out.println("Order item added.");
                                break;

                            case 2:

                                break;

                            case 3:
                                System.out.print("Enter order item ID to delete: ");
                                int deleteOrderItemId = scanner.nextInt();
                                orderItemController.deleteOrderItem(deleteOrderItemId);
                                System.out.println("Order item deleted.");
                                break;

                            case 4:
                                System.out.print("Enter order item ID to get: ");
                                int getOrderItemId = scanner.nextInt();
                                OrderItem fetchedOrderItem = orderItemController.getOrderItem(getOrderItemId);
                                System.out.println(fetchedOrderItem);
                                break;

                            case 5:
                                List<OrderItem> orderItemsList = orderItemController.getAllOrderItems();
                                for (OrderItem oi : orderItemsList) {
                                    System.out.println(oi);
                                }
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }
                        break;

                    case 6:
                        System.out.println("Payment Management:");
                        System.out.println("1. Add Payment");
                        System.out.println("2. Update Payment");
                        System.out.println("3. Delete Payment");
                        System.out.println("4. Get Payment");
                        System.out.println("5. List Payments");
                        int paymentChoice = scanner.nextInt();
                        scanner.nextLine(); 

                        switch (paymentChoice) {
                            case 1:
                                System.out.print("Enter payment ID: ");
                                int paymentId = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter order ID: ");
                                int orderIdForPayment = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter user ID: ");
                                int userIdForPayment = scanner.nextInt();
                                scanner.nextLine(); 
                                System.out.print("Enter amount: ");
                                BigDecimal amount = scanner.nextBigDecimal();
                                System.out.print("Enter payment date (yyyy-mm-dd): ");
                                Date paymentDate = Date.from(Instant.parse(scanner.nextLine()));
                                System.out.print("Enter payment status: ");
                                String paymentStatus = scanner.nextLine();

                                Payment payment = new Payment(paymentId, orderIdForPayment, userIdForPayment, amount, paymentDate, paymentStatus);
                                paymentController.addPayment(payment);
                                System.out.println("Payment added.");
                                break;

                            case 2:

                                break;

                            case 3:
                                System.out.print("Enter payment ID to delete: ");
                                int deletePaymentId = scanner.nextInt();
                                paymentController.deletePayment(deletePaymentId);
                                System.out.println("Payment deleted.");
                                break;

                            case 4:
                                System.out.print("Enter payment ID to get: ");
                                int getPaymentId = scanner.nextInt();
                                Payment fetchedPayment = paymentController.getPayment(getPaymentId);
                                System.out.println(fetchedPayment);
                                break;

                            case 5:
                                List<Payment> payments = paymentController.getAllPayments();
                                for (Payment p : payments) {
                                    System.out.println(p);
                                }
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }
                        break;

                    case 7:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

