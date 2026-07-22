CREATE TABLE inventory_items (

                                 id UUID PRIMARY KEY DEFAULT  gen_random_uuid(),

                                 product_code VARCHAR(100) NOT NULL UNIQUE,

                                 description VARCHAR(255) NOT NULL,

                                 available_stock INTEGER NOT NULL,

                                 reserved_stock INTEGER NOT NULL,

                                 created_at TIMESTAMP NOT NULL

);