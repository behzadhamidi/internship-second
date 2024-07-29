INSERT INTO users (username, email, password, fullName, phoneNumber, profilePicture)
VALUES ('behzad1', 'behzadhamidi@gmail.com', '3549Bb', 'behzad hamidi yamchi', '9143942289', 'behzad.jpg');
INSERT INTO users (username, email, password, fullName, phoneNumber, profilePicture)
VALUES ('amir', 'amirazizkhani@gmail.com', '1368', 'amir azizkhani ', '9121234567', 'behzad.jpg');

SELECT * FROM users;

SELECT * FROM users WHERE userId = 1;

SELECT * FROM users WHERE username = 'amir';

DELETE FROM users WHERE userId = 1;


DELETE FROM users WHERE username = 'amir';

UPDATE users
SET fullName = 'behzad hamidi', phoneNumber = '9228224373', profilePicture = 'profile1.jpg'
WHERE userId = 1;

UPDATE users
SET password = '3549b'
WHERE userId = '2';

CREATE TABLE  products (
                                        productId INT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        description TEXT,
                                        price DECIMAL(20, 2) NOT NULL,
                                        quantity INT NOT NULL,
                                        category VARCHAR(255),
                                        createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO products (name, description, price, quantity, category)
VALUES
    ('Laptop', 'High-performance laptop with 16GB RAM and 512GB SSD.', 1000, 10, 'Electronics'),
    ('Smartphone', 'Latest model smartphone with 5G connectivity.', 700, 25, 'Electronics'),
    ('Headphones', 'Noise-cancelling over-ear headphones.', 200, 50, 'Accessories'),
    ('Office Chair', 'Ergonomic office chair with adjustable height.', 150, 15, 'Furniture'),
    ('Coffee Maker', 'Single-serve coffee maker with programmable timer.', 90, 30, 'Home Appliances');


SELECT * FROM products;


SELECT * FROM products WHERE productId = 1;

SELECT * FROM products WHERE category = 'Electronics';

UPDATE products
SET price = 15000, quantity = 12
WHERE productId = 1;

UPDATE products
SET category = 'Office Supplies'
WHERE name = 'Office Chair';


DELETE FROM products WHERE productId = 1;

DELETE FROM products WHERE name = 'Coffee Maker';


CREATE TABLE IF NOT EXISTS orders (
                                      orderId INT AUTO_INCREMENT PRIMARY KEY,
                                      userId INT NOT NULL,
                                      totalPrice DECIMAL(20, 2) NOT NULL,
                                      orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      FOREIGN KEY (userId) REFERENCES users(userId)
);

INSERT INTO orders (userId, totalPrice)
VALUES
    (1, 950),
    (2, 699.99);

SELECT * FROM orders;


SELECT * FROM orders WHERE orderId = 1;


SELECT * FROM orders WHERE userId = 1;


SELECT * FROM orders WHERE DATE(orderDate) = '2024-07-27';



UPDATE orders
SET totalPrice = 2600
WHERE orderId = 1;


UPDATE orders
SET userId = 2
WHERE orderId = 3;

DELETE FROM orders WHERE orderId = 1;

DELETE FROM orders WHERE userId = 2;

CREATE TABLE IF NOT EXISTS order_items (
                                           orderItemId INT AUTO_INCREMENT PRIMARY KEY,
                                           orderId INT NOT NULL,
                                           productId INT NOT NULL,
                                           quantity INT NOT NULL,
                                           price DECIMAL(20, 2) NOT NULL,
                                           createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                           updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                           FOREIGN KEY (orderId) REFERENCES orders(orderId),
                                           FOREIGN KEY (productId) REFERENCES products(productId)
);
INSERT INTO order_items (orderId, productId, quantity, price)
VALUES
    (4, 1, 2, 15000),
    (5, 2, 1, 699.99),
    (5, 3, 3, 199.99),
    (5, 1, 1, 15000),
    (4, 2, 2, 699.99);

SELECT * FROM order_items;

SELECT * FROM order_items WHERE orderItemId = 1;


SELECT * FROM order_items WHERE orderId = 1;


SELECT * FROM order_items WHERE productId = 101;

UPDATE order_items
SET quantity = 3, price = 299.99
WHERE orderItemId = 11;

UPDATE order_items
SET price = price * 1.1
WHERE orderId = 5;

DELETE FROM order_items WHERE orderItemId = 14;

DELETE FROM order_items WHERE orderId = 5;

CREATE TABLE IF NOT EXISTS payments (
                                        paymentId INT AUTO_INCREMENT PRIMARY KEY,
                                        orderId INT NOT NULL,
                                        userId INT NOT NULL,
                                        amount DECIMAL(20, 2) NOT NULL,
                                        paymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        paymentStatus ENUM('PENDING', 'COMPLETED', 'FAILED') NOT NULL,
                                        FOREIGN KEY (orderId) REFERENCES orders(orderId),
                                        FOREIGN KEY (userId) REFERENCES users(userId)
);
INSERT INTO payments (orderId, userId, amount, paymentStatus)
VALUES
    (4, 1, 15000, 'COMPLETED'),
    (5, 2, 89.99, 'PENDING'),
    (4, 1, 349.98, 'FAILED');

CREATE TABLE IF NOT EXISTS bank_cards (
                                          cardId INT AUTO_INCREMENT PRIMARY KEY,
                                          cardNumber VARCHAR(16) NOT NULL,
                                          cardHolderName VARCHAR(100) NOT NULL,
                                          expirationDate DATE NOT NULL,
                                          cvv VARCHAR(4) NOT NULL,
                                          userId INT NOT NULL,
                                          FOREIGN KEY (userId) REFERENCES users(userId)
);
INSERT INTO bank_cards (cardNumber, cardHolderName, expirationDate, cvv, userId)
VALUES
    ('6393461035495809', 'behzad hamidi', '2025-12-31', '123', 1),
    ('8765432187654321', 'amir azizkhani', '2024-11-30', '456', 2),
    ('1111222233334444', 'behzad hamidi', '2026-01-31', '789', 1);




SELECT * FROM bank_cards;

SELECT * FROM bank_cards WHERE cardId = 1;

SELECT * FROM bank_cards WHERE userId = 2;


SELECT * FROM bank_cards WHERE cardNumber = '6393461035495809';

UPDATE bank_cards
SET cardHolderName = 'behzad yamchi', expirationDate = '2025-11-30'
WHERE cardId = 1;


UPDATE bank_cards
SET cvv = '321'
WHERE userId = 2;


DELETE FROM bank_cards WHERE cardId = 1;


DELETE FROM bank_cards WHERE userId = 2;



