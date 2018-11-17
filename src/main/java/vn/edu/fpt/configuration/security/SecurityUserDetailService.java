package vn.edu.fpt.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.fpt.entity.User;
import vn.edu.fpt.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class SecurityUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public SecurityUserDetailService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        User user = userRepository.findByUsername( username );
        if ( user != null ) {
            return new SecurityUser(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles().stream().map( role -> new SimpleGrantedAuthority( role.getRoleName() ) ).collect( Collectors.toSet() ),
                    user.isEnable(),
                    user );
        }
        throw new UsernameNotFoundException( username );
    }
}
