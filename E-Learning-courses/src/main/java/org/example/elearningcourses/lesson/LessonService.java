package org.example.elearningcourses.lesson;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    public Lesson getLessonById(UUID lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalStateException("Lesson not found"));
    }

    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(UUID lessonId, Lesson lesson) {
        Lesson existingLesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalStateException("Lesson not found"));
        existingLesson.setTitle(lesson.getTitle());
        existingLesson.setContent(lesson.getContent());
        existingLesson.setSequence(lesson.getSequence());
        existingLesson.setCourse(lesson.getCourse());
        return lessonRepository.save(existingLesson);
    }

    public void deleteLesson(UUID lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
