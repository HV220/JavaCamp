CREATE TABLE IF NOT EXISTS users (
                       id SERIAL PRIMARY KEY,
                       login VARCHAR(100) NOT NULL,
                       password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS chatrooms (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           owner_id INTEGER REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users_chatrooms (
                                 user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
                                 chatroom_id INTEGER REFERENCES chatrooms(id) ON DELETE CASCADE,
                                 PRIMARY KEY (user_id, chatroom_id)
);

CREATE TABLE IF NOT EXISTS messages (
                          id SERIAL PRIMARY KEY,
                          author_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
                          room_id INTEGER REFERENCES chatrooms(id) ON DELETE CASCADE,
                          text VARCHAR(1000) NOT NULL,
                          date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);