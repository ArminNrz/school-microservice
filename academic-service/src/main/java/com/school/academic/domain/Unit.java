package com.school.academic.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "unit", indexes = {
        @Index(name = "lesson_id_idx", columnList = "lesson_id"),
        @Index(name = "teacher_id_idx", columnList = "teacher_id")
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "point", nullable = false)
    private BigDecimal point;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "lesson_id", insertable = false, updatable = false)
    private Long lessonId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    @ToString.Exclude
    private Lesson lesson;

    @Column(name = "teacher_id", insertable = false, updatable = false)
    private Long teacherId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    @ToString.Exclude
    private Teacher teacher;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
    private Set<UnitStudent> unitStudents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Unit unit = (Unit) o;
        return id != null && Objects.equals(id, unit.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}