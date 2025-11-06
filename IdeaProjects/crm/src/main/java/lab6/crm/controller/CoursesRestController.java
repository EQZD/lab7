package lab6.crm.controller;

import lab6.crm.entity.Courses;
import lab6.crm.service.CoursesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CoursesRestController {

    private final CoursesService coursesService;

    public CoursesRestController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @GetMapping
    public List<Courses> getAll() {
        return coursesService.getAll();
    }

    @PostMapping
    public ResponseEntity<Courses> create(@RequestBody Courses course) {
        coursesService.save(course);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        coursesService.delete(id);
        return ResponseEntity.ok("Course with id " + id + " deleted successfully");
    }
}
