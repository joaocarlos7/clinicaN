-- public.tb_user definição

-- Drop table

-- DROP TABLE public.tb_user;

CREATE TABLE public.tb_user (
                                id uuid DEFAULT gen_random_uuid() NOT NULL,
                                "name" varchar(255) NOT NULL,
                                email varchar(255) NOT NULL,
                                "password" varchar(255) NOT NULL,
                                "role" varchar(255) NOT NULL,
                                active bool DEFAULT true NOT NULL,
                                created_at timestamp DEFAULT now() NOT NULL,
                                CONSTRAINT tb_user_email_key UNIQUE (email),
                                CONSTRAINT tb_user_pkey PRIMARY KEY (id)
);