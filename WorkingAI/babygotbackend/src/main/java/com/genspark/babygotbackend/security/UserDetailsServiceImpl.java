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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/*  This interface has only one method that we need to override:

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException receives a username and returns
    a UserDetails object.  Overridden as String email for clarity.

    This method will be used by Spring Security when someone tries to authenticate. In the method, we need to retrieve
    user data by email from the storage and convert that data to UserDetails. If the user with a specified username
    is not found, we throw UsernameNotFoundException.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user;
        Optional<User> o = this.userRepository.findUserByEmail(email.toLowerCase());

        if (o.isEmpty()){
            throw new UsernameNotFoundException(email);
        }

        user = o.get();

        UserDetailsImpl userDetails = UserDetailsImpl.CustomUserBuilder.aCustomUser()
                .withEmail(user.getEmail())
                .withPassword(user.getPassword())
                .withAuthorities(getAuthorities(user))  //with multiple roles, custom builder necessary
                .withEnabled(user.isEnabled())
                .build();

        return userDetails;
    }
/*  We injected UserRepository to use it in the loadUserByUsername method to retrieve user info.
    Note that the class is annotated with the @Service annotation.

    Why do we retrieve user data just by username without checking the password or authorities/roles? The answer is
    they will be checked automatically by Spring Security. Our only task is to return the UserDetails object that
    stores user info. It doesn't matter how we convert user data to UserDetails, and where our users are stored.
 */

    private Collection<GrantedAuthority> getAuthorities(User user){
        Set<Group> userGroups = user.getUserGroups();
        Collection<GrantedAuthority> authorities = new ArrayList<>(userGroups.size());
        for(Group userGroup : userGroups){
            authorities.add(new SimpleGrantedAuthority(userGroup.getName()));
        }

        return authorities;
    }
}
