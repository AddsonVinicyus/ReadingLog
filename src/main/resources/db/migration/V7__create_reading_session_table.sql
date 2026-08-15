CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE reading_session(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    duration_seconds INT NOT NULL,
    pages_read INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    user_id UUID NOT NULL,
    book_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);