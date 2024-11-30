package you_tube.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import you_tube.profile.entity.ProfileEntity;
import you_tube.profile.enums.ProfileRole;
import you_tube.profile.enums.ProfileStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    @Getter
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private ProfileRole role;
    private ProfileStatus status;
    private Boolean visible;
    private LocalDateTime createdDate;
    private String photo;

    public CustomUserDetails(ProfileEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.role = entity.getRole();
        this.status = entity.getStatus();
        this.visible = entity.getVisible();
        this.createdDate = entity.getCreatedDate();
        this.photo = entity.getPhoto();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role.name()));
        return roles;
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
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status == ProfileStatus.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
