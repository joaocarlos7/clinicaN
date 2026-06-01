-- public.consultation_history definição

-- Drop table

-- DROP TABLE public.consultation_history;

CREATE TABLE public.consultation_history (
                                             id uuid DEFAULT gen_random_uuid() NOT NULL,
                                             consultation_id uuid NOT NULL,
                                             status_before varchar(255) NULL,
                                             status_new varchar(255) NOT NULL,
                                             changed_by uuid NULL,
                                             changed_at timestamp DEFAULT now() NOT NULL,
                                             CONSTRAINT consultation_history_pkey PRIMARY KEY (id)
);


-- public.consultation_history chaves estrangeiras

ALTER TABLE public.consultation_history ADD CONSTRAINT consultation_history_changed_by_fkey FOREIGN KEY (changed_by) REFERENCES public.tb_user(id);
ALTER TABLE public.consultation_history ADD CONSTRAINT consultation_history_consultation_id_fkey FOREIGN KEY (consultation_id) REFERENCES public.consultation(id);