package org.example.elearningcourses.lesson;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.elearningcourses.course.Course;
import org.example.elearningcourses.EntityBase;
import org.example.elearningcourses.resource.Resource;

import java.util.List;

@EqualsAndHashCode(callSuper = true, exclude = {"course"})
@Data
@Entity(name = "lessons")
public class Lesson extends EntityBase {

    private String title;

    private String content;

    private int sequence;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany
    @JsonManagedReference
    @JoinColumn(name = "lesson_id")
    private List<Resource> resources;
}
