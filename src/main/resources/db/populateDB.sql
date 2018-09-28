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
  ('Ресторан 1', 'м. Київ вул. 1', '2018-09-20 10:10:00', 100004),
  ('Ресторан 2', 'м. Київ вул. 2', '2018-09-20 9:00:00', 100004),
  ('Ресторан 3', 'м. Київ вул. 3', '2018-09-20 9:30:00', 100005);

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
  ('Борщ', 25, 100006),
  ('Суп', 25, 100006),
  ('Салат', 30, 100006),
  ('Гарнір', 50, 100006),
  ('Пиріг', 20, 100007),
  ('Кекс', 18, 100007),
  ('Торт', 22, 100007),
  ('Біфштекс', 80, 100008),
  ('Пельмені', 40, 100008),
  ('Шашлик', 60, 100008);