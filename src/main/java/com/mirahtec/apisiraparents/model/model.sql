CREATE TABLE token_blacklist (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP NOT NULL
);

--ajouter un column dans la table user isActive
ALTER TABLE user ADD COLUMN isActive BOOLEAN DEFAULT TRUE;