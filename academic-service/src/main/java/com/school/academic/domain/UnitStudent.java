package com.school.academic.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "unit_student", indexes = {
        @Index(name = "unit_id_idx", columnList = "unit_id"),
        @Index(name = "student_id_idx", columnList = "student_id"),
        @Index(name = "student_unit_idx", columnList = "student_id, unit_id")
}, uniqueConstraints ={
        @UniqueConstraint(columnNames = {"student_id", "unit_id"})
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UnitStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Long studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private Student student;

    @Column(name = "unit_id", insertable = false, updatable = false)
    private Long unitId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    @ToString.Exclude
    private Unit unit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UnitStudent that = (UnitStudent) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}