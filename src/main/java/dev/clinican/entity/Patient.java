package dev.clinican.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private TbUser user;

    @Column(unique = true)
    private String cpf;

    @Column(name = "born_day", nullable = false)
    private LocalDateTime bornDay;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    private String address;
}
