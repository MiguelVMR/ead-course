package com.ead.course.services;

import com.ead.course.dtos.ModuleRecordDto;
import com.ead.course.models.CourseModel;
import com.ead.course.models.ModuleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The Interface ModuleService
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 27/11/2024
 */
public interface ModuleService {

    void delete(ModuleModel moduleModel);

    ModuleModel save(ModuleRecordDto moduleRecordDto, CourseModel courseModel);

    Page<ModuleModel> findAllModulesIntoCourse(Specification<ModuleModel> spec, Pageable pageable);

    Optional<ModuleModel> findModuleIntoCourse(UUID courseId, UUID moduleId);

    ModuleModel update(ModuleRecordDto moduleRecordDto, ModuleModel moduleModel);

    Optional<ModuleModel> findById(UUID moduleId);

    List<ModuleModel> findAllModulesIntoCourse(UUID courseId);
}
