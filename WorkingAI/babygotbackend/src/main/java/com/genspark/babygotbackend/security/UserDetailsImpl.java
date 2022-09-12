package com.genspark.babygotbackend.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/*  The UserDetails interface has 7 self-explanatory methods that needs to be implemented.

    String getUsername() returns the username used to authenticate the user. Since we are using email to authenticate
        the user, it is set to return email if it ever gets called.  String getEmail() added for the same purpose.
    String getPassword() returns the password used to authenticate the user.
    Collection<GrantedAuthority> getAuthorities() returns the authorities and roles granted to the user.

    Note that roles and authorities are stored together in one container and have the same type named
    GrantedAuthority (interface). To convert a String role/authority to GrantedAuthority we can use the
    SimpleGrantedAuthority class that implements the interface and has a constructor that receives a
    role/authority of the String type, for example, new SimpleGrantedAuthority("ROLE_USER").

    Also, now it should be more clear why we prefix a role with ROLE_. For the framework the
    difference between a role and an authority is minimal and they are stored together. To tell
    them from one another we prefix a role with ROLE_. If there is no prefix, we're dealing with
    an authority.

    All remaining methods of the UserDetails interface enable or disable the account for different reasons:

    boolean isEnabled() indicates whether the user is enabled or disabled.
    boolean isAccountNonExpired() indicates whether the user's account has expired.
    boolean isAccountNonLocked() indicates whether the user is locked or unlocked.
    boolean isCredentialsNonExpired() indicates whether the user's credentials (password) have expired.

    Not all applications have accounts that expire or get locked under certain conditions. If you don't need
    to implement these functionalities in your application, you can simply make these four methods return true,
    which means that the users are active.

    Now let's provide our implementation of the UserDetails interface. As we've mentioned before, the class that
    implements the interface (UserDetailsImpl) will be used to store and transfer core user information from the
    user store to Spring Security. In the UserRepository the info about one user is stored in a User object.
    Now we need to provide a converter from User to UserDetails. We will do it with a custom builder that will
    construct UserDetails object from User object information and store its content.

    Big applications can have a lot of roles/authorities, and all of them should be converted to
    GrantedAuthority.
*/
public class UserDetailsImpl implements UserDetails {

    private String email;
    private String password;
    private Collection<GrantedAuthority> authorities;
    private boolean enabled;

    public UserDetailsImpl(String email,
                           String password,
                           Collection<? extends GrantedAuthority> authorities,
                           boolean enabled
    ) {

        if (((email == null) || "".equals(email)) || (password == null)) {
            throw new IllegalArgumentException(
                    "Cannot pass null or empty values to constructor");
        }

        this.email = email;
        this.password = password;
        this.authorities = null;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public static final class CustomUserBuilder {

        private String email;
        private String password;
        private Collection<GrantedAuthority> authorities;
        private boolean enabled;

        private CustomUserBuilder() {
        }

        public static CustomUserBuilder aCustomUser() {
            return new CustomUserBuilder();
        }

        public CustomUserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomUserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public CustomUserBuilder withAuthorities(Collection<GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public CustomUserBuilder withEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserDetailsImpl build() {
            UserDetailsImpl userDetailsImpl = new UserDetailsImpl(email, password, authorities,
                    enabled);
            userDetailsImpl.authorities = this.authorities;
            return userDetailsImpl;
        }
    }
}
