
CREATE TABLE prescription (
                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              consultation UUID NOT NULL UNIQUE REFERENCES consultation(id),
                              observation TEXT,
                              created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE prescription_medicine (
                                       prescription_id UUID NOT NULL REFERENCES prescription(id),
                                       medicine_id UUID NOT NULL REFERENCES medicine(id),
                                       dose VARCHAR(50) NOT NULL,
                                       frequency VARCHAR(80) NOT NULL,
                                       number_of_days INTEGER,
                                       PRIMARY KEY (prescription_id, medicine_id)
);