-- Insert Owners
INSERT INTO owner (id, name, email, currency)
VALUES
(1, 'John Doe', 'john.doe@example.com', 'USD'),
(2, 'Jane Smith', 'jane.smith@example.com', 'EUR');
-- Insert Budgets
INSERT INTO budget (id, owner_id, category, threshold, spent)
VALUES
(1, 1, 'Food', 500.00, 120.00),
(2, 1, 'Entertainment', 300.00, 50.00),
(3, 2, 'Travel', 1000.00, 300.00);
-- Insert Transactions
INSERT INTO transaction (id, owner_id, budget_id, cash_flow, amount, category, description, initiated_on)
VALUES
(1, 1, 1, 'EXPENSE', 20.00, 'Food', 'Lunch at a restaurant', '2023-12-02 10:30:00'),
(2, 1, 1, 'EXPENSE', 100.00, 'Food', 'Grocery shopping', '2023-12-02 10:30:00'),
(3, 1, 2, 'EXPENSE', 50.00, 'Entertainment', 'Movie night', '2023-12-02 10:30:00'),
(4, 2, 3, 'EXPENSE', 300.00, 'Travel', 'Flight tickets', '2023-12-02 10:30:00'),
(5, 2, NULL, 'INCOME', 2000.00, 'Salary', 'Monthly salary deposit', '2023-12-02 10:30:00');
-- Insert Savings Goals
INSERT INTO savings_goal (id, owner_id, name, target_amount, saved_amount, deadline)
VALUES
(1, 1, 'Vacation Fund', 2000.00, 500.00, '2024-06-01'),
(2, 2, 'Car Down Payment', 5000.00, 2000.00, '2024-12-01');