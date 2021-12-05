INSERT INTO account(name, surname, email, password, status, role) VALUES
(
    'admin',
    'admin',
    'admin@outlook.com',
    '$2a$10$D.0IppTBsKgz8RJeaP8qmupsviZIt38lAcNMTLrZmrI4xMGHvVbbO',
    'ACTIVE',
    'ROLE_ADMIN'),
(
    'user',
    'user',
    'admin@outlook.com',
    '$2a$10$D.0IppTBsKgz8RJeaP8qmupsviZIt38lAcNMTLrZmrI4xMGHvVbbO',
    'BLOCKED',
    'ROLE_USER'
);

