CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE books(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    author VARCHAR(255),
    img_url VARCHAR(250),
    completed BOOLEAN NOT NULL,
    started_at TIMESTAMP NOT NULL,
    finished_at TIMESTAMP,
    user_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);