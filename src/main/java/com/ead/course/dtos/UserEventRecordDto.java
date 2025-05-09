package com.ead.course.dtos;

import com.ead.course.models.UserModel;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

/**
 * The Record UserEventRecordDto
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 19/01/2025
 */
public record UserEventRecordDto(UUID userId,
                                 String username,
                                 String email,
                                 String fullName,
                                 String userStatus,
                                 String userType,
                                 String phoneNumber,
                                 String imageUrl,
                                 String actionType) {

    public UserModel convertToUserModel(){
        var userModel = new UserModel();
        BeanUtils.copyProperties(this, userModel);
        return userModel;
    }
}
