package com.ead.course.validations;

import com.ead.course.configs.security.AuthenticationCurrentUserService;
import com.ead.course.configs.security.UserDetailsImpl;
import com.ead.course.dtos.CourseRecordDto;
import com.ead.course.enums.UserType;
import com.ead.course.models.UserModel;
import com.ead.course.services.CourseService;
import com.ead.course.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
import java.util.UUID;

/**
 * The Class CourseValidator
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 16/12/2024
 */
@Component
public class CourseValidator implements Validator {

    final Validator validator;
    final CourseService courseService;
    final UserService userService;
    final AuthenticationCurrentUserService authenticationCurrentUserService;

    public CourseValidator(@Qualifier("defaultValidator") Validator validator, CourseService courseService, UserService userService, AuthenticationCurrentUserService authenticationCurrentUserService) {
        this.validator = validator;
        this.courseService = courseService;
        this.userService = userService;
        this.authenticationCurrentUserService = authenticationCurrentUserService;
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
        UserDetailsImpl userDetails = authenticationCurrentUserService.getCurrentUser();
        if(userDetails.getUserId().equals(userInstructor) || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))){
            Optional<UserModel> userModelOptional = userService.findById(userInstructor);
            if(userModelOptional.get().getUserType().equals(UserType.STUDENT.toString()) ||
               userModelOptional.get().getUserType().equals(UserType.USER.toString())){
                errors.rejectValue("userInstructor", "UserInstructorError", "User must be INSTRUCTOR or ADMIN.");
            }
        } else {
            throw new AccessDeniedException("Forbidden");
        }
    }


}
