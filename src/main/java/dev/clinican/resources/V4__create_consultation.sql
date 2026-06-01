-- public.consultation definição

-- Drop table

-- DROP TABLE public.consultation;

CREATE TABLE public.consultation (
                                     id uuid DEFAULT gen_random_uuid() NOT NULL,
                                     patient_id uuid NOT NULL,
                                     doctor_id uuid NOT NULL,
                                     day_and_time timestamp(6) NOT NULL,
                                     reason varchar(500) NULL,
                                     note text NULL,
                                     created_by uuid NULL,
                                     created_at timestamp DEFAULT now() NOT NULL,
                                     consultation_status varchar(255) NOT NULL,
                                     CONSTRAINT consultation_pkey PRIMARY KEY (id)
);
CREATE INDEX idx_consultation_data ON public.consultation USING btree (day_and_time);
CREATE INDEX idx_consultation_doctor ON public.consultation USING btree (doctor_id);
CREATE INDEX idx_consultation_patient ON public.consultation USING btree (patient_id);


-- public.consultation chaves estrangeiras

ALTER TABLE public.consultation ADD CONSTRAINT consultation_created_by_fkey FOREIGN KEY (created_by) REFERENCES public.tb_user(id);
ALTER TABLE public.consultation ADD CONSTRAINT consultation_doctor_id_fkey FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);
ALTER TABLE public.consultation ADD CONSTRAINT consultation_patient_id_fkey FOREIGN KEY (patient_id) REFERENCES public.patient(id);