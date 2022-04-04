package com.socialnetwork.demo.utils;

import com.socialnetwork.demo.dto.User;
import com.socialnetwork.demo.exceptions.badRequest.MissedFieldException;
import com.socialnetwork.demo.exceptions.notFound.UserNotFoundException;
import com.socialnetwork.demo.repositories.UserRepository;
import org.springframework.data.repository.CrudRepository;

public class Utils {
    public static void verifyId(CrudRepository repository, Long userId) throws MissedFieldException, UserNotFoundException {
        if (null == userId || 0 == userId) {
            throw new MissedFieldException("user_id");
        }
        if (!repository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
    }

    public static User updateUserData(User oldUser, User newUser) {
        User finalUser = oldUser.clone();
        if (null != newUser.getAddress()) {
            finalUser.setAddress(newUser.getAddress());
        }
        if (null != newUser.getUserName()) {
            finalUser.setUserName(newUser.getUserName());
        }
        return finalUser;
    }
}
