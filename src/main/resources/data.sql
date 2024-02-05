-- Добавление ролей
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

-- Добавление пользователей
INSERT INTO users (username, password, age, name, last_name) VALUES ('u', 'u', 24, 'Pavel', 'RobotXiaomi');
INSERT INTO users (username, password, age, name, last_name) VALUES ('r', 'r', 31, 'Ricardo', 'Milos');
INSERT INTO users (username, password, age, name, last_name) VALUES ('n', 'n', 43, 'Natalia', 'Marines');
INSERT INTO users (username, password, age, name, last_name) VALUES ('admin', 'admin', 24, 'Alex', 'White');
INSERT INTO users (username, password, age, name, last_name) VALUES ('user', 'user', 14, 'Oleg', 'GoodJob');

-- Связывание пользователей с ролями
INSERT INTO users_roles (user_id, roles_id) VALUES
    ((SELECT id FROM users WHERE username = 'u'), (SELECT id FROM roles WHERE name = 'ROLE_USER')),
    ((SELECT id FROM users WHERE username = 'r'), (SELECT id FROM roles WHERE name = 'ROLE_USER')),
    ((SELECT id FROM users WHERE username = 'r'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
    ((SELECT id FROM users WHERE username = 'n'), (SELECT id FROM roles WHERE name = 'ROLE_USER')),
    ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
    ((SELECT id FROM users WHERE username = 'user'), (SELECT id FROM roles WHERE name = 'ROLE_USER'));
