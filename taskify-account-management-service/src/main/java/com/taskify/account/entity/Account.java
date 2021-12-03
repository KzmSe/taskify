package com.taskify.account.entity;

import com.taskify.account.entity.enums.AccountRole;
import com.taskify.account.entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private AccountRole role;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<AccountOrganizationCollection> organizationCollection = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(this.role);
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status.equals(AccountStatus.BLOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status.equals(AccountStatus.ACTIVE);
    }
}
