CREATE TABLE IF NOT EXISTS brands (
  id INT PRIMARY KEY AUTO_INCREMENT,
  description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS products (
  id INT PRIMARY KEY,
  description VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS prices (
  id INT PRIMARY KEY AUTO_INCREMENT,
  brand_id INT,
  start_date DATETIME,
  end_date DATETIME,
  price_list INT,
  product_id INT,
  priority INT,
  price DECIMAL(10, 2),
  currency VARCHAR(3),
  FOREIGN KEY (brand_id) REFERENCES brands(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO brands (description)
VALUES ('ZARA');

INSERT INTO products (id, description)
VALUES (35455,'Product A'), (35456,'Product B');

INSERT INTO prices (brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES
    (1, '2020-06-14 10:00:00', '2020-12-31 23:59:00', 1, 35455, 0, 35.50, 'EUR'),
    (1, '2020-06-14 16:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
    (1, '2020-06-14 21:00:00', '2020-12-31 23:59:00', 3, 35455, 1, 38.95, 'EUR'),
    (1, '2020-06-15 10:00:00', '2020-12-31 23:59:00', 3, 35455, 1, 38.95, 'EUR'),
    (1, '2020-06-16 21:00:00', '2020-06-16 18:30:00', 2, 35455, 1, 25.45, 'EUR');