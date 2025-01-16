package com.ead.course.repositories;

import com.ead.course.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

/**
 * The Interface UserRepository
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/01/2025
 */
public interface UserRepository extends JpaRepository<UserModel, UUID>, JpaSpecificationExecutor<UserModel> {
}
