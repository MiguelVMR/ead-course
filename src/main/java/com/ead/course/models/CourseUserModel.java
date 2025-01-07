package com.ead.course.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * The Class CourseUserModel
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 12/12/2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "tb_courses_users")
public class CourseUserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private CourseModel course;


    public CourseUserModel(UUID id, UUID userId, CourseModel course) {
        this.id = id;
        this.userId = userId;
        this.course = course;
    }

    public CourseUserModel() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }
}
