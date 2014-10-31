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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("authService")
public class AuthServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        me.ksiazka.model.User user = userService.findUserByEmail(email);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

        return buildUserForAuth(user, authorities);
    }

    //Zamienia User z modelu na User z org.springframework.security.core.userdetails.User
    private User buildUserForAuth(me.ksiazka.model.User user, List<GrantedAuthority> authorities){
        return new User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<UserRole> userRoles){

        List<GrantedAuthority> setAuth = new ArrayList<GrantedAuthority>();

        //Przepisanie wszystkich roli uzytkownika.
        for(UserRole userRole : userRoles){
            setAuth.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return setAuth;
    }

    private String hashPassword(String pass){
        String hashedPass = BCrypt.hashpw(pass, BCrypt.gensalt());
        return hashedPass;
    }

    public void saveUser(me.ksiazka.model.User user){
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        user.setPassword(hashPassword(user.getPassword()));
        userRole.setUser(user);
        user.getRoles().add(userRole);
        userService.save(user);
    }
}
