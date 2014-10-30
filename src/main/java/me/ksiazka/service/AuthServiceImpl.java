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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("authService")
public class AuthServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        me.ksiazka.model.User user = userDao.findUserByEmail(email);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

        return buildUserForAuth(user, authorities);
    }

    //Zamienia User z modelu na User z org.springframework.security.core.userdetails.User
    private User buildUserForAuth(me.ksiazka.model.User user, List<GrantedAuthority> authorities){
        return new User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<UserRole> userRoles){

        Set<GrantedAuthority> setAuth = new HashSet<GrantedAuthority>();

        for(UserRole userRole : userRoles){
            setAuth.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuth);

        return result;
    }
}
