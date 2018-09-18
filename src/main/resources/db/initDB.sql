DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id          INTEGER                 NOT NULL,
  role             VARCHAR                 NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name              VARCHAR                 NOT NULL,
  open_time         TIME                    NOT NULL,
  close_time        TIME                    NOT NULL,
  address           VARCHAR                 NOT NULL,
  menu_id           INTEGER                 NOT NULL,
  user_id           INTEGER                 NOT NULL
);
CREATE UNIQUE INDEX restaurant_unique_address_idx ON restaurants (address);

CREATE TABLE menu
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date_time         TIMESTAMP DEFAULT now() NOT NULL,
  restaurant_id     INTEGER                 NOT NULL,
  CONSTRAINT menu_restaurant_idx UNIQUE (restaurant_id, id),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX unique_menu_datetime_idx
  ON menu (id, date_time);

CREATE TABLE meals
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  menu_id           INTEGER                 NOT NULL,
  description       VARCHAR                 NOT NULL,
  calories          INTEGER                 NOT NULL,
  CONSTRAINT meal_menu_idx UNIQUE (menu_id, id),
  FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  id                INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id           INTEGER                 NOT NULL,
  restaurant_id     INTEGER                 NOT NULL,
  date_time         TIMESTAMP               NOT NULL,
  CONSTRAINT vote_user_idx UNIQUE (user_id, id),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  CONSTRAINT vote_restaurant_idx UNIQUE (restaurant_id, id),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX unique_vote_datetime_idx
  ON votes (id, date_time);