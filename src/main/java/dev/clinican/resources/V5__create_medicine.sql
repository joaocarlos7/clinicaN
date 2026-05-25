CREATE TABLE medicine (
                             id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                             name VARCHAR(100) NOT NULL UNIQUE,
                             active_ingredient VARCHAR(100),
                             description TEXT
);