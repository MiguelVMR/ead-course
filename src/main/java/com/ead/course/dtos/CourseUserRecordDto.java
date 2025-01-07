package com.ead.course.dtos;

import java.util.UUID;

/**
 * The Record CourseUserRecordDto
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 16/12/2024
 */
public record CourseUserRecordDto(UUID courseId,
                                  UUID userId) {
}
