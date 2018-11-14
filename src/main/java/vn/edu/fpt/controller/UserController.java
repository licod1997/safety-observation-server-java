package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createUser( @RequestParam("username") String username,
                                              @RequestParam("password") String password) {
        User user = userService.createUser( username,password );
        if (user!= null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/create-user-by-admin")
    public String createUserByAdmin( @RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("isEnable") boolean isEnable,
                                   @RequestParam("roleId") Integer roleId,
                                   @RequestParam("rePassword") String rePassword) {


        User user = userService.findByUsername( username );
        if(user!= null){
            return "duplicate_username";
        }else if(!password.trim().equals( rePassword.trim() )){
            return "rePass_not_match";
        }else{
            User user2 = userService.createUserByAdmin( username,password,isEnable,roleId );;
            if (user2 != null){
                return "create_successfully";
            }
            return "create_failed";
        }


    }

    @PostMapping("/edit-user")
    public ResponseEntity editUser( @RequestParam("userId") long userId,
                                   @RequestParam("password") String password ) {
        User user= userService.editUser( userId,password );
        if (user!= null){
           return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/edit-user-by-admin")
    public ResponseEntity editUserByAdmin( @RequestParam("userId") Long userId,
                                           @RequestParam("password") String password,
                                           @RequestParam("isEnable") boolean isEnable,
                                           @RequestParam("roleId") Integer roleId) {

        User user = userService.editUserByAdministrator( userId,password,isEnable,roleId );
        if (user!= null){
           return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/ket-qua-tim-kiem")
    public ModelAndView searchByUsername (@RequestParam("usernameSearch") String username, ModelAndView mv ) {

        List<User> resultSearchList = userService.searchByUsername( username.trim() );
    if(resultSearchList.size() > 0){
        mv.addObject( "resultSearchList",resultSearchList );
    }
        mv.setViewName( "ket-qua-tim-kiem" );
        return mv;

    }

    @GetMapping("/quan-ly-user" )
    public ModelAndView getManagUserPage( ModelAndView mv ) {
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
    public ResponseEntity disableUser (@RequestParam("userId") Long userId) {

        User user = userService.disableUser( userId );
        if (user!= null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/chi-tiet-user" )
    public ModelAndView getDetailUser(@RequestParam("userId")Long userId, ModelAndView mv ) {
        User user = userService.findByUserId(userId);
        List<Role> roleList = roleRepository.findAll();
        mv.addObject( "user", user );
        mv.addObject( "roleList",roleList);
        mv.setViewName( "chi-tiet-user" );
        return mv;
    }
    @GetMapping("/tao-moi-user" )
    public ModelAndView getCreatUserPage(ModelAndView mv ) {
        List<Role> roleList = roleRepository.findAll();
        mv.addObject( "roleList",roleList);
        mv.setViewName( "tao-moi-user" );
        return mv;
    }
}
