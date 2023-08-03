package com.example.MutsaSNS.dtos;

import com.example.MutsaSNS.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private String email;
    private String phone;
    private boolean deleted;

    public static CustomUserDetails fromEntity(UserEntity entity) {
        CustomUserDetails details = new CustomUserDetails();
        details.username = entity.getUsername();
        details.password = entity.getPassword();
        details.email = entity.getEmail();
        details.phone = entity.getPhone();
        details.deleted = entity.getDeleted();
        return details;
    }

    public UserEntity newEntity() {
        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setEmail(email);
        entity.setPhone(phone);
        entity.setDeleted(deleted);
        return entity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.deleted;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return !this.deleted;
    }
}
