package you_tube.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import you_tube.exceptionhandler.AppBadException;
import you_tube.history.service.EmailSendingService;
import you_tube.profile.dto.CreateProfile;
import you_tube.profile.dto.JwtDTO;
import you_tube.profile.dto.ProfileDTO;
import you_tube.profile.enums.ProfileStatus;
import you_tube.utils.JwtUtil;
import you_tube.profile.dto.UpdateProfileDTO;
import you_tube.profile.entity.ProfileEntity;
import you_tube.profile.repository.ProfileRepository;


import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailSendingService emailSendingService;

    private String updateEmail ;
    @Value("${server.domain}")
    private String domainName;

    public ProfileDTO create(CreateProfile profile) {
        ProfileEntity byEmail = profileRepository.findByEmail(profile.getEmail());
        if (byEmail == null && !byEmail.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadException("Email does not exist");
        }
        byEmail.setRole(profile.getRole());
        profileRepository.save(byEmail);
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setEmail(byEmail.getEmail());
        profileDTO.setRole(byEmail.getRole());
        return profileDTO;
    }


    public String updateEmailConfirm(String newEmail, String token) {
        updateEmail  = newEmail;
        JwtDTO dto = JwtUtil.decode(token.substring(7));

        ProfileEntity byEmail = profileRepository.findByEmail(dto.getEmail());
        StringBuilder sb = new StringBuilder();
        sb.append("<h1 style=\"text-align: center\"> Complete Registration</h1>");
        sb.append("<br>");
        sb.append("<p>Click the link below to complete registration</p>\n");
        sb.append("<p><a style=\"padding: 5px; background-color: indianred; color: white\"  href=\"http://localhost:8080/api/profile/email/confirm/")
                .append(byEmail.getId()).append("\" target=\"_blank\">Click Th</a></p>\n");
        emailSendingService.sendSimpleMessage(dto.getEmail(), "Complite Registration", getConfirmationButton(byEmail.getId()));
        emailSendingService.sendMimeMessage(dto.getEmail(), "Tasdiqlash", getConfirmationButton(byEmail.getId()));
        return "Confirm sent email";
    }

    public String UpdateEmail(Integer id) {
        Optional<ProfileEntity> byId = profileRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().setEmail(updateEmail);
            profileRepository.save(byId.get());
            return "Email updated";
        }
        return "Email not found";
    }

    public String updateProfile(UpdateProfileDTO dto, String email) {
        ProfileEntity byEmail = profileRepository.findByEmail(email);
        if (byEmail == null || !byEmail.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadException("Email does not exist");
        }
        byEmail.setName(dto.getName());
        byEmail.setSurname(dto.getSurname());
        profileRepository.save(byEmail);
        return "Profile updated";
    }

    public ProfileDTO getById(Integer id) {
        Optional<ProfileEntity> byId = profileRepository.findById(id);
        if (byId.isPresent() && byId.get().getStatus().equals(ProfileStatus.ACTIVE) && byId.get().getVisible().equals(true)) {
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setId(byId.get().getId());
            profileDTO.setName(byId.get().getName());
            profileDTO.setSurname(byId.get().getSurname());
            profileDTO.setEmail(byId.get().getEmail());
            profileDTO.setPhoto(byId.get().getPhoto());
            return profileDTO;
        }
        throw new AppBadException("Invalid profile id");
    }
    public ProfileEntity getByEmail(String email){
        return profileRepository.findByEmail(email);
    }

    public String getConfirmationButton(Integer id) {
        return "<html><body>" +
                "<p>Click below to confirm your registration:</p>" +
                "<a href=\"" + domainName + "/auth/registration/confirm/" + id + "\" " +
                "style=\"display: inline-block; padding: 10px 20px; font-size: 16px; background-color:" +
                " #007bff; color: white; text-decoration: none; border-radius: 5px; border: " +
                "1px solid #007bff;\">Confirm</a> </body></html>";

    }
}
