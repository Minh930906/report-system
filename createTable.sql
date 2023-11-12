CREATE TABLE IF NOT EXISTS location (
    id UUID PRIMARY KEY,
    manager_name VARCHAR(255),
    phone VARCHAR(20),
    address_primary VARCHAR(255),
    address_secondary VARCHAR(255),
    country VARCHAR(50),
    town VARCHAR(50),
    postal_code VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS listing_status (
    id INTEGER PRIMARY KEY,
    status_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS marketplace (
    id INTEGER PRIMARY KEY,
    marketplace_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS listing (
    id UUID PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    location_id UUID REFERENCES location(id),
    listing_price DOUBLE PRECISION,
    currency VARCHAR(10),
    quantity INTEGER,
    listing_status_id INTEGER REFERENCES listing_status(id),
    marketplace_id INTEGER REFERENCES marketplace(id),
    upload_time TIMESTAMP,
    owner_email_address VARCHAR(255)
);

ALTER TABLE listing
ADD CONSTRAINT fk_listing_location FOREIGN KEY (location_id) REFERENCES location(id);

ALTER TABLE listing
ADD CONSTRAINT fk_listing_listing_status FOREIGN KEY (listing_status) REFERENCES listing_status(id);

ALTER TABLE listing
ADD CONSTRAINT fk_listing_marketplace FOREIGN KEY (marketplace) REFERENCES marketplace(id);
