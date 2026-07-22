CREATE TABLE order_items (

                             id BIGSERIAL PRIMARY KEY,

                             order_id UUID NOT NULL,

                             product_code VARCHAR(100) NOT NULL,

                             quantity INTEGER NOT NULL,

                             CONSTRAINT fk_order_items_order
                                 FOREIGN KEY (order_id)
                                     REFERENCES orders(id)
                                     ON DELETE CASCADE
);