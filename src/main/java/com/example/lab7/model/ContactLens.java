package com.example.lab7.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contactlens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactLens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "color")
    private String color;
    @Column(name = "power")
    private double power;
    @Column(name = "howM")
    private int howM;
    @Column(name = "bbdate")
    private String bbdate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "lens_tag",
            joinColumns = @JoinColumn(name = "lens_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();

}
