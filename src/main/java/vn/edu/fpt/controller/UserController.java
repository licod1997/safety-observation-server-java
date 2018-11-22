package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.configuration.security.SecurityUser;
import vn.edu.fpt.entity.Role;
import vn.edu.fpt.entity.User;
import vn.edu.fpt.repository.RoleRepository;
import vn.edu.fpt.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping( "/create-user" )
    public ResponseEntity createUser( @RequestParam( "username" ) String username,
                                      @RequestParam( "password" ) String password ) {
        User user = userService.createUser( username, password );
        if ( user != null ) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }

    @PreAuthorize( "hasRole('ROLE_ADMIN')" )
    @PostMapping( "/tao-tai-khoan" )
    public ResponseEntity createUserByAdmin( @RequestParam( "username" ) String username,
                                             @RequestParam( "password" ) String password,
                                             @RequestParam( "roleId" ) Integer roleId ) {
        User user = userService.findByUsername( username );
        if ( user != null ) {
            return ResponseEntity.badRequest().body( "Tài khoản đã tồn tại." );
        }
        user = userService.createUserByAdmin( username, password, true, roleId );
        if ( user != null ) {
            return ResponseEntity.ok().body("Đã tạo tài khoản thành công.");
        }
        return ResponseEntity.badRequest().body( "Có lỗi đã xảy ra." );
    }

    @PostMapping( "/edit-user" )
    public ResponseEntity editUser( @RequestParam( "userId" ) long userId,
                                    @RequestParam( "password" ) String password ) {
        User user = userService.editUser( userId, password );
        if ( user != null ) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping( "/edit-user-by-admin" )
    public String editUserByAdmin( @RequestParam( "userId" ) Long userId,
                                   @RequestParam( "password" ) String password,
                                   @RequestParam( "isEnable" ) boolean isEnable,
                                   @RequestParam( "roleId" ) Integer roleId,
                                   @RequestParam( "rePassword" ) String rePassword ) {

        if ( !password.trim().equals( rePassword.trim() ) ) {
            return "rePass_not_match";
        }
        User user = userService.editUserByAdministrator( userId, password, isEnable, roleId );
        if ( user == null ) {
            return "edit_failed";
        }
        return "edit_successfullly";
    }

    @GetMapping( "/ket-qua-tim-kiem" )
    public ModelAndView searchByUsername( @RequestParam( "usernameSearch" ) String username, ModelAndView mv ) {

        List<User> resultSearchList = userService.searchByUsername( username.trim() );
        if ( resultSearchList.size() > 0 ) {
            mv.addObject( "resultSearchList", resultSearchList );
        }
        mv.setViewName( "ket-qua-tim-kiem" );
        return mv;

    }

    @PreAuthorize( "hasRole('ROLE_ADMIN')" )
    @GetMapping( "/nguoi-dung" )
    public ModelAndView getManagUserPage( ModelAndView mv,
                                          Authentication auth ) {
        if ( auth != null ) {
            SecurityUser securityUser = (SecurityUser) auth.getPrincipal();
            mv.addObject( "loggedInUser", securityUser.getUsername() );
        }
        List<User> userList = userService.getAllUser();
        if ( userList.size() > 0 ) {
            mv.addObject( "userList", userList );
            mv.addObject( "firstId", userList.get( 0 ).getId() );
            mv.addObject( "lastId", userList.get( userList.size() - 1 ).getId() );
            mv.setViewName( "nguoi-dung" );
        }

        return mv;
    }

    @GetMapping( "/disable-user" )
    public ResponseEntity disableUser( @RequestParam( "userId" ) Long userId ) {

        User user = userService.disableUser( userId );
        if ( user != null ) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();

    }

    @Autowired
    RoleRepository roleRepository;

    @GetMapping( "/chi-tiet-user" )
    public ModelAndView getDetailUser( @RequestParam( "userId" ) Long userId, ModelAndView mv ) {
        User user = userService.findByUserId( userId );
        List<Role> roleList = roleRepository.findAll();
        mv.addObject( "user", user );
        mv.addObject( "roleList", roleList );
        mv.setViewName( "chi-tiet-user" );
        return mv;
    }

    @PreAuthorize( "hasRole('ROLE_ADMIN')" )
    @GetMapping( "/tao-tai-khoan" )
    public ModelAndView getCreatUserPage( ModelAndView mv,
                                          Authentication auth ) {
        if ( auth != null ) {
            SecurityUser securityUser = (SecurityUser) auth.getPrincipal();
            mv.addObject( "loggedInUser", securityUser.getUsername() );
        }
        List<Role> roleList = roleRepository.findAll();
        mv.addObject( "roleList", roleList );
        mv.setViewName( "tao-tai-khoan" );
        return mv;
    }
}
