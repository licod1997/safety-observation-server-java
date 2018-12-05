package vn.edu.fpt.service;

import vn.edu.fpt.entity.User;

import java.util.List;

public interface UserService {

    User createUserByAdmin( String username, String password, boolean isEnable );

    User findByUserId( Long userId );

    List<User> getAllUser();

    User findByUsername( String username );

    User updateUserByAdmin( Long userId, Boolean enable );
}
