package org.example.elearningcourses.resource;

import lombok.AllArgsConstructor;
import org.example.elearningsecurity.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService resourceService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    private boolean verifyToken(String token) {
        String username = jwtService.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtService.isTokenValid(token, userDetails);
    }

    @GetMapping("/lessons/{lessonId}")
    public List<Resource> getResourcesByLesson(@PathVariable UUID lessonId, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ") || !verifyToken(authHeader.substring(7))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT Token");
        }
        return resourceService.getResourcesByLesson(lessonId);
    }

    @GetMapping("/{resourceId}")
    public Resource getResourceById(@PathVariable UUID resourceId, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ") || !verifyToken(authHeader.substring(7))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT Token");
        }
        return resourceService.getResourceById(resourceId);
    }

    @GetMapping
    public List<Resource> getResources(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ") || !verifyToken(authHeader.substring(7))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT Token");
        }
        return resourceService.getResources();
    }

    @PostMapping
    public Resource saveResource(@RequestBody Resource resource, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ") || !verifyToken(authHeader.substring(7))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT Token");
        }
        return resourceService.saveResource(resource);
    }

    @PutMapping("/{resourceId}")
    public Resource updateResource(@PathVariable UUID resourceId, @RequestBody Resource resource, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ") || !verifyToken(authHeader.substring(7))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT Token");
        }
        return resourceService.updateResource(resourceId, resource);
    }

    @DeleteMapping("/{resourceId}")
    public void deleteResource(@PathVariable UUID resourceId, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ") || !verifyToken(authHeader.substring(7))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT Token");
        }
        resourceService.deleteResource(resourceId);
    }
}
