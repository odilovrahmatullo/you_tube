CREATE TABLE IF NOT EXISTS profile (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255),
                         surname VARCHAR(255),
                         email VARCHAR(255) UNIQUE,
                         password VARCHAR(255),
                         photo VARCHAR(255),
                         role VARCHAR(255) CHECK (role IN ('ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE_PUBLISHER', 'ROLE_USER')),
                         status VARCHAR(255),
                         visible BOOLEAN,
                         created_date TIMESTAMP
);
