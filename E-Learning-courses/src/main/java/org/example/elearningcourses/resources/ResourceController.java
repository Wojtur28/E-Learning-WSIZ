package org.example.elearningcourses.resources;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("/{resourceId}")
    public Resource getResourceById(@PathVariable UUID resourceId) {
        return resourceService.getResourceById(resourceId);
    }

    @GetMapping
    public List<Resource> getResources() {
        return resourceService.getResources();
    }

    @PostMapping
    public Resource saveResource(@RequestBody Resource resource) {
        return resourceService.saveResource(resource);
    }

    @PutMapping("/{resourceId}")
    public Resource updateResource(@PathVariable UUID resourceId, @RequestBody Resource resource) {
        return resourceService.updateResource(resourceId, resource);
    }

    @DeleteMapping("/{resourceId}")
    public void deleteResource(@PathVariable UUID resourceId) {
        resourceService.deleteResource(resourceId);
    }
}
