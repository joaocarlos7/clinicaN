CREATE TABLE consultation_history (
                                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                      consultation_id UUID NOT NULL REFERENCES consultation(id),
                                      status_before status_type,
                                      status_new status_type,
                                      changed_by UUID REFERENCES tb_user(id),
                                      changed_at TIMESTAMP NOT NULL DEFAULT NOW()
);