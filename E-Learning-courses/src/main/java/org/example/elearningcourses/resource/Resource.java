package org.example.elearningcourses.resource;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.elearningcourses.EntityBase;
import org.example.elearningcourses.lesson.Lesson;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "resources")
public class Resource extends EntityBase {

    private String title;

    private String url;

    @Enumerated(EnumType.STRING)
    private ResourceType type;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}
