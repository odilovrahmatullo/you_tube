package you_tube.category.controller;

import you_tube.ExceptionHandler.AppBadException;
import you_tube.category.dto.CategoryDTO;
import you_tube.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO dto = categoryService.craeteCategory(categoryDTO);
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO dto = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategory() {
         List<CategoryDTO> dtoList = categoryService.getAll();
        return ResponseEntity.ok(dtoList);
    }


    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
