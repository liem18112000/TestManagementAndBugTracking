package com.pm.app.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pm.app.user.profile.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

import static java.util.Collections.singletonList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails{

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "provider")
    private String provider = "default";

    @Column(name = "enabled")
    private Boolean enabled = true;

    @Column(name = "locked")
    private Boolean locked = false;

    @Column(name = "account_expired")
    private Boolean accountExpired = false;

    @Column(name = "credential_expired")
    private Boolean credentialExpired = false;

    @Column(name = "updated_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("User");
        return singletonList(authority);
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
