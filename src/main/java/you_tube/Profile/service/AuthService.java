package you_tube.Profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.ExceptionHandler.AppBadException;
import you_tube.History.service.EmailSendingService;
import you_tube.Profile.dto.AuthDTO;
import you_tube.Profile.dto.ProfileDTO;
import you_tube.Profile.dto.RegistrationDTO;
import you_tube.Profile.entity.ProfileEntity;
import you_tube.Profile.enums.ProfileRole;
import you_tube.Profile.enums.ProfileStatus;
import you_tube.Profile.repository.ProfileRepository;
import you_tube.Util.JwtUtil;
import you_tube.Util.MD5Util;

import java.util.Optional;

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
        profileEntity.setPassword(MD5Util.md5(registration.getPassword()));
        profileEntity.setRole(ProfileRole.USER);
        profileEntity.setStatus(ProfileStatus.IN_REGISTRATION);
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
        if (byEmail == null && !byEmail.getPassword().equals(MD5Util.md5(dto.getPassword()))) {
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
}
