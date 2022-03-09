package com.school.academic.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teacher")
@Data
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "national_code", unique = true, nullable = false)
    private Long nationalCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    @ToString.Exclude
    private Set<Unit> units;
}
