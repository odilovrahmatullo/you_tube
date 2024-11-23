package you_tube.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import you_tube.exceptionHandler.AppBadException;
import you_tube.history.service.EmailSendingService;
import you_tube.profile.dto.AuthDTO;
import you_tube.profile.dto.ProfileDTO;
import you_tube.profile.dto.RegistrationDTO;
import you_tube.profile.entity.ProfileEntity;
import you_tube.profile.enums.ProfileRole;
import you_tube.profile.enums.ProfileStatus;
import you_tube.profile.repository.ProfileRepository;
import you_tube.utils.JwtUtil;
import you_tube.utils.MD5Util;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailSendingService emailSendingService;
    @Value("${server.domain}")
    private String domainName;

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
        emailSendingService.sendSimpleMessage(registration.getEmail(), "Complite Registration", getConfirmationButton(profileEntity.getId()));
        emailSendingService.sendMimeMessage(registration.getEmail(), "Tasdiqlash",getConfirmationButton(profileEntity.getId()));
        return "Confirm sent email";
    }

    private ProfileEntity ConvertDTOToEntity(RegistrationDTO registration) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setEmail(registration.getEmail());
        profileEntity.setName(registration.getName());
        profileEntity.setSurname(registration.getSurname());
        profileEntity.setPassword(MD5Util.md5(registration.getPassword()));
        profileEntity.setRole(ProfileRole.ROLE_USER);
        profileEntity.setStatus(ProfileStatus.IN_REGISTRATION);
        profileEntity.setVisible(Boolean.TRUE);
        profileEntity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(profileEntity);
        return profileEntity;
    }

    public String registrationConfirm(Integer id) {
        Optional<ProfileEntity> byId = profileRepository.findById(id);
        if (byId.isPresent()) {
            ProfileEntity profileEntity = byId.get();
            profileEntity.setStatus(ProfileStatus.ACTIVE);
            profileRepository.save(profileEntity);
            return "Confirm sent email";
        }
        throw new AppBadException("User not found");
    }

    public ProfileDTO login(AuthDTO dto) {
        ProfileEntity byEmail = profileRepository.findByEmail(dto.getEmail());
        if (byEmail == null){
            throw new AppBadException("email not found");
        }
        if (!byEmail.getPassword().equals(MD5Util.md5(dto.getPassword()))) {
            throw new AppBadException("email or password incorrect");
        }
        if (byEmail.getStatus() != ProfileStatus.ACTIVE) {
            throw new AppBadException("email not active");
        }
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(byEmail.getId());
        profileDTO.setName(byEmail.getName());
        profileDTO.setEmail(byEmail.getEmail());
        profileDTO.setSurname(byEmail.getSurname());

        String token = JwtUtil.encode(byEmail.getEmail(),byEmail.getRole().toString());
        profileDTO.setJwtToken(token);
        String RefreshToken = JwtUtil.generateRefreshToken(byEmail.getEmail());
        profileDTO.setRefreshToken(RefreshToken);

        return profileDTO;
    }


    public String getConfirmationButton(Integer id) {
        return "<html><body>" +
                "<p>Click below to confirm your registration:</p>" +
                "<a href=\"" + domainName + "/api/auth/registration/confirm/" + id + "\" " +
                "style=\"display: inline-block; padding: 10px 20px; font-size: 16px; background-color:" +
                " #007bff; color: white; text-decoration: none; border-radius: 5px; border: " +
                "1px solid #007bff;\">Confirm</a> </body></html>";

    }
}
