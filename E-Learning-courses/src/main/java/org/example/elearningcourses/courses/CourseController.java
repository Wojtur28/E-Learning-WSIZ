package org.example.elearningcourses.courses;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable UUID courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(courseService.getCourses());
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.saveCourse(course));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable UUID courseId, @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, course));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}
