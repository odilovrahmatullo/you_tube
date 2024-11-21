package you_tube.tag.service;

import you_tube.exp.AppBadRequest;
import you_tube.tag.dto.TagDTO;
import you_tube.tag.entity.TagEntity;
import you_tube.tag.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public TagDTO craeteTag(TagDTO dto) {
        TagEntity entity = new TagEntity();
        entity.setName(dto.getName());
        entity.setVisible(Boolean.TRUE);
        entity.setCreatedDate(LocalDateTime.now());

        tagRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public TagDTO updateTag(Integer id, TagDTO dto) {                           //ADMIN
        TagEntity entity = tagRepository.findByIdAndVisibleTrue(id);
        if (entity == null) {
            throw new AppBadRequest("Tag not found");
        }
        entity.setName(dto.getName());
        tagRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
//        return "Successful:" + dto.getName();
        return dto;
    }

    public void deleteTag(Integer id) {                                        //ADMIN
        TagEntity entity = tagRepository.findByIdAndVisibleTrue(id);
        if (entity == null) {
            throw new AppBadRequest("Tag not found");
        }
        entity.setVisible(Boolean.FALSE);
        tagRepository.save(entity);
    }

    public List<TagDTO> getAll() {
        Iterable<TagEntity> entities = tagRepository.findAll();
        if (entities == null) {
            throw new AppBadRequest("Tag not found");
        }
        List<TagDTO> dtoList = new ArrayList<>();
        for (TagEntity category : entities) {
            TagDTO dto = new TagDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setCreatedDate(category.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
