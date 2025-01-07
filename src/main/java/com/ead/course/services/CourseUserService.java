package com.ead.course.services;

import com.ead.course.models.CourseModel;
import com.ead.course.models.CourseUserModel;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * The Interface CourseUserService
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 12/12/2024
 */
public interface CourseUserService {
    boolean existsByCourseAndUserId(CourseModel courseModel, UUID userId);

    CourseUserModel saveAndSendSubscriptionUserInCourse(CourseUserModel courseUserModel);

    boolean existsByUserId(UUID userId);

    void deleteAllByUserId(UUID userId);
}
