-- public.prescription definição

-- Drop table

-- DROP TABLE public.prescription;

CREATE TABLE public.prescription (
                                     id uuid DEFAULT gen_random_uuid() NOT NULL,
                                     consultation uuid NOT NULL,
                                     observation varchar(255) NULL,
                                     created_at timestamp DEFAULT now() NOT NULL,
                                     consultation_id uuid NOT NULL,
                                     doctor uuid NOT NULL,
                                     patient uuid NOT NULL,
                                     CONSTRAINT prescription_consultation_key UNIQUE (consultation),
                                     CONSTRAINT prescription_pkey PRIMARY KEY (id)
);


-- public.prescription chaves estrangeiras

ALTER TABLE public.prescription ADD CONSTRAINT fk8q23qrdhl2pih96ea0ydfqhqp FOREIGN KEY (doctor) REFERENCES public.doctor(id);
ALTER TABLE public.prescription ADD CONSTRAINT fkbijh9r2m0km9ua4x4i4i53to6 FOREIGN KEY (consultation_id) REFERENCES public.consultation(id);
ALTER TABLE public.prescription ADD CONSTRAINT fkd7n8yv15l4hb6145hqi3gwnl1 FOREIGN KEY (patient) REFERENCES public.patient(id);
ALTER TABLE public.prescription ADD CONSTRAINT prescription_consultation_fkey FOREIGN KEY (consultation) REFERENCES public.consultation(id);