CREATE TYPE status_type AS ENUM ('SCHEDULED', 'COMPLETED', 'CANCELED');



CREATE TABLE consultation (

                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              patient_id UUID NOT NULL REFERENCES patient(id),
                              doctor_id UUID NOT NULL REFERENCES doctor(id),
                              day_and_time TIMESTAMP NOT NULL,
                              status status_type NOT NULL,
                              reason VARCHAR(500),
                              note TEXT,
                              created_by UUID REFERENCES tb_user(id),
                              created_at TIMESTAMP NOT NULL DEFAULT NOW()

);

CREATE INDEX idx_consultation_patient ON consultation(patient_id);
CREATE INDEX idx_consultation_doctor ON consultation(doctor_id);
CREATE INDEX idx_consultation_data ON consultation(day_and_time);


