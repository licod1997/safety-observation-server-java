package vn.edu.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.entity.Role;
import vn.edu.fpt.entity.User;
import vn.edu.fpt.repository.RoleRepository;
import vn.edu.fpt.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    @Override
    public User createUser( String username, String password ) {
        User user = new User( username,password,true );
        userRepository.saveAndFlush( user );
        return user;
    }

    @Override
    public User createUserByAdmin( String username, String password, boolean isEnable, Integer roleId ) {
      Set<Role> setRole = new HashSet<Role>(  );
      Role role = roleRepository.findById(roleId);
      setRole.add(role);

        User user = new User( username,password,isEnable, setRole);
        userRepository.saveAndFlush( user );
        return user;
    }

    @Override
    public User editUser( Long id, String password ) {
        User user = userRepository.findById( id );
        if(user!= null){
            user.setPassword( password );
            return userRepository.saveAndFlush( user );
        }
        return null;
    }

    @Override
    public User editUserByAdministrator( Long id, String password, Boolean isEnable, Integer roleId ) {
        User user = userRepository.findById( id );
        if(user!= null){
            Set<Role> setRole = new HashSet<Role>(  );
            Role role = roleRepository.findById(roleId);
            setRole.add(role);

            user.setEnable( isEnable );
            user.setPassword( password );
           user.setRoles( setRole );
            return userRepository.saveAndFlush( user );
        }
        return null;
    }

    @Override
    public User disableUser( Long id ) {
       User user= userRepository.findById( id );
       user.setEnable( false );
       return userRepository.saveAndFlush( user );
    }

    @Override
    public User findByUserId( Long userId ) {
       return userRepository.findById( userId );
    }

    @Override
    public List<User> searchByUsername( String username ) {
        return userRepository.findAllByUsernameContaining( username );
    }

}
