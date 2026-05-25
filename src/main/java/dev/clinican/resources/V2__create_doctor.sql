
CREATE TABLE doctor (
                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                        user_id UUID NOT NULL UNIQUE REFERENCES tb_user(id),
                        crm VARCHAR(20) NOT NULL UNIQUE,
                        name VARCHAR(100) NOT NULL,
                        speciality VARCHAR(90) NOT NULL,
                        phone_number VARCHAR(20)

)







