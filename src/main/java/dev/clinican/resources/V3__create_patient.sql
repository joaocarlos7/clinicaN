-- public.patient definição

-- Drop table

-- DROP TABLE public.patient;

CREATE TABLE public.patient (
                                id uuid DEFAULT gen_random_uuid() NOT NULL,
                                user_id uuid NOT NULL,
                                cpf varchar(255) NOT NULL,
                                born_day timestamp(6) NOT NULL,
                                phone_number varchar(255) NULL,
                                address varchar(255) NULL,
                                CONSTRAINT patient_cpf_key UNIQUE (cpf),
                                CONSTRAINT patient_pkey PRIMARY KEY (id),
                                CONSTRAINT patient_user_id_key UNIQUE (user_id)
);


-- public.patient chaves estrangeiras

ALTER TABLE public.patient ADD CONSTRAINT patient_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.tb_user(id);