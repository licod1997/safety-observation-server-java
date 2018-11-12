package vn.edu.fpt.service;

import vn.edu.fpt.entity.User;

import java.util.List;

public interface UserService {

    User createUser(String username, String password);

    User createUserByAdmin(String username, String password,boolean isEnable,Integer roleId);

    User editUser(Long id,String password);

    User editUserByAdministrator(Long id, String password,Boolean isEnable,Integer roleId);

    User disableUser(Long id);

    User findByUserId(Long userId);

    List<User>findAllByUsernameContains(String username);
}
