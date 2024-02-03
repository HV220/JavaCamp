-- Insert users
INSERT INTO users (login, password) VALUES
    ('Andru', 'password1'),
    ('Jon', 'password2'),
    ('Ron', 'password3'),
    ('Kate', 'password4'),
    ('Man', 'password5');

-- Insert chatrooms
INSERT INTO chatrooms (name, owner_id) VALUES
    ('Actiivty', 1),
    ('Programming Help', 2),
    ('Travel', 3),
    ('Movie', 4),
    ('Music', 5);

-- Insert users into chatrooms
INSERT INTO users_chatrooms (user_id, chatroom_id) VALUES
    (1, 1),
    (2, 1),
    (3, 2),
    (4, 3),
    (5, 4);

-- Insert messages
INSERT INTO IF NOT EXISTS messages (author_id, room_id, text) VALUES
    (1, 1, 'HI!'),
    (2, 1, 'My Live is good!'),
    (3, 2, 'Harry potter?'),
    (4, 3, 'Asdfsdfsd?'),
    (5, 4, 'test?');