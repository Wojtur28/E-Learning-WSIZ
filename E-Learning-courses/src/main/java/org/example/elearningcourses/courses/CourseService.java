package org.example.elearningcourses.courses;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course getCourseById(UUID courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("Course not found"));
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(UUID courseId, Course course) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("Course not found"));
        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setLessons(course.getLessons());
        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(UUID courseId) {
        courseRepository.deleteById(courseId);
    }
}
