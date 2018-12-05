package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.fpt.configuration.security.SecurityUser;
import vn.edu.fpt.dto.UserAdministrationDTO;
import vn.edu.fpt.entity.Role;
import vn.edu.fpt.entity.User;
import vn.edu.fpt.repository.RoleRepository;
import vn.edu.fpt.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize( "hasRole('ROLE_ADMIN')" )
    @PostMapping( "/tao-tai-khoan" )
    public ResponseEntity createUserByAdmin( @RequestParam( "username" ) String username,
                                             @RequestParam( "password" ) String password) {
        User user = userService.findByUsername( username );
        if ( user != null ) {
            return ResponseEntity.badRequest().body( "Tài khoản đã tồn tại." );
        }
        user = userService.createUserByAdmin( username, password, true );
        if ( user != null ) {
            return ResponseEntity.ok().body( "Đã tạo tài khoản thành công." );
        }
        return ResponseEntity.badRequest().body( "Có lỗi đã xảy ra." );
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

    @PreAuthorize( "hasRole('ROLE_ADMIN')" )
    @PostMapping( "/cap-nhat-tai-khoan" )
    public ResponseEntity updateUser( @RequestParam("userId") Long userId,
                                      @RequestParam("enable") Boolean enable) {
        User user = userService.updateUserByAdmin( userId, enable );
        if (user != null) {
            UserAdministrationDTO userAdministrationDTO = new UserAdministrationDTO();
            userAdministrationDTO.setUserId( user.getId() );
            userAdministrationDTO.setEnable( user.isEnable() );
            return ResponseEntity.ok( userAdministrationDTO );
        }
        return ResponseEntity.badRequest().build();
    }
}
