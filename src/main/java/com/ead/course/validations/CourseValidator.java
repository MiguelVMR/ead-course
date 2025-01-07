package com.ead.course.validations;

import com.ead.course.clients.AuthUserClient;
import com.ead.course.dtos.CourseRecordDto;
import com.ead.course.dtos.UserRecordDto;
import com.ead.course.enums.UserType;
import com.ead.course.services.CourseService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.UUID;

/**
 * The Class CourseValidator
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 16/12/2024
 */
@Component
public class CourseValidator implements Validator {

    private final Validator validator;
    final CourseService courseService;
    final AuthUserClient authUserClient;

    public CourseValidator(@Qualifier("defaultValidator") Validator validator, CourseService courseService, AuthUserClient authUserClient) {
        this.validator = validator;
        this.courseService = courseService;
        this.authUserClient = authUserClient;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        CourseRecordDto courseRecordDto = (CourseRecordDto) o;
        validator.validate(courseRecordDto, errors);
        if(!errors.hasErrors()){
            validateCourseName(courseRecordDto, errors);
            validateUserInstructor(courseRecordDto.userInstructor(), errors);
        }
    }

    private void validateCourseName(CourseRecordDto courseRecordDto, Errors errors){
        if(courseService.existsByName(courseRecordDto.name())){
            errors.rejectValue("name", "courseNameConflict", "Course Name is Already Taken.");
        }
    }

    private void validateUserInstructor(UUID userInstructor, Errors errors){
        ResponseEntity<UserRecordDto> responseUserInstructor = authUserClient.getOneUserById(userInstructor);
        if(responseUserInstructor.getBody().userType().equals(UserType.STUDENT) ||
           responseUserInstructor.getBody().userType().equals(UserType.USER)){
            errors.rejectValue("userInstructor", "UserInstructorError", "User must be INSTRUCTOR or ADMIN.");
        }
    }


}
