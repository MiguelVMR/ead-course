package com.ead.course.services;

import com.ead.course.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * The Interface UserService
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/01/2025
 */
public interface UserService {

    Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);
}
