    package dev.clinican.entity;


    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.util.UUID;

    @Entity
    @Table(name = "doctor")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder


    public class Doctor {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @OneToOne
        @JoinColumn(name = "user_id", nullable = false, unique = true)
        private TbUser user;

        @Column(unique = true)
        private Integer crm;

        @Column(nullable = false)
        private String speciality;

        @Column(name = "phone_number", nullable = false)
        private Integer phoneNumber;

    }
