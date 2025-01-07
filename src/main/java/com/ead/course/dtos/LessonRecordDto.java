package com.ead.course.dtos;

import jakarta.validation.constraints.NotBlank;

/**
 * The Record LessonRecordDto
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 10/12/2024
 */
public record LessonRecordDto(@NotBlank(message = "Title is mandatory")
                              String title,

                              @NotBlank(message = "Description is mandatory")
                              String description,

                              @NotBlank(message = "VideoUrl is mandatory")
                              String videoUrl) {
}
