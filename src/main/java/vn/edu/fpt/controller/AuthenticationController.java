package vn.edu.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.configuration.security.SecurityUser;
import vn.edu.fpt.configuration.security.TokenProvider;
import vn.edu.fpt.dto.AuthToken;

@CrossOrigin( origins = "*", maxAge = 3600 )
@RestController
@RequestMapping( "/token" )
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping( value = "/generate-token" )
    public ResponseEntity register( @RequestBody SecurityUser loginUser ) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication( authentication );
        final String token = tokenProvider.generateToken( authentication );
        return ResponseEntity.ok( new AuthToken( token ) );
    }
}
