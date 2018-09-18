DELETE FROM users;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq_user RESTART WITH 100000;
ALTER SEQUENCE global_seq_menu RESTART WITH 100000;
ALTER SEQUENCE global_seq_restaurant RESTART WITH 100000;
ALTER SEQUENCE global_seq_vote RESTART WITH 100000;

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

INSERT INTO restaurants (name, address, menu_id, vote_id, user_id) VALUES
  ('Ресторан 1', 'м. Київ вул. 1', 100000, 100000, 100002),
  ('Ресторан 2', 'м. Київ вул. 2', 100001, 100001, 100002),
  ('Ресторан 3', 'м. Київ вул. 3', 100002, 100002, 100003);

INSERT INTO menu (date_time, restaurant_id) VALUES
  ('2018-09-20 10:00:00', 100000),
  ('2018-09-20 9:00:00', 100001),
  ('2018-09-20 9:30:00', 100002);

INSERT INTO meals (description, calories, menu_id) VALUES
  ('Борщ', 500, 100000),
  ('Салат', 1000, 100000),
  ('Гарнір', 1000, 100000),
  ('Пиріг', 1000, 100001),
  ('Кекс', 1000, 100001),
  ('Торт', 1000, 100001),
  ('Біфштекс', 1000, 100002),
  ('Пельмені', 1000, 100002),
  ('Шашлик', 1000, 100002);