package vn.edu.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fpt.entity.Role;
import vn.edu.fpt.entity.User;
import vn.edu.fpt.repository.RoleRepository;
import vn.edu.fpt.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public User createUserByAdmin( String username, String password, boolean isEnable ) {
        Set<Role> setRole = new HashSet<Role>();
        Role role = roleRepository.findByRoleName( "ROLE_USER" );
        setRole.add( role );

        User user = new User( username, password, isEnable, setRole );
        userRepository.saveAndFlush( user );
        return user;
    }

    @Override
    public User findByUserId( Long userId ) {
        return userRepository.findById( userId );
    }

    @Override
    public List<User> getAllUser() {
        Role role = roleRepository.findByRoleName( "ROLE_USER" );
        if (role != null) {
            Set<Role> roleSet = new HashSet<>();
            roleSet.add( role );
            List<User> resultList = userRepository.findByRolesIn( roleSet );
            return resultList;
        }
        return null;
    }

    @Override
    public User findByUsername( String username ) {
        return userRepository.findByUsername( username );
    }

    @Transactional
    @Override
    public User updateUserByAdmin( Long userId, Boolean enable ) {
        User user = userRepository.findById( userId );
        if ( user != null ) {
            user.setEnable( enable );
            return userRepository.save( user );
        }
        return null;
    }

    @Override
    public User updatePersonalInfo( String username, String password ) {
        User user = userRepository.findByUsername( username );
        if(user != null){
            user.setPassword( password );
            return userRepository.saveAndFlush( user );
        }

        return null;
    }

}
