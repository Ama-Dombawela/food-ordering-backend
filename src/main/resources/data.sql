-- Sample data for Food Ordering System
-- Loaded automatically by Spring Boot on first run

-- Users (admin and customers)
INSERT INTO `user` (`id`, `name`, `email`, `password`, `role`) VALUES
(1, 'customer@gmail.com', 'customer@gmail.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'CUSTOMER'),
(4, 'Admin', 'admin@gmail.com', '$2a$10$0SGurP03modhdy62DxjON.PtY1hgDCBot3DDHYnzNmLATHmLMsh0e', 'ADMIN'),
(7, 'James Silva', 'james@gmail.com', '$2a$10$toRCdAnIlv77/QuGcWRlXOHbWqEBRO.c6BYDuE7Q2oDNqtc2NnlpK', 'CUSTOMER'),
(8, 'Sara Perera', 'sara@gmail.com', '$2a$10$toRCdAnIlv77/QuGcWRlXOHbWqEBRO.c6BYDuE7Q2oDNqtc2NnlpK', 'CUSTOMER'),
(9, 'Tom Bandara', 'tom@gmail.com', '$2a$10$toRCdAnIlv77/QuGcWRlXOHbWqEBRO.c6BYDuE7Q2oDNqtc2NnlpK', 'CUSTOMER');

-- Categories
INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Pizza'),
(2, 'Burgers'),
(3, 'Pasta'),
(4, 'Salads'),
(5, 'Desserts'),
(6, 'Drinks'),
(7, 'Sandwiches'),
(8, 'Sushi');

-- Food Items
INSERT INTO `food_item` (`id`, `name`, `description`, `price`, `status`, `category_id`) VALUES
(1, 'Margherita', 'Classic pizza', 12.99, 'AVAILABLE', 1),
(2, 'Pepperoni Pizza', 'Loaded with spicy pepperoni and mozzarella', 14.99, 'AVAILABLE', 1),
(3, 'BBQ Chicken Pizza', 'Smoky BBQ sauce with grilled chicken', 15.99, 'AVAILABLE', 1),
(4, 'Veggie Supreme', 'Fresh garden vegetables on a crispy base', 13.49, 'AVAILABLE', 1),
(5, 'Four Cheese Pizza', 'Mozzarella, cheddar, parmesan and gouda', 16.99, 'OUT_OF_STOCK', 1),
(6, 'Classic Cheeseburger', 'Beef patty with cheddar and fresh veggies', 9.99, 'AVAILABLE', 2),
(7, 'Double Smash Burger', 'Double smashed beef patty with special sauce', 13.99, 'AVAILABLE', 2),
(8, 'Crispy Chicken Burger', 'Golden fried chicken with coleslaw', 11.49, 'AVAILABLE', 2),
(9, 'Mushroom Swiss Burger', 'Beef patty with sauteed mushrooms and Swiss cheese', 12.49, 'AVAILABLE', 2),
(10, 'Spaghetti Carbonara', 'Creamy egg sauce with pancetta and parmesan', 13.99, 'AVAILABLE', 3),
(11, 'Penne Arrabiata', 'Spicy tomato sauce with garlic and chili', 11.99, 'AVAILABLE', 3),
(12, 'Fettuccine Alfredo', 'Rich creamy white sauce with fettuccine', 12.99, 'AVAILABLE', 3),
(13, 'Lasagna', 'Layered beef and bechamel baked to perfection', 14.49, 'AVAILABLE', 3),
(14, 'Caesar Salad', 'Romaine lettuce, croutons and Caesar dressing', 8.99, 'AVAILABLE', 4),
(15, 'Greek Salad', 'Tomatoes, cucumber, olives, feta cheese', 9.49, 'AVAILABLE', 4),
(16, 'Garden Fresh Salad', 'Mixed greens with balsamic vinaigrette', 7.99, 'AVAILABLE', 4),
(17, 'Chocolate Lava Cake', 'Warm chocolate cake with molten center', 6.99, 'AVAILABLE', 5),
(18, 'New York Cheesecake', 'Classic creamy cheesecake with berry compote', 5.99, 'AVAILABLE', 5),
(19, 'Tiramisu', 'Italian coffee dessert with mascarpone', 6.49, 'AVAILABLE', 5),
(20, 'Vanilla Ice Cream', 'Three scoops of classic vanilla', 4.49, 'OUT_OF_STOCK', 5),
(21, 'Fresh Lemonade', 'Freshly squeezed lemonade with mint', 3.99, 'AVAILABLE', 6),
(22, 'Mango Smoothie', 'Thick and creamy mango smoothie', 4.99, 'AVAILABLE', 6),
(23, 'Iced Coffee', 'Cold brew over ice with milk', 4.49, 'AVAILABLE', 6),
(24, 'Sparkling Water', 'Chilled sparkling mineral water', 2.49, 'AVAILABLE', 6),
(26, 'BLT Sandwich', 'Bacon, lettuce and tomato on toasted bread', 8.99, 'AVAILABLE', 7),
(27, 'Grilled Veggie Wrap', 'Grilled vegetables in a whole wheat wrap', 9.49, 'AVAILABLE', 7),
(28, 'Salmon Roll', '8 pieces of fresh salmon maki roll', 12.99, 'AVAILABLE', 8),
(29, 'Spicy Tuna Roll', '8 pieces with spicy tuna and cucumber', 13.49, 'AVAILABLE', 8),
(30, 'Dragon Roll', 'Prawn tempura topped with avocado', 15.99, 'AVAILABLE', 8),
(31, 'Club Sandwich', 'A classic stacked sandwich with chicken, bacon, egg, lettuce and tomato on toasted bread', 10.99, 'AVAILABLE', 7);

-- Carts (one per user)
INSERT INTO `cart` (`id`, `user_id`) VALUES
(1, 1),
(4, 7),
(5, 8),
(6, 9);

-- Orders
INSERT INTO `order` (`id`, `user_id`, `status`, `total_amount`, `order_date`) VALUES
(1, 1, 'PLACED', 25.98, '2026-05-10 03:56:00.000000'),
(4, 7, 'DELIVERED', 35.97, '2026-05-15 16:30:00.000000'),
(5, 8, 'CANCELLED', 13.99, '2026-05-15 17:30:00.000000'),
(6, 9, 'PLACED', 42.96, '2026-05-16 13:30:00.000000');

-- Order Items
INSERT INTO `order_item` (`id`, `order_id`, `food_item_id`, `quantity`, `price`) VALUES
(1, 1, 1, 2, 12.99),
(4, 4, 6, 1, 9.99),
(5, 4, 10, 1, 13.99),
(6, 4, 17, 1, 6.99),
(7, 5, 7, 1, 13.99),
(8, 6, 3, 2, 15.99),
(9, 6, 21, 2, 3.99),
(10, 6, 18, 1, 5.99);

-- Payments
INSERT INTO `payment` (`id`, `order_id`, `status`, `amount`, `payment_date`) VALUES
(1, 1, 'PENDING', 25.98, '2026-05-10 03:59:07.000000'),
(4, 4, 'COMPLETED', 35.97, '2026-05-15 16:35:00.000000'),
(5, 5, 'FAILED', 13.99, '2026-05-15 17:35:00.000000'),
(6, 6, 'PENDING', 42.96, '2026-05-16 13:35:00.000000');