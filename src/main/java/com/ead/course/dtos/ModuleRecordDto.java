package com.ead.course.dtos;

import jakarta.validation.constraints.NotBlank;

/**
 * The Record ModuleRecordDto
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 10/12/2024
 */
public record ModuleRecordDto(@NotBlank(message = "Title is mandatory")
                              String title,

                              @NotBlank(message = "Description is mandatory")
                              String description) {
}
