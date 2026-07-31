CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE user_profile(
    id  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL ,
    email VARCHAR(150) NOT NULL UNIQUE,
    user_id UUID NOT NULL UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
