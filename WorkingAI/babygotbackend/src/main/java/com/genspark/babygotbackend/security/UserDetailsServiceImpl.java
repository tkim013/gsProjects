package com.genspark.babygotbackend.security;

import com.genspark.babygotbackend.entity.Group;
import com.genspark.babygotbackend.entity.User;
import com.genspark.babygotbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user;
        Optional<User> o = this.userRepository.findUserByEmail(email.toLowerCase());

        if (o.isEmpty()){

            throw new UsernameNotFoundException(email);
        }

        user = o.get();

        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(getAuthorities(user))
                .enabled(user.isEnabled())
                .build();

        return userDetails;
    }

    private Collection<GrantedAuthority> getAuthorities(User user){
        Set<Group> userGroups = user.getUserGroups();
        Collection<GrantedAuthority> authorities = new ArrayList<>(userGroups.size());
        for(Group userGroup : userGroups){
            authorities.add(new SimpleGrantedAuthority(userGroup.getName()));
        }

        return authorities;
    }
}
