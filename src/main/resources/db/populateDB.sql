DELETE FROM users;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User1', 'user1@gmail.com', 'password'),
  ('User2', 'user2@gmail.com', 'password'),
  ('User3', 'user3@gmail.com', 'password'),
  ('User4', 'user4@gmail.com', 'password'),
  ('Admin1', 'admin1@gmail.com', 'user'),
  ('Admin2', 'admin2@gmail.com', 'user');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_USER', 100003),
  ('ROLE_USER', 100004),
  ('ROLE_ADMIN', 100004),
  ('ROLE_USER', 100005),
  ('ROLE_ADMIN', 100005);

INSERT INTO restaurants (name, address, date_of_add_menu, user_id) VALUES
  ('Restaurant 1', 'Kiev str. 1', '2018-09-20 10:10:00', 100004),
  ('Restaurant 2', 'Kiev str. 2', '2018-09-20 9:00:00', 100004),
  ('Restaurant 3', 'Kiev str. 3', '2018-09-20 9:30:00', 100005);

INSERT INTO votes (user_id, restaurant_id, date_time, vote) VALUES
  (100000, 100006, '2018-09-20 10:10:00', 1),
  (100001, 100006, '2018-09-20 10:20:00', 1),
  (100003, 100006, '2018-09-20 11:30:00', -1),
  (100000, 100006, '2018-09-19 10:00:00', 1),
  (100000, 100007, '2018-09-20 15:10:00', 1),
  (100002, 100007, '2018-09-20 11:10:00', 1),
  (100003, 100007, '2018-09-20 15:30:00', -1),
  (100001, 100008, '2018-09-20 16:00:00', 1);

INSERT INTO meals (description, price, restaurant_id) VALUES
  ('Beet soup', 25, 100006),
  ('Soup', 25, 100006),
  ('Salad', 30, 100006),
  ('Garnish', 50, 100006),
  ('Pie', 20, 100007),
  ('Cake', 18, 100007),
  ('Strawberry Cake', 22, 100007),
  ('Steak', 80, 100008),
  ('Dumplings', 40, 100008),
  ('Kebab', 60, 100008);