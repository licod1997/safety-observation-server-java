package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.entity.Role;
import vn.edu.fpt.entity.User;
import vn.edu.fpt.repository.RoleRepository;
import vn.edu.fpt.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/create-user")
    public User createUser( @RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        User user = userService.createUser( username,password );
        return user;

    }

    @PostMapping("/create-user-by-admin")
    public User createUserByAdmin( @RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("isEnable") boolean isEnable,
                                   @RequestParam("roleId") Integer roleId) {

        User user = userService.createUserByAdmin( username,password,isEnable,roleId );
        return user;

    }

    @PostMapping("/edit-user")
    public User editUser( @RequestParam("userId") long userId,
                                   @RequestParam("password") String password) {
        return userService.editUser( userId,password );

    }

    @PostMapping("/edit-user-by-admin")
    public User editUserByAdmin(    @RequestParam("userId") Long userId,
                                   @RequestParam("password") String password,
                                   @RequestParam("isEnable") boolean isEnable,
                                   @RequestParam("roleId") Integer roleId) {

        User user = userService.editUserByAdministrator( userId,password,isEnable,roleId );
        return user;

    }

    @GetMapping("/search-by-username")
    public List<User> searchByLikeUsername (@RequestParam("usernameSearch") String username) {

        List<User> resultList = userService.searchByUsername( username );
        return resultList;

    }

    @GetMapping("/quan-ly-user" )
    public ModelAndView getFeedbackPage( ModelAndView mv ) {
        List<User> userList = userService.getAllUserIsEnable();

        if(userList.size() > 0){
            mv.addObject( "userList", userList );
            mv.addObject( "firstId", userList.get( 0 ).getId() );
            mv.addObject( "lastId", userList.get( userList.size() - 1 ).getId() );
            mv.setViewName( "quan-ly-user" );
        }

        return mv;
    }

    @GetMapping("/disable-user")
    public User disableUser (@RequestParam("userId") Long userId) {

        User user = userService.disableUser( userId );
        return user;

    }

}
