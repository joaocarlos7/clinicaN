-- public.doctor definição

-- Drop table

-- DROP TABLE public.doctor;

CREATE TABLE public.doctor (
                               id uuid DEFAULT gen_random_uuid() NOT NULL,
                               user_id uuid NOT NULL,
                               crm int8 NOT NULL,
                               speciality varchar(255) NOT NULL,
                               phone_number int8 NULL,
                               CONSTRAINT doctor_crm_key UNIQUE (crm),
                               CONSTRAINT doctor_pkey PRIMARY KEY (id),
                               CONSTRAINT doctor_user_id_key UNIQUE (user_id)
);


-- public.doctor chaves estrangeiras

ALTER TABLE public.doctor ADD CONSTRAINT doctor_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.tb_user(id);