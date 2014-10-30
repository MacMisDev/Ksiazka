package me.ksiazka.service;

import me.ksiazka.dao.UserDAO;
import me.ksiazka.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("authService")
public class AuthDetailsImpl implements UserDetailsService{

    @Autowired
    private UserDAO userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return null;
    }

    //Zamienia User z modelu na User z org.springframework.security.core.userdetails.User
    private User buildUserForAuth(me.ksiazka.model.User user, List<GrantedAuthority> authorities){
        return new User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles){

        Set<GrantedAuthority> setAuth = new HashSet<GrantedAuthority>();

        for(UserRole userRole : userRoles){
            //setAuth.add(new SimpleGrantedAuthority(userRole.))
        }
    }
}
