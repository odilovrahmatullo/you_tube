package you_tube.you_tube.Profile.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import you_tube.Profile.enums.ProfileRole;
import you_tube.Profile.enums.ProfileStatus;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "photo")
    private String photo;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ProfileRole role;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;
    @Column(name = "visible")
    private Boolean visible ;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
