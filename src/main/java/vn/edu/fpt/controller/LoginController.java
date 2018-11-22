package vn.edu.fpt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin( origins = "*", maxAge = 3600 )
@RestController
public class LoginController {

    @GetMapping( "/dang-nhap" )
    public ModelAndView getLoginPage( ModelAndView mv ) {
        mv.setViewName( "dang-nhap" );
        return mv;
    }
}
