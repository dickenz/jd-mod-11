INSERT INTO client (name) VALUES
    ('Alice'),
    ('Bob'),
    ('Charlie'),
    ('David'),
    ('Eve'),
    ('Frank'),
    ('Grace'),
    ('Helen'),
    ('Isaac'),
    ('Jane');

INSERT INTO planet (id, name) VALUES
    ('MARS', 'Mars'),
    ('VEN', 'Venus'),
    ('JUP', 'Jupiter'),
    ('SAT', 'Saturn'),
    ('URAN', 'Uranus');

INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id) VALUES
    (CURRENT_TIMESTAMP, 1, 'MARS', 'VEN'),
    (CURRENT_TIMESTAMP, 2, 'VEN', 'MARS'),
    (CURRENT_TIMESTAMP, 3, 'JUP', 'URAN'),
    (CURRENT_TIMESTAMP, 4, 'SAT', 'MARS'),
    (CURRENT_TIMESTAMP, 5, 'URAN', 'JUP'),
    (CURRENT_TIMESTAMP, 6, 'MARS', 'JUP'),
    (CURRENT_TIMESTAMP, 7, 'SAT', 'VEN'),
    (CURRENT_TIMESTAMP, 8, 'VEN', 'JUP'),
    (CURRENT_TIMESTAMP, 9, 'JUP', 'MARS'),
    (CURRENT_TIMESTAMP, 10, 'URAN', 'SAT');