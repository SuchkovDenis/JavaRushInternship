package ru.suchkov.parts.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "parts")
@Data
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "necessary", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean necessary;

    @Column(name = "amount")
    private Integer amount;
}
