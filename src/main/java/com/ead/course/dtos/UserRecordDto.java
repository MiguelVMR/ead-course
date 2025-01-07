package com.ead.course.dtos;

import com.ead.course.enums.UserStatus;
import com.ead.course.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

/**
* The Record UserRecordDto
*
* @author Miguel Vilela Moraes Ribeiro
* @since 15/12/2024
*/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRecordDto(UUID userId,
                            String username,
                            String email,
                            String fullName,
                            UserStatus userStatus,
                            UserType userType,
                            String phoneNumber,
                            String imageUrl) {
}
