package you_tube.videotag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoTagService {
    @Autowired
    private VideoTagRepository videoTagRepository;
}
