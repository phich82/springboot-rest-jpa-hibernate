package com.maison.demo.securities;

import com.maison.demo.models.Role;
import com.maison.demo.models.User;
import com.maison.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            User user = userService.findByEmail(email);
            System.out.println("auth => " + user);

            if (user != null && user.getUserId() > 0 && passwordEncoder.matches(password, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(email, null, getGrantedAuthorities(user.getRole()));
            }
            throw new BadCredentialsException("Invalid credentials!");
        } catch (Exception e) {
            System.out.println("auth => error");
            throw new BadCredentialsException(String.format("Invalid credentials: email [%s] not exists!", email));
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<GrantedAuthority> getGrantedAuthorities(Role role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        return grantedAuthorities;
    }
}
