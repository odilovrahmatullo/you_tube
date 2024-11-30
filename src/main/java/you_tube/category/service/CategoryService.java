package you_tube.category.service;

import you_tube.category.dto.CategoryDTO;
import you_tube.category.dto.SimpleCategoryDTO;
import you_tube.category.entity.CategoryEntity;
import you_tube.category.repository.CategoryRepository;
import you_tube.exceptionhandler.AppBadRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import you_tube.exceptionhandler.ResourceNotFoundException;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryDTO craeteCategory(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(dto.getName());
        entity.setVisible(Boolean.TRUE);
        entity.setCreatedDate(LocalDateTime.now());

        categoryRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public CategoryDTO updateCategory(Integer id, CategoryDTO dto) {
        CategoryEntity entity = categoryRepository.findByIdAndVisibleTrue(id);
        if (entity == null) {
            throw new AppBadRequest("Category not found");
        }
        entity.setName(dto.getName());
        categoryRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
//        return "Successful:" + dto.getName();
        return dto;
    }

    public void deleteCategory(Integer id) {
        CategoryEntity entity = categoryRepository.findByIdAndVisibleTrue(id);
        if (entity == null) {
            throw new AppBadRequest("Category not found");
        }
        entity.setVisible(Boolean.FALSE);
        categoryRepository.save(entity);
    }

    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> entities = categoryRepository.findAll();
        if (entities == null) {
            throw new AppBadRequest("Category not found");
        }
        List<CategoryDTO> dtoList = new ArrayList<>();
        for (CategoryEntity category : entities) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setCreatedDate(category.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public CategoryEntity getById(Integer id){
        CategoryEntity entity = categoryRepository.findByIdAndVisibleTrue(id);
        if(entity==null){
            throw new ResourceNotFoundException("category not found");
        }
        return entity;
    }

    public SimpleCategoryDTO getDTO(Integer id){
        SimpleCategoryDTO dto = new SimpleCategoryDTO();
        dto.setId(id);
        dto.setName(getById(id).getName());
        return dto;
    }
}
