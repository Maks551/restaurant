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

INSERT INTO restaurants (name, address, date_of_add_menu, user_id) VALUES
  ('Ресторан 1', 'м. Київ вул. 1', '2018-09-20 10:10:00', 100002),
  ('Ресторан 2', 'м. Київ вул. 2', '2018-09-20 9:00:00', 100002),
  ('Ресторан 3', 'м. Київ вул. 3', '2018-09-20 9:30:00', 100003);

INSERT INTO votes (user_id, restaurant_id, date_time) VALUES
  ('100000', '100004', '2018-09-20 10:10:00'),
  ('100001', '100004', '2018-09-20 10:30:00'),
  ('100000', '100005', '2018-09-20 15:10:00'),
  ('100001', '100006', '2018-09-20 16:00:00');

INSERT INTO meals (description, calories, restaurant_id) VALUES
  ('Борщ', 600, 100004),
  ('Суп', 500, 100004),
  ('Салат', 300, 100004),
  ('Гарнір', 800, 100004),
  ('Пиріг', 600, 100005),
  ('Кекс', 400, 100005),
  ('Торт', 700, 100005),
  ('Біфштекс', 1000, 100006),
  ('Пельмені', 900, 100006),
  ('Шашлик', 1000, 100006);