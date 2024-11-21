package you_tube.you_tube.Profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.you_tube.History.service.EmailSendingService;
import you_tube.you_tube.Profile.dto.RegistrationDTO;
import you_tube.you_tube.Profile.entity.ProfileEntity;
import you_tube.you_tube.Profile.enums.ProfileRole;
import you_tube.you_tube.Profile.enums.ProfileStatus;
import you_tube.you_tube.Profile.repository.ProfileRepository;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailSendingService emailSendingService;

    public String create(RegistrationDTO registration) {
        ProfileEntity byEmail = profileRepository.findByEmail(registration.getEmail());
        if (byEmail != null) {
            throw new RuntimeException("Email already exists");
        }
        ProfileEntity profileEntity = ConvertDTOToEntity(registration);
        StringBuilder sb = new StringBuilder();
        sb.append("<h1 style=\"text-align: center\"> Complete Registration</h1>");
        sb.append("<br>");
        sb.append("<p>Click the link below to complete registration</p>\n");
        sb.append("<p><a style=\"padding: 5px; background-color: indianred; color: white\"  href=\"http://localhost:8080/api/auth/registration/confirm/")
                .append(profileEntity.getId()).append("\" target=\"_blank\">Click Th</a></p>\n");
        emailSendingService.sendSimpleMessage(registration.getEmail(), "Complite Registration", sb.toString());
        emailSendingService.sendMimeMessage(registration.getEmail(), "Tasdiqlash", sb.toString());
        return "Confirm sent email";
    }

    private ProfileEntity ConvertDTOToEntity(RegistrationDTO registration) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setEmail(registration.getEmail());
        profileEntity.setName(registration.getName());
        profileEntity.setSurname(registration.getSurname());
        profileEntity.setPassword(registration.getPassword());
        profileEntity.setRole(ProfileRole.USER);
        profileEntity.setStatus(ProfileStatus.IN_REGISTRATION);
        profileRepository.save(profileEntity);
        return profileEntity;
    }

    public String registrationConfirm(Integer id) {
        return null;
    }
}
