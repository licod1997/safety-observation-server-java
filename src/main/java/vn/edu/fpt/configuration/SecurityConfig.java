package vn.edu.fpt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import vn.edu.fpt.configuration.security.JwtAuthenticationEntryPoint;
import vn.edu.fpt.configuration.security.JwtAuthenticationFilter;
import vn.edu.fpt.configuration.security.SecurityUserDetailService;

import static vn.edu.fpt.configuration.security.Constants.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    @Autowired
    private SecurityUserDetailService securityUserDetailService;

    @Autowired
    public void globalUserDetails( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( securityUserDetailService );
    }

    @Override
    public void configure( WebSecurity web ) throws Exception {
        web.ignoring().antMatchers( "/", "/resources/**", "/static/**", "/*.html", "/**/*.html", "/**/*.css",
                "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.gif", "/**/*.svg", "/**/*.ico", "/**/*.ttf", "/**/*.woff", "/**/*.woff2" );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers( GENERATE_TOKEN_URL, SIGN_UP_URL, SIGN_IN_URL, CAMERA_URL ).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint( unauthorizedHandler ).and()
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )
                .and()
                .addFilterBefore( authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class )
                .logout()
                .logoutRequestMatcher( new AntPathRequestMatcher( "/dang-xuat" ) )
                .logoutSuccessUrl( "/dang-nhap" );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

}
