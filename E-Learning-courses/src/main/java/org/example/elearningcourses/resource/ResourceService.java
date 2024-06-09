package org.example.elearningcourses.resource;

import lombok.AllArgsConstructor;
import org.example.elearningcourses.lesson.Lesson;
import org.example.elearningcourses.lesson.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;

    private final LessonRepository lessonRepository;

    public Resource getResourceById(UUID resourceId) {
        return resourceRepository.findById(resourceId)
                .orElseThrow(() -> new IllegalStateException("Resource not found"));
    }

    public List<Resource> getResources() {
        return resourceRepository.findAll();
    }

    public Resource saveResource(Resource resource, UUID lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalStateException("Lesson not found"));

        resource.setLesson(lesson);
        return resourceRepository.save(resource);
    }

    public Resource updateResource(UUID resourceId, Resource resource) {
        Resource existingResource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new IllegalStateException("Resource not found"));
        existingResource.setTitle(resource.getTitle());
        existingResource.setUrl(resource.getUrl());
        existingResource.setType(resource.getType());
        existingResource.setLesson(resource.getLesson());
        return resourceRepository.save(existingResource);
    }

    public void deleteResource(UUID resourceId) {
        resourceRepository.deleteById(resourceId);
    }

    public List<Resource> getResourcesByLesson(UUID lessonId) {
        return resourceRepository.findByLessonId(lessonId);
    }

}
