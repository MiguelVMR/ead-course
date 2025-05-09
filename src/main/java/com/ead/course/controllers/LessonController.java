package com.ead.course.controllers;

import com.ead.course.dtos.LessonRecordDto;
import com.ead.course.models.LessonModel;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;
import com.ead.course.specifications.SpecificationTemplate;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The Class LessonController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 10/12/2024
 */
@RestController
public class LessonController {

    private final ModuleService moduleService;

    private final LessonService lessonService;

    public LessonController(ModuleService moduleService, LessonService lessonService) {
        this.moduleService = moduleService;
        this.lessonService = lessonService;
    }

    @PreAuthorize( "hasAnyRole('INSTRUCTOR')")
    @PostMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<Object> saveLesson(@PathVariable(value = "moduleId") UUID moduleId,
                                             @RequestBody @Valid LessonRecordDto lessonRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(lessonService.save(lessonRecordDto, moduleService.findById(moduleId).get()));
    }

    @PreAuthorize( "hasAnyRole('USER')")
    @GetMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<Page<LessonModel>> getAllLessons(@PathVariable(value = "moduleId") UUID moduleId,
                                                           SpecificationTemplate.LessonSpec spec,
                                                           Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonService.findAllLessonsIntoModule(SpecificationTemplate.lessonModuleId(moduleId).and(spec), pageable));
    }

    @PreAuthorize( "hasAnyRole('USER')")
    @GetMapping("/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<Object> getOneLesson(@PathVariable(value = "moduleId") UUID moduleId,
                                               @PathVariable(value = "lessonId") UUID lessonId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonService.findLessonIntoModule(moduleId, lessonId).get());
    }

    @PreAuthorize( "hasAnyRole('INSTRUCTOR')")
    @DeleteMapping("/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<Object> deleteLesson(@PathVariable(value = "moduleId") UUID moduleId,
                                               @PathVariable(value = "lessonId") UUID lessonId){
        lessonService.delete(lessonService.findLessonIntoModule(moduleId, lessonId).get());
        return ResponseEntity.status(HttpStatus.OK).body("Lesson deleted successfully.");
    }

    @PreAuthorize( "hasAnyRole('INSTRUCTOR')")
    @PutMapping("/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<Object> updateLesson(@PathVariable(value = "moduleId") UUID moduleId,
                                               @PathVariable(value = "lessonId") UUID lessonId,
                                               @RequestBody @Valid LessonRecordDto lessonRecordDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonService.update(lessonRecordDto, lessonService.findLessonIntoModule(moduleId, lessonId).get()));
    }
}
