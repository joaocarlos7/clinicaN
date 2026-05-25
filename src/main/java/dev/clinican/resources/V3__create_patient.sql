
CREATE TABLE patient (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         user_id UUID NOT NULL UNIQUE REFERENCES tb_user(id),
                         cpf VARCHAR(14) NOT NULL UNIQUE,
                         born_day DATE NOT NULL,
                         phone_number VARCHAR(20),
                         adress VARCHAR(255)

)

