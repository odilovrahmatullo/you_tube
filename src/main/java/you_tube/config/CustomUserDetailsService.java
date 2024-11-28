package you_tube.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import you_tube.exceptionhandler.ResourceNotFoundException;
import you_tube.profile.entity.ProfileEntity;
import you_tube.profile.repository.ProfileRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
        ProfileEntity entity = profileRepository.findByEmail(username);
        if (entity == null) {
            throw new ResourceNotFoundException("Resource not found ");
        }
        return new CustomUserDetails(entity);
    }
}
