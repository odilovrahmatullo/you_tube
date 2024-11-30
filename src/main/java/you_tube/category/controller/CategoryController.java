package you_tube.category.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import you_tube.exceptionhandler.AppBadException;
import you_tube.category.dto.CategoryDTO;
import you_tube.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Tag(name = "Category controller", description = "All, Music, Podcast, ...")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Api for Category", description = "This api admin for category create")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO dto = categoryService.craeteCategory(categoryDTO);
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Api for Category", description = "This api admin for category by id update")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO dto = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Api for Category", description = "This api admin for category by id delete")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    @Operation(summary = "Api for Category", description = "This api user for category get all")
    public ResponseEntity<?> getAllCategory() {
         List<CategoryDTO> dtoList = categoryService.getAll();
        return ResponseEntity.ok(dtoList);
    }


    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
