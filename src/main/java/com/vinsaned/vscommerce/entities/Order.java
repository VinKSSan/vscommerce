package com.vinsaned.vscommerce.entities;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.time.Instant;

@Entity
@Table(name="tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;
    private OrderStatus status ;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;//relacionamento para um
}
