DELETE FROM users;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User1', 'user1@gmail.com', 'password'),
  ('User2', 'user2@gmail.com', 'password'),
  ('Admin1', 'admin1@gmail.com', 'admin'),
  ('Admin2', 'admin2@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_ADMIN', 100002),
  ('ROLE_USER', 100002),
  ('ROLE_ADMIN', 100003),
  ('ROLE_USER', 100003);

INSERT INTO restaurants (name, address, open_time, close_time, menu_id, user_id) VALUES
  ('Ресторан 1', 'м. Київ вул. 1', '08:00:00', '21:00:00', 100007, 100002),
  ('Ресторан 2', 'м. Київ вул. 2', '09:00:00', '22:00:00', 100008, 100002),
  ('Ресторан 3', 'м. Київ вул. 3', '08:30:00', '22:00:00', 100009, 100003);

INSERT INTO menu (date_time, restaurant_id) VALUES
  ('2018-09-20 10:00:00', 100004),
  ('2018-09-20 9:00:00', 100005),
  ('2018-09-20 9:30:00', 100006);

INSERT INTO votes (user_id, restaurant_id, date_time) VALUES
  ('100000', '100004', '2018-09-20 10:10:00'),
  ('100001', '100004', '2018-09-20 10:30:00'),
  ('100000', '100005', '2018-09-20 15:10:00'),
  ('100001', '100006', '2018-09-20 16:00:00');

INSERT INTO meals (description, calories, menu_id) VALUES
  ('Борщ', 500, 100007),
  ('Салат', 1000, 100007),
  ('Гарнір', 1000, 100007),
  ('Пиріг', 1000, 100008),
  ('Кекс', 1000, 100008),
  ('Торт', 1000, 100008),
  ('Біфштекс', 1000, 100009),
  ('Пельмені', 1000, 100009),
  ('Шашлик', 1000, 100009);