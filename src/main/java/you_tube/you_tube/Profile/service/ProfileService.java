package you_tube.you_tube.Profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.you_tube.Profile.dto.ProfileDTO;
import you_tube.you_tube.Profile.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;


    public ProfileDTO create(ProfileDTO profile) {
        return null;
    }
}
