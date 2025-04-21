package com.ead.course.dtos;

import java.util.UUID;

/**
 * The Record NotificationRecordCommandDto
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 11/02/2025
 */
public record NotificationRecordCommandDto(String title,
                                           String message,
                                           UUID userId) {
}
