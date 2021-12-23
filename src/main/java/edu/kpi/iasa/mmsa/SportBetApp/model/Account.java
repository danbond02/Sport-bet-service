package edu.kpi.iasa.mmsa.SportBetApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
@Builder
@AllArgsConstructor
public class Account {
    public static final Account ACCOUNT = new Account("anonymous");

    private Long id;
    private String username;
    private String e_mail;
    private String password;
    private String status_;
    private Collection<Role> roles = new HashSet<>();

    public Account() {}

    public Account(String username) { this.username = username; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Size(
            min = 6,
            max = 50,
            message = "validation.text.error.from.six.to.fifty")
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "validation.text.error.required.field")
    @Size(
            min = 6,
            max = 50,
            message = "validation.text.error.from.six.to.fifty")
    @Column(name = "e_mail")
    public String getEmail() {
        return e_mail;
    }

    public void setEmail(String email) {
        this.e_mail = email;
    }

    @ManyToMany
    @JoinTable(
            name = "account_role",
            joinColumns = {
                    @JoinColumn(
                            name = "account_id",
                            referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "role_id",
                            referencedColumnName = "id")})
    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(getRoles());
        return authorities;
    }

    public boolean hasRole(String role) {
        for (Role r : getRoles()) {
            if (r.getName().equals(role)) return true;
        }
        return false;
    }

    public String toString() {
        return username;
    }

    public String getStatus_() {
        return status_;
    }

    public void setStatus_(String status_) {
        this.status_ = status_;
    }
}
