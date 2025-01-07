package com.ead.course.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/**
 * The Record ErrorRecordResponse
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 11/12/2024
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorRecordResponse(int errorCode,
                                  String errorMessage,
                                  Map<String, String> errorsDetails) {
}
