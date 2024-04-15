package org.example.elearningcourses.course;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.elearningcourses.EntityBase;
import org.example.elearningcourses.lesson.Lesson;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "courses")
public class Course extends EntityBase {

    private String title;

    private String description;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lesson> lessons;
}
