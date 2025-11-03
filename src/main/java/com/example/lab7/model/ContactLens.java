package com.example.lab7.model;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name="contactlens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class СontactLens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "color")
    private String color;
    @Column(name = "typePlMin")
    private String typePlMin;
    @Column(name = "howM")
    private int howM;
    @Column(name = "bbdate")
    private String bbdate;
}
