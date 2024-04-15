package org.example.elearningcourses.lesson;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.elearningcourses.course.Course;
import org.example.elearningcourses.EntityBase;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "lessons")
public class Lesson extends EntityBase {

    private String title;

    private String content;

    private int sequence;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
