package com.ead.course.controllers;

import com.ead.course.dtos.CourseRecordDto;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;
import com.ead.course.specifications.SpecificationTemplate;
import com.ead.course.validations.CourseValidator;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The Class CourseController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 09/12/2024
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseValidator courseValidator;

    public CourseController(CourseService courseService, CourseValidator courseValidator) {
        this.courseService = courseService;
        this.courseValidator = courseValidator;
    }

    @PreAuthorize( "hasAnyRole('INSTRUCTOR')")
    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody CourseRecordDto courseRecordDto, Errors errors){

        courseValidator.validate(courseRecordDto, errors);
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getAllErrors());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseRecordDto));
    }

    @PreAuthorize( "hasAnyRole('USER')")
    @GetMapping
    public ResponseEntity<Page<CourseModel>> getAllCourses(SpecificationTemplate.CourseSpec spec,
                                                           Pageable pageable,
                                                           @RequestParam(required = false) UUID userId){
        Page<CourseModel> courseModelPage = (userId != null)
                ? courseService.findAll(SpecificationTemplate.courseUserId(userId).and(spec), pageable)
                : courseService.findAll(spec, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(courseModelPage);
    }

    @PreAuthorize( "hasAnyRole('USER')")
    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getOneCourse(@PathVariable(value = "courseId") UUID courseId){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findById(courseId).get());
    }

    @PreAuthorize( "hasAnyRole('INSTRUCTOR')")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable(value = "courseId") UUID courseId){
        courseService.delete(courseService.findById(courseId).get());
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully.");
    }

    @PreAuthorize( "hasAnyRole('INSTRUCTOR')")
    @PutMapping("/{courseId}")
    public ResponseEntity<Object> updateCourse(@PathVariable(value = "courseId") UUID courseId,
                                               @RequestBody @Valid CourseRecordDto courseRecordDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(courseService.update(courseRecordDto, courseService.findById(courseId).get()));
    }
}
