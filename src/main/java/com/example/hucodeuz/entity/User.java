package com.example.hucodeuz.entity;
import com.example.hucodeuz.entity.templete.AbsEntity;
import com.example.hucodeuz.enums.PositionType;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.*;

/**
 * @author "Husniddin Ulachov"
 * @created 7:35 PM on 7/12/2022
 * @project Edu-Center
 */
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class User extends AbsEntity implements UserDetails {
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    private String fullName, phone;

    private Double salary;

    private boolean active = true;

    @ManyToOne
    private Filial filial;

    @Enumerated
    private PositionType positionType;

    private String password;
    private boolean accountNonExpired = true ;
    private boolean accountNonLocked = true;
    private boolean credentialNonExpired = true;
    private boolean enabled = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : this.roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(Set<Role> roles, String fullName, String phone, String password, boolean enabled) {
        this.roles = roles;
        this.fullName = fullName;
        this.phone = phone;
        this.password = password;
        this.enabled = enabled;
    }
}
