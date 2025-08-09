CREATE TABLE locations (
    id SERIAL PRIMARY KEY,
    code VARCHAR(10) UNIQUE NOT NULL,
    name VARCHAR(100),
    address VARCHAR(200),
    city VARCHAR(100),
    country VARCHAR(100),
    zip_code VARCHAR(10),
    geolocation VARCHAR(100)
);

CREATE TABLE states (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE packages (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(200),
    weight DECIMAL(10,2),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    current_state_id INT REFERENCES states(id),
    source_id INT REFERENCES locations(id),
    destination_id INT REFERENCES locations(id)
);

CREATE TABLE state_history (
    id SERIAL PRIMARY KEY,
    package_id INT REFERENCES packages(id),
    state_id INT REFERENCES states(id),
    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


