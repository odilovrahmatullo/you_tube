package you_tube.tag.controller;

import you_tube.exceptionhandler.AppBadException;
import you_tube.tag.dto.TagDTO;
import you_tube.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping()
    public ResponseEntity<?> addCategory(@RequestBody TagDTO tagDTO) {
        TagDTO dto = tagService.craeteTag(tagDTO);
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody TagDTO tagDTO) {
        TagDTO dto = tagService.updateTag(id, tagDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategory() {
         List<TagDTO> dtoList = tagService.getAll();
        return ResponseEntity.ok(dtoList);
    }


    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
