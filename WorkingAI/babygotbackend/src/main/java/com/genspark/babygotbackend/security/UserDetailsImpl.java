package com.genspark.babygotbackend.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

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
