package com.projects.api_notes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "login")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Login implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "id_login"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<RoleModel> roleModels;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleModels.stream().map(role
                -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());
    }

}