package com.ead.course.services;

import com.ead.course.dtos.CourseRecordDto;
import com.ead.course.models.CourseModel;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

/**
 * The Interface CourseService
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 27/11/2024
 */
public interface CourseService {

    void delete(CourseModel courseModel);

    CourseModel save(CourseRecordDto courseRecordDto);

    boolean existsByName(@NotBlank(message = "Name is mandatory") String name);

    Page<CourseModel> findAll(Specification<CourseModel> spec, Pageable pageable);

    Optional<CourseModel> findById(UUID courseId);

    CourseModel update(CourseRecordDto courseRecordDto, CourseModel courseModel);
}
