package org.example.elearningcourses.lessons;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/{lessonId}")
    public Lesson getLessonById(@PathVariable UUID lessonId) {
        return lessonService.getLessonById(lessonId);
    }

    @GetMapping
    public List<Lesson> getLessons() {
        return lessonService.getLessons();
    }

    @PostMapping
    public Lesson saveLesson(@RequestBody Lesson lesson) {
        return lessonService.saveLesson(lesson);
    }

    @PutMapping("/{lessonId}")
    public Lesson updateLesson(@PathVariable UUID lessonId, @RequestBody Lesson lesson) {
        return lessonService.updateLesson(lessonId, lesson);
    }

    @DeleteMapping("/{lessonId}")
    public void deleteLesson(@PathVariable UUID lessonId) {
        lessonService.deleteLesson(lessonId);
    }

}
