package com.example.demo.features.user.entity;

import com.example.demo.features.mailSystem.entity.Participation;
import com.example.demo.features.user.enums.Roles;
import com.example.demo.features.user.enums.UsagePurpose;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class, property = "id"
)
public class UserEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  private String password;

  @Column(unique = true)
  @JsonIgnore
  private String email;

  @Column(name = "username")
  private String username;

  @Column(name = "full_name")
  private String fullName;

  @Enumerated(EnumType.STRING)
  @JsonIgnore
  private Roles role;

  @Column(name = "image_url")
  private String imageUrl;

  @Enumerated(EnumType.STRING)
  @JsonIgnore
  @Column(name = "usage_purpose")
  private UsagePurpose usagePurpose;

  @JsonIgnore
  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<Participation> participations;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  @JsonIgnore
  private Timestamp updatedAt;

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return "UserEntity [id=" + id + ", "
        + "password=" + password + ", "
        + "email=" + email + ", "
        + "username=" + username + ", "
        + "role=" + role + ", "
        + "imageUrl=" + imageUrl + ", "
        + "createdAt=" + createdAt + ", "
        + "updatedAt=" + updatedAt
        + "]";
  }
}
