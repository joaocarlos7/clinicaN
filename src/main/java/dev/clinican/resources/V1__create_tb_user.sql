CREATE TYPE role_type AS ENUM ('ADMIN', 'DOCTOR', 'PATIENT', 'RECEPTIONIST');


CREATE TABLE tb_user (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(150) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL,
                         role role_type NOT NULL,
                         active BOOLEAN NOT NULL DEFAULT TRUE,
                         created_at TIMESTAMP NOT NULL DEFAULT NOW()








)