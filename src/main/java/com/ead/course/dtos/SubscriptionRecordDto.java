package com.ead.course.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * The Record SubscriptionRecordDto
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/12/2024
 */
public record SubscriptionRecordDto(@NotNull(message = "UserId is mandatory") UUID userId) {
}
